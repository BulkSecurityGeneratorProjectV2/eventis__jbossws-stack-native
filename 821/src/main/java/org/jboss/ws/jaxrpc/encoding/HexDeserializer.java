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
import org.jboss.xb.binding.SimpleTypeBindings;

/**
 * Deserializer for hexBinary.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 04-Dec-2004
 * @see <a href="http://www.w3.org/TR/xmlschema-2/#hexBinary">XML Schema 3.2.16</a>
 */
public class HexDeserializer extends DeserializerSupport
{
   // provide logging
   private static final Logger log = Logger.getLogger(HexDeserializer.class);

   public Object deserialize(QName xmlName, QName xmlType, String xmlFragment, SerializationContextImpl serContext) throws BindingException
   {
      log.debug("deserialize: [xmlName=" + xmlName + ",xmlType=" + xmlType + "]");

      byte[] value = null;

      String valueStr = unwrapValueStr(xmlFragment);
      if (valueStr != null)
      {
         value = SimpleTypeBindings.unmarshalHexBinary(valueStr);
      }

      return value;
   }
}