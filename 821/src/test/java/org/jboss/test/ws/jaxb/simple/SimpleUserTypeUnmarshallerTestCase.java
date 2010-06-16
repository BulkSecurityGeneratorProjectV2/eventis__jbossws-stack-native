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
package org.jboss.test.ws.jaxb.simple;

import java.io.ByteArrayInputStream;

import javax.xml.namespace.QName;

import org.apache.xerces.xs.XSModel;
import org.jboss.test.ws.tools.WSToolsTest;
import org.jboss.util.xml.DOMUtils;
import org.jboss.ws.jaxb.JAXBConstants;
import org.jboss.ws.jaxb.JBossXBUnmarshallerImpl;
import org.jboss.ws.jaxb.JAXBUnmarshaller;
import org.jboss.ws.metadata.jaxrpcmapping.JavaWsdlMapping;
import org.jboss.ws.metadata.jaxrpcmapping.PackageMapping;

/**
 * Test the JAXB unmarshalling of a SimpleUserType
 *
 * @author Thomas.Diesler@jboss.org
 * @since 29-Apr-2005
 */
public class SimpleUserTypeUnmarshallerTestCase extends WSToolsTest
{

   private static final String TARGET_NAMESPACE = "http://org.jboss.ws/types";

   public void testGenerateSchema() throws Exception
   {
      QName xmlType = new QName(TARGET_NAMESPACE, "SimpleUserType", "ns1");
      String xsdSchema = generateSchema(xmlType, SimpleUserType.class);

      String exp = "<schema targetNamespace='http://org.jboss.ws/types' " + SCHEMA_NAMESPACES + " xmlns:tns='http://org.jboss.ws/types'>"
            + " <complexType name='SimpleUserType'>" + "  <sequence>" + "   <element name='a' type='int'/>" + "   <element name='b' type='int'/>" + "  </sequence>"
            + " </complexType>" + "</schema>";

      assertEquals(DOMUtils.parse(exp), DOMUtils.parse(xsdSchema));
   }

   public void testUnmarshallSimpleUserType() throws Exception
   {
      QName xmlName = new QName(TARGET_NAMESPACE, "SimpleUser", "ns1");
      QName xmlType = new QName(TARGET_NAMESPACE, "SimpleUserType", "ns1");

      XSModel model = generateSchemaXSModel(xmlType, SimpleUserType.class);

      SimpleUserType obj = null;
      JAXBUnmarshaller unmarshaller = new JBossXBUnmarshallerImpl();
      unmarshaller.setProperty(JAXBConstants.JAXB_XS_MODEL, model);
      unmarshaller.setProperty(JAXBConstants.JAXB_ROOT_QNAME, xmlName);
      unmarshaller.setProperty(JAXBConstants.JAXB_TYPE_QNAME, xmlType);
      unmarshaller.setProperty(JAXBConstants.JAXB_JAVA_MAPPING, getJavaWSDLMapping());

      String xml = "<ns1:SimpleUser xmlns:ns1='" + TARGET_NAMESPACE + "'>" + " <a>0</a>" + " <b>0</b>" + "</ns1:SimpleUser>";

      obj = (SimpleUserType)unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));

      SimpleUserType exp = new SimpleUserType();
      assertNotNull("Resulting object cannot be null", obj);
      assertEquals(exp, obj);
   }

   public void testUnmarshallSimpleUserTypeNoPrefix() throws Exception
   {
      QName xmlName = new QName(TARGET_NAMESPACE, "SimpleUser");
      QName xmlType = new QName(TARGET_NAMESPACE, "SimpleUserType");

      XSModel model = generateSchemaXSModel(xmlType, SimpleUserType.class);

      SimpleUserType obj = null;
      JAXBUnmarshaller unmarshaller = new JBossXBUnmarshallerImpl();
      unmarshaller.setProperty(JAXBConstants.JAXB_XS_MODEL, model);
      unmarshaller.setProperty(JAXBConstants.JAXB_ROOT_QNAME, xmlName);
      unmarshaller.setProperty(JAXBConstants.JAXB_TYPE_QNAME, xmlType);
      unmarshaller.setProperty(JAXBConstants.JAXB_JAVA_MAPPING, getJavaWSDLMapping());

      String xml = "<SimpleUser xmlns='" + TARGET_NAMESPACE + "'>" + " <a xmlns=''>0</a>" + " <b xmlns=''>0</b>" + "</SimpleUser>";

      obj = (SimpleUserType)unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));

      SimpleUserType exp = new SimpleUserType();
      assertNotNull("Resulting object cannot be null", obj);
      assertEquals(exp, obj);
   }

   /**
    * Setup the required jaxrpc-mapping meta data
    */
   private JavaWsdlMapping getJavaWSDLMapping()
   {
      JavaWsdlMapping javaWsdlMapping = new JavaWsdlMapping();
      PackageMapping packageMapping = new PackageMapping(javaWsdlMapping);
      javaWsdlMapping.addPackageMapping(packageMapping);
      packageMapping.setNamespaceURI(TARGET_NAMESPACE);
      packageMapping.setPackageType("org.jboss.test.ws.jaxb.simple");
      return javaWsdlMapping;
   }
}