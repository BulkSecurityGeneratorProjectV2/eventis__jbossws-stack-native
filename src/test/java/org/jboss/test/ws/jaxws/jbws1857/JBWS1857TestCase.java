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
package org.jboss.test.ws.jaxws.jbws1857;

import java.io.File;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.jboss.test.ws.jaxws.jbws1857.types.Stammdaten;
import org.jboss.wsf.test.JBossWSTest;

/**
 * [JBWS-1857] JAXBContext created for every invokation of service.getPort()
 * 
 * http://jira.jboss.org/jira/browse/JBWS-1857
 *
 * @author Thomas.Diesler@jboss.com
 * @since 28-Feb-2008
 */
public class JBWS1857TestCase extends JBossWSTest
{

   public void testPortCreation() throws Exception
   {
      File wsdlFile = new File("resources/jaxws/jbws1857/StammdatenService.wsdl");
      assertTrue(wsdlFile.exists());

      QName serviceName = new QName("http://example.com", "StammdatenService");
      Service service = Service.create(wsdlFile.toURL(), serviceName);
      
      long start = System.currentTimeMillis();
      Stammdaten port = service.getPort(Stammdaten.class);
      long time = start - System.currentTimeMillis();
      
      assertTrue("Creation of the port took too long", time < 20000);
   }
}
