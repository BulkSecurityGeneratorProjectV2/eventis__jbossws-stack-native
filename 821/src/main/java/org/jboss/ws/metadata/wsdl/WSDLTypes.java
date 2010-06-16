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

//$Id$

import java.io.Serializable;

import javax.xml.namespace.QName;

import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSTypeDefinition;
import org.jboss.logging.Logger;
import org.jboss.ws.WSException;
import org.jboss.ws.metadata.wsdl.xmlschema.JBossXSModel;

/**
 * WSDL types.
 *
 * @author Thomas.Diesler@jboss.org
 * @author Anil.Saldhana@jboss.org
 * @since 10-Oct-2004
 */
public class WSDLTypes implements Serializable
{
   private static final long serialVersionUID = 7919937323521372194L;

   // provide logging
   private static final Logger log = Logger.getLogger(WSDLTypes.class);

   private WSDLDefinitions wsdlDefinitions;

   // The Schema Model
   private JBossXSModel schemaModel;

   public WSDLTypes(WSDLDefinitions wsdlDefinitions)
   {
      this.wsdlDefinitions = wsdlDefinitions;
      this.schemaModel = new JBossXSModel();
   }

   public WSDLDefinitions getWsdlDefinitions()
   {
      return wsdlDefinitions;
   }

   /**
    * Add a schema model for a given namespaceURI
    * @param nsURI the namespaceURI under which the model has been generated
    * @param schema the Schema Model that needs to be added to existing schema
    *               model in WSDLTypes
    * <dt>Warning:</dd>
    * <p>Passing a null nsURI will replace the internal schema model
    * held by WSDLTypes by the model passed as an argument.</p>
    */
   public void addSchemaModel(String nsURI, JBossXSModel schema)
   {
      if(nsURI == null)
      {
         log.debug("nsURI passed to addSchemaModel is null. Replacing Schema Model");
         schemaModel = schema;
      }
      else
          schemaModel.merge(schema);
   }

   /**
    * Return the global Schema Model
    * @return
    */
   public JBossXSModel getSchemaModel()
   {
      return schemaModel;
   }

   /** Get the xmlType from a given element xmlName
    */
   public QName getXMLType(QName xmlName)
   {
      QName xmlType = null;
      String nsURI = xmlName.getNamespaceURI();
      String localPart = xmlName.getLocalPart();
      XSElementDeclaration xsel = schemaModel.getElementDeclaration(localPart, nsURI);
      if (xsel != null)
      {
         XSTypeDefinition xstype = xsel.getTypeDefinition();
         if (xstype == null)
            throw new WSException("Cannot obtain XSTypeDefinition for: " + xmlName);

         if (xstype.getAnonymous() == false)
         {
            xmlType = new QName(xstype.getNamespace(), xstype.getName());
         }
         else
         {
            xmlType = new QName(xstype.getNamespace(), ">" + localPart);
         }
      }
      return xmlType;
   }

   public String toString()
   {
      StringBuilder buffer = new StringBuilder("WSDLTypes:\n");
      buffer.append(schemaModel != null ? schemaModel.serialize() : "<schema/>");
      return buffer.toString();
   }
}