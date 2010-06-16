/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.ws.jaxrpc;

// $Id$

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.encoding.TypeMapping;
import javax.xml.rpc.soap.SOAPFaultException;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.jboss.logging.Logger;
import org.jboss.util.xml.DOMUtils;
import org.jboss.util.xml.DOMWriter;
import org.jboss.ws.Constants;
import org.jboss.ws.WSException;
import org.jboss.ws.binding.BindingException;
import org.jboss.ws.jaxrpc.encoding.DeserializerFactoryBase;
import org.jboss.ws.jaxrpc.encoding.DeserializerSupport;
import org.jboss.ws.jaxrpc.encoding.SerializationContextImpl;
import org.jboss.ws.jaxrpc.encoding.SerializerFactoryBase;
import org.jboss.ws.jaxrpc.encoding.SerializerSupport;
import org.jboss.ws.metadata.FaultMetaData;
import org.jboss.ws.metadata.OperationMetaData;
import org.jboss.ws.soap.MessageContextAssociation;
import org.jboss.ws.soap.MessageFactoryImpl;
import org.jboss.ws.soap.NameImpl;
import org.jboss.ws.soap.SOAPEnvelopeImpl;
import org.jboss.ws.soap.SOAPFactoryImpl;
import org.jboss.ws.soap.SOAPMessageContextImpl;
import org.jboss.xb.binding.NamespaceRegistry;
import org.w3c.dom.Element;

/**
 * A Helper that translates between SOAPFaultException and SOAPFault.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 03-Feb-2005
 */
public class SOAPFaultExceptionHelper
{
   // provide logging
   private static Logger log = Logger.getLogger(SOAPFaultExceptionHelper.class);

   private static List<QName> allowedFaultCodes = new ArrayList<QName>();
   static
   {
      allowedFaultCodes.add(Constants.SOAP11_FAULT_CODE_CLIENT);
      allowedFaultCodes.add(Constants.SOAP11_FAULT_CODE_SERVER);
      allowedFaultCodes.add(Constants.SOAP11_FAULT_CODE_VERSION_MISMATCH);
      allowedFaultCodes.add(Constants.SOAP11_FAULT_CODE_MUST_UNDERSTAND);
   }

   /** Hide constructor */
   private SOAPFaultExceptionHelper()
   {
   }

   /** Factory method for FaultException for a given SOAPFault */
   public static SOAPFaultException getSOAPFaultException(SOAPFault soapFault)
   {
      QName faultCode = ((NameImpl)soapFault.getFaultCodeAsName()).toQName();
      String faultString = soapFault.getFaultString();
      String faultActor = soapFault.getFaultActor();
      Detail detail = soapFault.getDetail();

      SOAPFaultException faultEx = new SOAPFaultException(faultCode, faultString, faultActor, detail);

      SOAPMessageContextImpl msgContext = MessageContextAssociation.peekMessageContext();
      if (detail != null && msgContext != null)
      {
         SerializationContextImpl serContext = msgContext.getSerializationContext();
         TypeMapping typeMapping = serContext.getTypeMapping();

         Iterator it = detail.getDetailEntries();
         while (it.hasNext())
         {
            DetailEntry deElement = (DetailEntry)it.next();
            Name deName = deElement.getElementName();
            QName xmlName = new QName(deName.getURI(), deName.getLocalName());

            OperationMetaData opMetaData = msgContext.getOperationMetaData();
            FaultMetaData faultMetaData = opMetaData.getFault(xmlName);
            if (faultMetaData != null)
            {
               log.debug("Deserialize fault: " + faultMetaData);
               QName xmlType = faultMetaData.getXmlType();
               Class javaType = faultMetaData.getJavaType();

               // Get the deserializer from the type mapping
               DeserializerFactoryBase desFactory = (DeserializerFactoryBase)typeMapping.getDeserializer(javaType, xmlType);
               if (desFactory == null)
                  throw new JAXRPCException("Cannot obtain deserializer factory for: " + xmlType);

               // Try jaxb deserialization
               try
               {
                  // http://jira.jboss.org/jira/browse/JBWS-955
                  // Cannot deserialize fault detail
                  String prefix = deName.getPrefix();
                  if (prefix.length() > 0)
                  {
                     String nsURI = deName.getURI();
                     String attrValue = deElement.getAttribute("xmlns:" + prefix);
                     if (nsURI.length() > 0 && attrValue.length() == 0)
                        deElement.addNamespaceDeclaration(prefix, nsURI);
                  }   
                  
                  String xmlFragment = DOMWriter.printNode(deElement, false);
                  DeserializerSupport des = (DeserializerSupport)desFactory.getDeserializer();
                  Object userEx = des.deserialize(xmlName, xmlType, xmlFragment, serContext);
                  if (userEx == null || (userEx instanceof Exception) == false)
                     throw new WSException("Invalid deserialization result: " + userEx);

                  faultEx.initCause((Exception)userEx);
               }
               catch (Exception ex)
               {
                  log.error("Cannot deserialize fault detail", ex);
               }
            }
            else
            {
               log.debug("Cannot find fault meta data for: " + xmlName);
            }
         }
      }

      return faultEx;
   }

   /** Translate the request exception into a SOAPFault message.
    */
   public static SOAPMessage exceptionToFaultMessage(Exception reqEx)
   {
      // Get or create the SOAPFaultException
      SOAPFaultException faultEx;
      if (reqEx instanceof SOAPFaultException)
      {
         faultEx = (SOAPFaultException)reqEx;
      }
      else
      {
         QName faultCode = Constants.SOAP11_FAULT_CODE_CLIENT;
         String faultString = (reqEx.getMessage() != null ? reqEx.getMessage() : reqEx.toString());
         faultEx = new SOAPFaultException(faultCode, faultString, null, null);
         faultEx.initCause(reqEx);
      }

      Throwable faultCause = faultEx.getCause();
      log.error("SOAP request exception", faultCause != null ? faultCause : faultEx);

      try
      {
         SOAPMessage faultMessage = toSOAPMessage(faultEx);
         return faultMessage;
      }
      catch (Exception ex)
      {
         log.error("Error creating SOAPFault message", ex);
         throw new JAXRPCException("Cannot create SOAPFault message for: " + faultEx);
      }
   }

   private static SOAPMessage toSOAPMessage(SOAPFaultException faultEx) throws SOAPException
   {
      assertFaultCode(faultEx.getFaultCode());

      MessageFactory factory = new MessageFactoryImpl();
      SOAPMessage soapMessage = factory.createMessage();

      SOAPEnvelopeImpl soapEnvelope = (SOAPEnvelopeImpl)soapMessage.getSOAPPart().getEnvelope();
      NamespaceRegistry nsRegistry = soapEnvelope.getNamespaceRegistry();
      SOAPBody soapBody = soapEnvelope.getBody();

      QName faultCode = faultEx.getFaultCode();
      if (faultCode.getNamespaceURI().length() > 0)
         faultCode = nsRegistry.registerQName(faultCode);
      
      String faultString = getValidFaultString(faultEx);
      SOAPFault soapFault = soapBody.addFault(new NameImpl(faultCode), faultString);
      
      String faultActor = faultEx.getFaultActor();
      if (faultActor != null)
      {
         SOAPElement soapElement = soapFault.addChildElement("faultactor");
         soapElement.addTextNode(faultActor);
      }

      Exception faultCause = (Exception)faultEx.getCause();
      Detail detail = faultEx.getDetail();
      if (detail != null)
      {
         soapFault.addChildElement(detail);
      }
      else if (faultCause != null && (faultCause instanceof RuntimeException) == false)
      {
         Class javaType = faultCause.getClass();

         SOAPMessageContextImpl msgContext = MessageContextAssociation.peekMessageContext();
         SerializationContextImpl serContext = msgContext.getSerializationContext();
         TypeMapping typeMapping = serContext.getTypeMapping();

         OperationMetaData opMetaData = msgContext.getOperationMetaData();
         if (opMetaData != null && opMetaData.getFault(javaType) != null)
         {
            FaultMetaData faultMetaData = opMetaData.getFault(javaType);
            QName xmlName = faultMetaData.getXmlName();
            QName xmlType = faultMetaData.getXmlType();

            xmlName = nsRegistry.registerQName(xmlName);

            // Get the serializer from the type mapping
            SerializerFactoryBase serFactory = (SerializerFactoryBase)typeMapping.getSerializer(javaType, xmlType);
            if (serFactory == null)
               throw new JAXRPCException("Cannot obtain serializer factory for: " + xmlType);

            try
            {
               SerializerSupport ser = (SerializerSupport)serFactory.getSerializer();
               String xmlFragment = ser.serialize(xmlName, xmlType, faultCause, serContext, null);

               SOAPFactoryImpl soapFactory = new SOAPFactoryImpl();
               Element domElement = DOMUtils.parse(xmlFragment);
               SOAPElement soapElement = soapFactory.createElement(domElement, true);

               detail = soapFault.addDetail();
               detail.addChildElement(soapElement);
            }
            catch (BindingException e)
            {
               throw new JAXRPCException(e);
            }
            catch (IOException e)
            {
               throw new JAXRPCException(e);
            }
         }
         else
         {
            log.debug("Cannot obtain fault meta data for: " + javaType);
         }
      }

      return soapMessage;
   }

   private static String getValidFaultString(SOAPFaultException faultEx)
   {
      String faultString = faultEx.getFaultString();
      if (faultString == null || faultString.length() == 0)
         faultString = "Unqualified " + faultEx.getFaultCode() + " fault";

      return faultString;
   }

   private static void assertFaultCode(QName faultCode)
   {
      if (faultCode == null)
         throw new IllegalArgumentException("faultcode cannot be null");

      // For lazy folkes like the CTS that don't bother to give 
      // a namesapce URI, assume they use a standard code
      String nsURI = faultCode.getNamespaceURI();
      if ("".equals(nsURI))
      {
         log.warn("Empty namespace URI with fault code '" + faultCode + "', assuming: " + Constants.NS_SOAP11_ENV);
         faultCode = new QName(Constants.NS_SOAP11_ENV, faultCode.getLocalPart());
      }
         
      // WS-I allows non custom faultcodes if you use a non soap namespace
      if (Constants.NS_SOAP11_ENV.equals(nsURI) && allowedFaultCodes.contains(faultCode) == false)
         throw new IllegalArgumentException("Illegal faultcode '" + faultCode + "', allowed values are: " + allowedFaultCodes);
   }
}