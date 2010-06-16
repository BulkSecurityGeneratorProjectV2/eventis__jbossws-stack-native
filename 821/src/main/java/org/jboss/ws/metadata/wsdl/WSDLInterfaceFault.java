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
package org.jboss.ws.metadata.wsdl;

// $Id$

import java.io.Serializable;

import javax.xml.namespace.QName;

import org.jboss.ws.WSException;

/**
 * An Interface Fault component describes a fault that MAY occur during invocation of an operation of the
 * interface. The Interface Fault component declares an abstract fault by naming it and indicating the
 * contents of the fault message. When and how the fault message flows is indicated by the Interface
 * Operation component 2.4 Interface Operation [p.22] .
 *
 * @author Thomas.Diesler@jboss.org
 * @since 10-Oct-2004
 */
public class WSDLInterfaceFault implements Serializable
{
   private static final long serialVersionUID = 7820459380133521551L;

   // The parent interface.
   private WSDLInterface wsdlInterface;

   /** The REQUIRED name attribute information item identifies a given fault element information item inside a given
    * interface element information item.
    */
   private NCName name;
   /** The OPTIONAL element attribute information item refers, by QName, to an element declaration component.
    */
   private QName xmlName;

   /** Derived XML type identifier. 
    */
   private QName xmlType;
   
   protected WSDLDocumentation documentationElement = null;

   public WSDLInterfaceFault(WSDLInterface wsdlInterface)
   {
      this.wsdlInterface = wsdlInterface;
   }

   public WSDLInterface getWsdlInterface()
   {
      return wsdlInterface;
   }

   public NCName getName()
   {
      return name;
   }

   public void setName(NCName name)
   {
      this.name = name;
   }

   public QName getXmlName()
   {
      return xmlName;
   }

   public void setXmlName(QName qname)
   {
      this.xmlName = qname;
   }

   public WSDLDocumentation getDocumentation()
   {
      return this.documentationElement;
   }

   public void setDocumentation(WSDLDocumentation doc)
   {
      this.documentationElement = doc;
   }

   /** Get the xmlType for this interface fault.
    */
   public QName getXmlType()
   {
      if (xmlType == null && xmlName != null)
      {
         WSDLDefinitions wsdlDefinitions = wsdlInterface.getWsdlDefinitions();
         WSDLTypes wsdlTypes = wsdlDefinitions.getWsdlTypes();
         xmlType = wsdlTypes.getXMLType(xmlName);
      }

      if (xmlType == null)
         throw new WSException("Cannot obtain xmlType for fault: " + xmlName);

      return xmlType;
   }

   public void setXmlType(QName xmlType)
   {
      this.xmlType = xmlType;
   }
}