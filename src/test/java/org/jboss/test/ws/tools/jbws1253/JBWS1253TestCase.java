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
package org.jboss.test.ws.tools.jbws1253;

import java.io.File;

import org.jboss.test.ws.tools.fixture.JBossSourceComparator;
import org.jboss.test.ws.tools.validation.JaxrpcMappingValidator;
import org.jboss.ws.tools.WSTools;
import org.jboss.wsf.test.JBossWSTest;

/**
 * Test for a port name which ends 'PortType'
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 * @since 27th September 2006
 */
public class JBWS1253TestCase extends JBossWSTest
{
   public final void testPortTypePort() throws Exception
   {
      String resourceDir = "resources/tools/jbws1253";
      String toolsDir = "tools/jbws1253";
      String[] args = new String[] { "-dest", toolsDir, "-config", resourceDir + "/wstools-config.xml" };
      new WSTools().generate(args);

      JaxrpcMappingValidator mappingValidator = new JaxrpcMappingValidator();
      mappingValidator.validate(resourceDir + "/myporttype-mapping.xml", toolsDir + "/myporttype-mapping.xml");

      File expSEI = new File(resourceDir + "/My.java");
      File wasSEI = new File(toolsDir + "/org/jboss/test/ws/jbws1253/My.java");

      JBossSourceComparator sc = new JBossSourceComparator(expSEI, wasSEI);
      sc.validate();
      sc.validateImports();

      File expService = new File(resourceDir + "/TestSEIService.java");
      File wasService = new File(toolsDir + "/org/jboss/test/ws/jbws1253/TestSEIService.java");

      sc = new JBossSourceComparator(expService, wasService);
      sc.validate();
      sc.validateImports();
   }
}