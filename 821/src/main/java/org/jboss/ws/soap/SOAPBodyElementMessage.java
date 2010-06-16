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

import org.jboss.util.xml.DOMWriter;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBodyElement;
import java.io.Writer;

/**
 * An abstract implemenation of the SOAPBodyElement
 * <p/>
 * This class should not expose functionality that is not part of
 * {@link javax.xml.soap.SOAPBodyElement}. Client code should use <code>SOAPBodyElement</code>.
 *
 * @author Thomas.Diesler@jboss.org
 */
public class SOAPBodyElementMessage extends SOAPElementImpl implements SOAPBodyElement
{
   public SOAPBodyElementMessage(Name name)
   {
      super(name);
   }

   public SOAPBodyElementMessage(SOAPElementImpl element)
   {
      super(element);
   }
   public String write(Writer writer, boolean pretty) {
      DOMWriter domWriter = new DOMWriter(writer);
      domWriter.setPrettyprint(pretty);
      domWriter.print(this);

      return null;
   }
}