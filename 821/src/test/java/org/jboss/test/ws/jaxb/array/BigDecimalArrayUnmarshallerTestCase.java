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
package org.jboss.test.ws.jaxb.array;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

import javax.xml.namespace.QName;

import org.apache.xerces.xs.XSModel;
import org.jboss.test.ws.tools.WSToolsTest;
import org.jboss.util.xml.DOMUtils;
import org.jboss.ws.jaxb.JAXBConstants;
import org.jboss.ws.jaxb.JBossXBUnmarshallerImpl;
import org.jboss.ws.jaxb.JAXBUnmarshaller;
import org.jboss.ws.metadata.jaxrpcmapping.JavaWsdlMapping;

/**
 * Test the JAXB marshalling of array types
 *
 * @author Thomas.Diesler@jboss.org
 * @since 29-Apr-2005
 */
public class BigDecimalArrayUnmarshallerTestCase extends WSToolsTest
{

   private static final String TARGET_NAMESPACE = "http://org.jboss.ws/types";

   private static final QName xmlName = new QName("result");

   private static final QName xmlType = new QName(TARGET_NAMESPACE, "BigDecimalArray", "ns1");

   public void testGenerateSchema() throws Exception
   {
      String xsdSchema = generateSchema(xmlType, BigDecimal[].class);

      String exp = 
         "<schema targetNamespace='http://org.jboss.ws/types' " + SCHEMA_NAMESPACES + " xmlns:tns='http://org.jboss.ws/types'>" + 
         " <complexType name='BigDecimalArray'>" + 
         "  <sequence>" + 
         "   <element name='value' type='decimal' nillable='true' minOccurs='0' maxOccurs='unbounded'/>" + 
         "  </sequence>" + 
         " </complexType>" + 
         "</schema>";

      assertEquals(DOMUtils.parse(exp), DOMUtils.parse(xsdSchema));
   }

   public void testUnmarshallArrayType() throws Exception
   {
      XSModel model = generateSchemaXSModel(xmlType, BigDecimal[].class);

      BigDecimal[] objArr = null;
      // Get the JAXB marshaller
      JAXBUnmarshaller unmarshaller = new JBossXBUnmarshallerImpl();
      unmarshaller.setProperty(JAXBConstants.JAXB_XS_MODEL, model);
      unmarshaller.setProperty(JAXBConstants.JAXB_ROOT_QNAME, xmlName);
      unmarshaller.setProperty(JAXBConstants.JAXB_TYPE_QNAME, xmlType);
      unmarshaller.setProperty(JAXBConstants.JAXB_JAVA_MAPPING, getJavaWSDLMapping());

      String xml = "<result>" + " <value>100</value>" + " <value>200</value>" + " <value>300</value>" + "</result>";

      objArr = (BigDecimal[]) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));

      assertNotNull("Resulting obj array cannot be null", objArr);

      assertEquals("Unexpected array length", 3, objArr.length);
      assertEquals(new BigDecimal(100), objArr[0]);
      assertEquals(new BigDecimal(200), objArr[1]);
      assertEquals(new BigDecimal(300), objArr[2]);
   }

   /**
    * Setup the required jaxrpc-mapping meta data
    */
   private JavaWsdlMapping getJavaWSDLMapping()
   {
      JavaWsdlMapping javaWsdlMapping = new JavaWsdlMapping();
      /*
       * This mapping should be optional JavaXmlTypeMapping xmlTypeMapping = new
       * JavaXmlTypeMapping(javaWsdlMapping);
       * xmlTypeMapping.setJavaType("java.math.BigDecimal[]");
       * xmlTypeMapping.setRootTypeQName(xmlType);
       * xmlTypeMapping.setQnameScope("complexType");
       * javaWsdlMapping.addJavaXmlTypeMappings(xmlTypeMapping);
       */
      return javaWsdlMapping;
   }
}