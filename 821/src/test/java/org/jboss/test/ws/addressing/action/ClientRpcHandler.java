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
package org.jboss.test.ws.addressing.action;

import java.net.URISyntaxException;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.ws.addressing.AddressingBuilder;
import javax.xml.ws.addressing.AddressingProperties;
import javax.xml.ws.addressing.JAXWSAConstants;

import org.jboss.logging.Logger;

/**
 * A client side handler for the ws-addressing
 *
 * @author Thomas.Diesler@jboss.org
 * @since 29-Nov-2005
 */
public class ClientRpcHandler extends GenericHandler
{
   // Provide logging
   private static Logger log = Logger.getLogger(ClientRpcHandler.class);
   
   public QName[] getHeaders()
   {
      return new QName[] {};
   }

   public boolean handleRequest(MessageContext msgContext)
   {
      log.info("handleRequest" + this);
      try
      {
         AddressingBuilder builder = AddressingBuilder.getAddressingBuilder();
         AddressingProperties outProps = builder.newAddressingProperties();
         outProps.setTo(builder.newURI("http://localhost:8080/jbossws-addressing-action-rpc/ActionService"));
         outProps.setAction(builder.newURI("urn:wsa-action-bar"));
         msgContext.setProperty(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES_OUTBOUND, outProps);
      }
      catch (URISyntaxException ex)
      {
         throw new IllegalStateException("Cannot handle request", ex);
      }
      return true;
   }
}