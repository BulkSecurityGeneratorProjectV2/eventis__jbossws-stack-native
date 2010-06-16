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
// $Id: JSR181MetaDataBuilderJSE.java 387 2006-05-20 14:45:47Z thomas.diesler@jboss.com $
package org.jboss.ws.deployment;

import java.util.Map;

import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.WSException;
import org.jboss.ws.metadata.ServerEndpointMetaData;
import org.jboss.ws.metadata.UnifiedMetaData;
import org.jboss.ws.metadata.j2ee.UnifiedWebMetaData;

/**
 * A server side meta data builder that is based on JSR-181 annotations
 *
 * @author Thomas.Diesler@jboss.org
 * @author <a href="mailto:jason.greene@jboss.com">Jason T. Greene</a>
 * @since 23-Jul-2005
 */
public class JSR181MetaDataBuilderJSE extends JSR181MetaDataBuilder
{
   // provide logging
   private final Logger log = Logger.getLogger(JSR181MetaDataBuilderJSE.class);

   /** Build from annotations
    */
   public UnifiedMetaData buildMetaData(UnifiedDeploymentInfo udi)
   {
      log.debug("START buildMetaData: [name=" + udi.getCanonicalName() + "]");
      try
      {
         UnifiedMetaData wsMetaData = new UnifiedMetaData();
         wsMetaData.setResourceLoader(resourceLoader);
         wsMetaData.setClassLoader(classLoader);

         if (udi.annotationsCl == null)
            throw new WSException("Annotations class loader not initialized");

         // For every bean
         UnifiedWebMetaData webMetaData = (UnifiedWebMetaData)udi.metaData;
         Map<String, String> servletClassMap = webMetaData.getServletClassMap();
         for (String servletName : servletClassMap.keySet())
         {
            String servletClassName = servletClassMap.get(servletName);
            try
            {
               Class beanClass = udi.annotationsCl.loadClass(servletClassName);
               if (beanClass.isAnnotationPresent(WebService.class))
               {
                  setupEndpointFromAnnotations(wsMetaData, udi, beanClass, servletName);
               }
            }
            catch (ClassNotFoundException ex)
            {
               log.warn("Cannot load service endpoint class: " + servletClassName);
            }            
         }

         log.debug("END buildMetaData: " + wsMetaData);
         return wsMetaData;
      }
      catch (RuntimeException rte)
      {
         throw rte;
      }
      catch (Exception ex)
      {
         throw new WSException("Cannot build meta data: " + ex.getMessage(), ex);
      }
   }
}