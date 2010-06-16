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
package org.jboss.ws.wsse;

// $Id$

import javax.xml.rpc.handler.MessageContext;

/**
 * A JAXRPC handler that delegates to the WSSecurityDispatcher
 *
 * @author Thomas.Diesler@jboss.org
 * @since 12-Nov-2005
 */
public class WSSecurityHandlerOutbound extends WSSecurityHandler
{
   public boolean handleRequest(MessageContext msgContext)
   {
      return handleOutboundSecurity(msgContext);
   }

   public boolean handleResponse(MessageContext msgContext)
   {
      return handleInboundSecurity(msgContext);
   }

   /* Their is a potential problem that can't be avoided using the JAX-RPC handler framework.
    * If a request handler (outbound for the client) throws an exception, this will get called,
    * but it will be incorrectly treated as an inbound message.
    *
    * This is intended to be called when the response message from the server (inbound)
    * is a fault message.
    */
   public boolean handleFault(MessageContext msgContext)
   {
      if (thrownByMe(msgContext))
         return true;

      return handleInboundSecurity(msgContext);
   }
}