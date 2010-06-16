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
package org.jboss.ws.jaxrpc.encoding;

// $Id$

import javax.xml.namespace.QName;

import org.jboss.logging.Logger;
import org.jboss.ws.xop.XOPMarshallerImpl;
import org.jboss.ws.binding.BindingException;
import org.jboss.xb.binding.NamespaceRegistry;
import org.jboss.xb.binding.SimpleTypeBindings;
import org.jboss.xb.binding.sunday.xop.XOPMarshaller;
import org.w3c.dom.NamedNodeMap;

/**
 * A serializer that can handle XMLSchema simple types.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 18-Oct-2004
 */
public class SimpleSerializer extends SerializerSupport
{
   // provide logging
   private static final Logger log = Logger.getLogger(SimpleSerializer.class);

   /** Marshal the value for a given XMLSchema type
    * @param xmlType local part of the schema type
    * @param value the value to marshal
    * @param serContext
    * @return the string representation od the value
    */
   public String serialize(QName xmlName, QName xmlType, Object value, SerializationContextImpl serContext, NamedNodeMap attributes) throws BindingException
   {
      log.debug("serialize: [xmlName=" + xmlName + ",xmlType=" + xmlType + "]");

      String valueStr;
      String typeName = xmlType.getLocalPart();
      NamespaceRegistry nsRegistry = serContext.getNamespaceRegistry();
      XOPMarshaller attachmentMarshaller = new XOPMarshallerImpl();

      if(attachmentMarshaller.isXOPPackage() && "base64Binary".equals(typeName))
      {
         // Only Byte[] and byte[] are mapped to SimpleSerializer,
         // other base64 types are mapped to the JAXBSerializer         
         if(value instanceof byte[] )
         {
            valueStr = attachmentMarshaller.addMtomAttachment(
                (byte[])value,
                xmlName.getNamespaceURI(),
                xmlName.getLocalPart()
                );
         }
         else {
            throw new IllegalArgumentException("Unable to apply MTOM to " + value.getClass());
         }
      }
      else
      {
         valueStr = SimpleTypeBindings.marshal(xmlType.getLocalPart(), value, nsRegistry);
      }

      String xmlFragment = wrapValueStr(xmlName, valueStr, nsRegistry, attributes);
      return xmlFragment;
   }
}