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
package org.jboss.test.ws.jaxws.samples.news;

import java.net.URL;

import junit.framework.Test;

import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * Tests press agency and printer
 * 
 * @author alessio.soldano@jboss.com
 * @since 01-May-2008
 */
public class NewsTestCase extends JBossWSTest
{
   public static Test suite()
   {
      return new JBossWSTestSetup(NewsTestCase.class, "jaxws-samples-news-step1-newspaper.jar");
   }
   
   public void testAgency() throws Exception
   {
      URL wsdlURL = new URL("http://" + getServerHost() + ":8080/news/pressRelease?wsdl");
      Agency agency = new Agency(wsdlURL);
      agency.run("Press release title", "Press release body");
   }
   
   public void testPrinterSwa() throws Exception
   {
      URL wsdlURL = new URL("http://" + getServerHost() + ":8080/news/newspaper/swa?wsdl");
      Printer printer = new Printer(wsdlURL, false);
      printer.run();
   }
   
   public void testPrinterMTOM() throws Exception
   {
      URL wsdlURL = new URL("http://" + getServerHost() + ":8080/news/newspaper/mtom?wsdl");
      Printer printer = new Printer(wsdlURL, true);
      printer.run();
   }
}
