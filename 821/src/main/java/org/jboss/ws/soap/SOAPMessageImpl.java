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

import org.jboss.ws.WSException;
import org.jboss.ws.xop.XOPContext;
import org.jboss.ws.metadata.EndpointMetaData;
import org.jboss.ws.metadata.OperationMetaData;
import org.jboss.ws.soap.attachment.*;

import javax.mail.MessagingException;
import javax.xml.soap.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The root class for all SOAP messages. As transmitted on the "wire", a SOAP message is an XML document or a
 * MIME message whose first body part is an XML/SOAP document.
 *
 * @author Thomas.Diesler@jboss.org
 * @author <a href="mailto:jason@stacksmash.com">Jason T. Greene</a>
 */
public class SOAPMessageImpl extends SOAPMessage
{
   private boolean saveRequired = true;
   private MimeHeaders mimeHeaders = new MimeHeaders();
   private List<AttachmentPart> attachments = new LinkedList<AttachmentPart>();
   private CIDGenerator cidGenerator = new CIDGenerator();
   private boolean isXOPMessage;
   private boolean faultMessage;
   private SOAPPartImpl soapPart;
   private MultipartRelatedEncoder multipartRelatedEncoder;

   // Cache the associated operation meta data
   private OperationMetaData opMetaData;

   SOAPMessageImpl() throws SOAPException
   {
      soapPart = new SOAPPartImpl(this);
      setProperty(CHARACTER_SET_ENCODING, "UTF-8");
      setProperty(WRITE_XML_DECLARATION, false);
   }

   public CIDGenerator getCidGenerator()
   {
      return cidGenerator;
   }

   public boolean isXOPMessage()
   {
      return isXOPMessage;
   }

   public void setXOPMessage(boolean isXOPMessage)
   {
      this.isXOPMessage = isXOPMessage;
   }

   public void setAttachments(Collection<AttachmentPart> parts) throws SOAPException
   {
      for (AttachmentPart part : parts)
      {
         attachments.add(part);
      }
      saveRequired = true;
   }

   public void addAttachmentPart(AttachmentPart part)
   {
      if (part == null)
         return;

      attachments.add(part);
      saveRequired = true;
   }

   public AttachmentPart getAttachmentByContentId(String cid) throws SOAPException
   {
      for (AttachmentPart part : attachments)
      {
         String contentId = part.getContentId();
         if (contentId.equals(cid))
            return part;
      }
      return null;
   }

   public AttachmentPart removeAttachmentByContentId(String cid)
   {
      for (AttachmentPart part : attachments)
      {
         String contentId = part.getContentId();
         if (contentId.equals(cid))
         {
           attachments.remove(part);
           return part;
         }
      }

      return null;
   }
   public AttachmentPart getAttachmentByPartName(String partName)
   {
      for (AttachmentPart part : attachments)
      {
         String contentId = part.getContentId();
         if (contentId.startsWith("<" + partName + "="))
            return part;
      }
      return null;
   }

   public AttachmentPart createAttachmentPart()
   {
      return new AttachmentPartImpl();
   }

   public String getContentDescription()
   {
      String[] value = mimeHeaders.getHeader(MimeConstants.CONTENT_DESCRIPTION);

      return (value == null) ? null : value[0];
   }

   public void setContentDescription(String description)
   {
      mimeHeaders.setHeader(MimeConstants.CONTENT_DESCRIPTION, description);
   }

   public MimeHeaders getMimeHeaders()
   {
      return mimeHeaders;
   }

   public void setMimeHeaders(MimeHeaders headers)
   {
      if (headers == null)
         throw new IllegalArgumentException("MimeHeaders cannot be null");
      this.mimeHeaders = headers;
   }

   public SOAPPart getSOAPPart()
   {
      return soapPart;
   }

   public void removeAllAttachments()
   {
      attachments.clear();
      saveRequired = true;
   }

   public int countAttachments()
   {
      return attachments.size();
   }

   public Iterator getAttachments()
   {
      // Someone could call remove on this iterator, affecting the attachment count
      saveRequired = true;
      return attachments.iterator();
   }

   public Iterator getAttachments(MimeHeaders headers)
   {
      if (headers == null)
         throw new WSException("MimeHeaders can not be null");

      return new MimeMatchingAttachmentsIterator(headers, attachments);
   }

   public void saveChanges() throws SOAPException
   {
      if (saveRequired == true)
      {
         try
         {
            String contentType;
            if (isXOPMessage() && XOPContext.isMTOMEnabled())
            {
               multipartRelatedEncoder = new MultipartRelatedXOPEncoder(this);
               multipartRelatedEncoder.encodeMultipartRelatedMessage();
               contentType = multipartRelatedEncoder.getContentType();
            }
            else if (attachments.size() > 0)
            {
               multipartRelatedEncoder = new MultipartRelatedSwAEncoder(this);
               multipartRelatedEncoder.encodeMultipartRelatedMessage();
               contentType = multipartRelatedEncoder.getContentType();
            }
            else
            {
               contentType = MimeConstants.TYPE_SOAP11 + "; charset=" + getCharSetEncoding();
            }
            mimeHeaders.setHeader(MimeConstants.CONTENT_TYPE, contentType);
         }
         catch (MessagingException ex)
         {
            throw new SOAPException(ex);
         }

         /*
          * We are lazily encoding our message, which means that currently
          * Content-Length is not being calculated. This should not be a problem
          * because HTTP/1.1 does not require that it be sent (WS Basic Profile 1.1
          * states that implementations SHOULD send HTTP 1.1). If it is determined
          * that it must be sent, this perhaps should be done by the transport
          * layer. However, there could be a space optimization where length is
          * precalulated per attachment, and that calculation would, of course,
          * belong here.
          */

         saveRequired = false;
      }
   }

   public boolean saveRequired()
   {
      return saveRequired;
   }

   public void writeTo(OutputStream outs) throws SOAPException, IOException
   {
      // Save all changes
      saveChanges();

      // If there are attahcments then we delegate encoding to MultipartRelatedEncoder
      if (attachments.size() > 0)
      {
         multipartRelatedEncoder.writeTo(outs);
      }
      else
      {
         SOAPEnvelope soapEnv = getSOAPPart().getEnvelope();
         if (soapEnv != null)
         {
            boolean writeXML = isWriteXMLDeclaration();
            String charsetEncoding = getCharSetEncoding();
            SAAJElementWriter writer = new SAAJElementWriter(outs, charsetEncoding);
            writer.setWriteXMLDeclaration(writeXML).print((SOAPEnvelopeImpl)soapEnv);
         }
      }
   }

   private String getCharSetEncoding() throws SOAPException
   {
      String charsetName = (String)getProperty(CHARACTER_SET_ENCODING);
      if (charsetName == null)
         charsetName = "UTF-8";
      return charsetName;
   }

   /** Get the operation meta data for this SOAP message
    */
   public OperationMetaData getOperationMetaData(EndpointMetaData epMetaData) throws SOAPException
   {
      if (opMetaData == null)
      {
         SOAPMessageDispatcher dispatcher = new SOAPMessageDispatcher();
         opMetaData = dispatcher.getDispatchDestination(epMetaData, this);
      }
      return opMetaData;
   }

   /**
    * Marks this <code>SOAPMessage</code> as a fault. Otherwise, the message
    * will be checked for a SOAPFault. The reason for this is to allow for
    * faults to be encrypted, in which case there is no SOAPFault.
    *
    * @param faultMessage whether this message is a fault
    */
   public void setFaultMessage(boolean faultMessage)
   {
      this.faultMessage = faultMessage;
   }

   public boolean isFaultMessage()
   {
      if (faultMessage)
         return true;

      SOAPFault soapFault = null;
      try
      {
         soapFault = getSOAPBody().getFault();
      }
      catch (Exception ignore)
      {
      }
      return soapFault != null;
   }

   private boolean isWriteXMLDeclaration() throws SOAPException
   {
      Boolean booleanValue = new Boolean(false);
      Object propValue = getProperty(WRITE_XML_DECLARATION);
      if (propValue instanceof Boolean)
         booleanValue = (Boolean)propValue;
      if (propValue instanceof String)
         booleanValue = new Boolean((String)propValue);
      return booleanValue.booleanValue();
   }

   public static class MimeMatchingAttachmentsIterator implements Iterator
   {
      private Iterator iterator;

      private MimeHeaders headers = new MimeHeaders();

      private AttachmentPart lastMatch;

      public MimeMatchingAttachmentsIterator(MimeHeaders headers, List attachments)
      {
         iterator = attachments.iterator();

         if (headers != null)
            this.headers = headers;
      }

      private boolean containsAllHeaders(Iterator headerIterator, AttachmentPart part)
      {
         while (headerIterator.hasNext())
         {
            MimeHeader header = (MimeHeader)headerIterator.next();
            String[] values = part.getMimeHeader(header.getName());
            if (values == null)
               return false;

            boolean match = false;
            for (int j = 0; j < values.length; j++)
            {
               if (values[j].equalsIgnoreCase(header.getValue()))
               {
                  match = true;
                  break;
               }
            }

            if (!match)
               return false;
         }

         return true;
      }

      private void nextMatch()
      {
         while (iterator.hasNext())
         {
            AttachmentPart part = (AttachmentPart)iterator.next();
            if (containsAllHeaders(headers.getAllHeaders(), part))
            {
               lastMatch = part;
               break;
            }
         }
      }

      public boolean hasNext()
      {
         if (lastMatch == null)
            nextMatch();

         return lastMatch != null;
      }

      public Object next()
      {
         if (!hasNext())
            return null;

         Object retval = lastMatch;
         lastMatch = null;

         return retval;
      }

      public void remove()
      {
         iterator.remove();
      }
   }
}