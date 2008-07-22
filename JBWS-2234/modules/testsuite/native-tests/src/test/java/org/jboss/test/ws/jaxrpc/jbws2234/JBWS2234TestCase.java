/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, JBoss Inc., and individual contributors as indicated
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

package org.jboss.test.ws.jaxrpc.jbws2234;

import javax.naming.InitialContext;
import javax.xml.rpc.Service;

import junit.framework.Test;

import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * @author darran.lofthouse@jboss.com
 * @since June 21, 2008
 */
public class JBWS2234TestCase extends JBossWSTest
{
   private static TestEndpoint port;

   public static Test suite() throws Exception
   {
      return new JBossWSTestSetup(JBWS2234TestCase.class, "jaxrpc-jbws2234.war, jaxrpc-jbws2234-client.jar");
   }

   public void setUp() throws Exception
   {
      super.setUp();
      if (port == null)
      {
         InitialContext iniCtx = getInitialContext();
         Service service = (Service)iniCtx.lookup("java:comp/env/service/TestService");
         port = (TestEndpoint)service.getPort(TestEndpoint.class);
      }
   }

   public void testCall() throws Exception
   {
      final String message = "Hello!!";
      String response = port.echo(message);

      assertEquals(message, response);
   }

   public void testCheckedException() throws Exception
   {
      try
      {
         port.echo(TestEndpointImpl.TEST_EXCEPTION);
         fail("Excpected TestException not thrown.");
      }
      catch (TestException te)
      {
      }
   }

   public void testRuntimeException()
   {
      try
      {
         port.echo(TestEndpointImpl.RUNTIME_EXCEPTION);
         fail("Excpected Exception not thrown.");
      }
      catch (Exception e)
      {
         assertEquals(TestEndpointImpl.RUNTIME_EXCEPTION, e.getMessage());
      }
   }
}
