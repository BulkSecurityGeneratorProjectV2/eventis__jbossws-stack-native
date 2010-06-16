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
import org.jboss.ws.binding.BindingException;
import org.jboss.xb.binding.NamespaceRegistry;
import org.jboss.xb.binding.SimpleTypeBindings;
import org.w3c.dom.NamedNodeMap;

/**
 * Serializer for QNames.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 04-Dec-2004
 */
public class QNameSerializer extends SerializerSupport
{
   // provide logging
   private static final Logger log = Logger.getLogger(QNameSerializer.class);

   public String serialize(QName xmlName, QName xmlType, Object value, SerializationContextImpl serContext, NamedNodeMap attributes) throws BindingException
   {
      log.debug("serialize: [xmlName=" + xmlName + ",xmlType=" + xmlType + "]");

      QName qnameValue = (QName)value;
      String nsURI = qnameValue.getNamespaceURI();

      NamespaceRegistry nsRegistry = serContext.getNamespaceRegistry();

      // Remove prefix and register again
      if (nsURI.length() > 0)
      {
         qnameValue = new QName(qnameValue.getNamespaceURI(), qnameValue.getLocalPart());
         qnameValue = nsRegistry.registerQName(qnameValue);
      }

      String valueStr = SimpleTypeBindings.marshalQName(qnameValue, nsRegistry);
      String xmlFragment = wrapValueStr(xmlName, valueStr, nsRegistry, attributes);

      // Insert the NS declaration for the qnameValue
      if (nsURI.length() > 0)
      {
         StringBuilder buffer = new StringBuilder(xmlFragment);
         int indexGT = xmlFragment.indexOf(">");
         String prefix = qnameValue.getPrefix();
         buffer.insert(indexGT, " xmlns:" + prefix + "='" + nsURI + "'");
         xmlFragment = buffer.toString();
      }

      return xmlFragment;
   }
}