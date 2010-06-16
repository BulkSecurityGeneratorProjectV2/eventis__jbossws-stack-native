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
package org.jboss.ws.jaxrpc;

// $Id$

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.HandlerChain;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.jboss.ws.handler.ClientHandlerChain;

/** 
 * Provides support for the programmatic configuration of
 * handlers in a HandlerRegistry.
 * 
 * A handler chain is registered per service endpoint, as indicated by the
 * qualified name of a port. The getHandlerChain returns the handler chain
 * (as a java.util.List) for the specified service endpoint. The returned
 * handler chain is configured using the java.util.List interface. Each element
 * in this list is required to be of the Java type
 * javax.xml.rpc.handler.HandlerInfo. 
 *
 * @author Thomas.Diesler@jboss.org
 * @since 20-Jul-2005
 */
public class HandlerRegistryImpl implements HandlerRegistry
{
   // Map<QName,HandlerChain> the endpoint name to a HandlerChain
   private Map<QName, HandlerChain> handlerChains = new HashMap<QName, HandlerChain>();
   // Maps the port name to a list of HandlerInfo objects
   private Map<QName, List<HandlerInfo>> handlerInfos = new HashMap<QName, List<HandlerInfo>>();

   public List getHandlerChain(QName portName)
   {
      List<HandlerInfo> list = handlerInfos.get(portName);
      if (list == null)
         list = new ArrayList<HandlerInfo>();
         
      return new ArrayList(list);

   }

   public void setHandlerChain(QName portName, List chain)
   {
      registerClientHandlerChain(portName, chain, null);
   }

   /** Get the handler chain for the given endpoint name, maybe null.
    */
   HandlerChain getHandlerChainInstance(QName portName)
   {
      HandlerChain handlerChain = handlerChains.get(portName);
      return handlerChain;
   }

   /** Register a handler chain for the given endpoint name
    */
   void registerClientHandlerChain(QName portName, List<HandlerInfo> infos, Set roles)
   {
      ClientHandlerChain chain = new ClientHandlerChain(infos, roles);
      handlerChains.put(portName, chain);
      handlerInfos.put(portName, infos);
   }
}