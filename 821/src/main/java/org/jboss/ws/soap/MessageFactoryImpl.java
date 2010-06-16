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
package org.jboss.ws.soap;

// $Id$

import org.jboss.logging.Logger;
import org.jboss.ws.Constants;
import org.jboss.ws.jaxrpc.Style;
import org.jboss.ws.soap.attachment.MimeConstants;
import org.jboss.ws.soap.attachment.MultipartRelatedDecoder;
import org.jboss.ws.utils.IOUtils;
import org.jboss.ws.utils.ThreadLocalAssociation;

import javax.mail.internet.ContentType;
import javax.mail.internet.ParseException;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

/**
 * MessageFactory implementation
 * 
 * @author Thomas.Diesler@jboss.org
 */
public class MessageFactoryImpl extends MessageFactory
{
   private static Logger log = Logger.getLogger(MessageFactoryImpl.class);

   // The envelope URI used by the MessageFactory
   private String envelopeURI = Constants.NS_SOAP11_ENV;

   // The style used by this MessageFactory
   private Style style;

   /**
    * Get the SOAP envelope URI this factory will use when creating envelopes.
    */
   public String getEnvelopeURI()
   {
      return envelopeURI;
   }

   /**
    * Set the SOAP envelope URI this factory will use when creating envelopes.
    */
   public void setEnvelopeURI(String envelopeURI)
   {
      this.envelopeURI = envelopeURI;
   }

   /**
    * Get the Style this message factory will use
    */
   public Style getStyle()
   {
      if (style == null)
      {
         SOAPMessageContextImpl msgContext = MessageContextAssociation.peekMessageContext();
         if (msgContext != null && msgContext.getOperationMetaData() != null)
         {
            style = msgContext.getOperationMetaData().getStyle();
         }
         log.trace("Using style: " + style);
      }
      return style;
   }

   public void setStyle(Style style)
   {
      this.style = style;
   }

   /**
    * Creates a new SOAPMessage object with the default SOAPPart, SOAPEnvelope,
    * SOAPBody, and SOAPHeader objects. Profile-specific message factories can
    * choose to prepopulate the SOAPMessage object with profile-specific
    * headers.
    * 
    * Content can be added to this message's SOAPPart object, and the message
    * can be sent "as is" when a message containing only a SOAP part is
    * sufficient. Otherwise, the SOAPMessage object needs to create one or more
    * AttachmentPart objects and add them to itself. Any content that is not in
    * XML format must be in an AttachmentPart object.
    * 
    * @return a new SOAPMessage object
    * @throws javax.xml.soap.SOAPException
    *             if a SOAP error occurs
    */
   public SOAPMessage createMessage() throws SOAPException
   {
      SOAPMessageImpl soapMessage = new SOAPMessageImpl();
      SOAPPartImpl soapPart = (SOAPPartImpl)soapMessage.getSOAPPart();
      new SOAPEnvelopeImpl(soapPart, envelopeURI);
      return soapMessage;
   }

   /**
    * Internalizes the contents of the given InputStream object into a new
    * SOAPMessage object and returns the SOAPMessage object.
    * 
    * @param mimeHeaders
    *            the transport-specific headers passed to the message in a
    *            transport-independent fashion for creation of the message
    * @param ins
    *            the InputStream object that contains the data for a message
    * @return a new SOAPMessage object containing the data from the given
    *         InputStream object
    * @throws java.io.IOException
    *             if there is a problem in reading data from the input stream
    * @throws javax.xml.soap.SOAPException
    *             if the message is invalid
    */
   public SOAPMessage createMessage(MimeHeaders mimeHeaders, InputStream ins) throws IOException, SOAPException
   {
      try
      {
         ThreadLocalAssociation.localDomExpansion().set(Boolean.FALSE);
         return createMessageInternal(mimeHeaders, ins, false);
      }
      finally{
         ThreadLocalAssociation.localDomExpansion().set(Boolean.TRUE);
      }
   }

   public SOAPMessage createMessageInternal(MimeHeaders mimeHeaders, InputStream ins, boolean ingnoreParseException) throws IOException, SOAPException
   {
      if (mimeHeaders == null)
      {
         mimeHeaders = new MimeHeaders();
      }
      else if (log.isTraceEnabled())
      {
         Iterator<MimeHeader> itMimeHeaders = mimeHeaders.getAllHeaders();
         while (itMimeHeaders.hasNext())
         {
            MimeHeader mh = itMimeHeaders.next();
            log.trace(mh);
         }
      }

      ContentType contentType = getContentType(mimeHeaders);
      log.debug("createMessage: [contentType=" + contentType + "]");

      // Debug the incoming message
      if (log.isTraceEnabled())
      {
         ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
         IOUtils.copyStream(baos, ins);
         byte[] bytes = baos.toByteArray();

         log.trace("createMessage\n" + new String(bytes));
         ins = new ByteArrayInputStream(bytes);
      }

      Collection<AttachmentPart> attachments = null;
      if (isMultipartRelatedContent(contentType))
      {
         MultipartRelatedDecoder decoder;
         try
         {
            decoder = new MultipartRelatedDecoder(contentType);
            decoder.decodeMultipartRelatedMessage(ins);
         }
         catch (RuntimeException rte)
         {
            throw rte;
         }
         catch (IOException ex)
         {
            throw ex;
         }
         catch (Exception ex)
         {
            throw new SOAPException("Cannot decode multipart related message", ex);
         }

         ins = decoder.getRootPart().getDataHandler().getInputStream();
         attachments = decoder.getRelatedParts();
      }
      else if (isSoapContent(contentType) == false)
      {
         throw new SOAPException("Unsupported content type: " + contentType);
      }

      SOAPMessageImpl soapMessage = new SOAPMessageImpl();
      if (mimeHeaders != null)
         soapMessage.setMimeHeaders(mimeHeaders);

      if (attachments != null)
         soapMessage.setAttachments(attachments);

      // create the SAAJ object model
      SAAJEnvelopeBuilderFactory builderFactory = SAAJEnvelopeBuilderFactory.newInstance();
      SAAJEnvelopeBuilder envelopeBuilder = builderFactory.createSAAJEnvelopeBuilder();
      envelopeBuilder.setIgnoreParseException(ingnoreParseException);
      envelopeBuilder.setStyle(getStyle());
      envelopeBuilder.setSOAPMessage(soapMessage);
      envelopeBuilder.build(ins);

      return soapMessage;
   }


   private static ContentType getContentType(MimeHeaders headers) throws SOAPException
   {
      ContentType contentType = null;
      try
      {
         String[] type = headers.getHeader(MimeConstants.CONTENT_TYPE);
         if (type != null)
         {
            contentType = new ContentType(type[0]);
         }
         else
         {
            contentType = new ContentType(MimeConstants.TYPE_SOAP11);
         }
         return contentType;
      }
      catch (ParseException e)
      {
         throw new SOAPException("Could not parse content type:" + e);
      }
   }

   private boolean isSoapContent(ContentType type)
   {
      String baseType = type.getBaseType();
      return MimeConstants.TYPE_SOAP11.equals(baseType) || MimeConstants.TYPE_SOAP12.equals(baseType);
   }

   private boolean isMultipartRelatedContent(ContentType type)
   {
      String baseType = type.getBaseType();
      return MimeConstants.TYPE_MULTIPART_RELATED.equals(baseType);
   }
}