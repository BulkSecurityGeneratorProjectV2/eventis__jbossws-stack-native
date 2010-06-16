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
package org.jboss.ws.eventing.element;

// $Id$

import org.w3c.dom.Element;
import org.jboss.ws.addressing.EndpointReferenceImpl;
import org.jboss.ws.soap.SOAPFactoryImpl;
import org.jboss.ws.eventing.EventingConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPException;
import java.net.URI;

/**
 * Simplified endpoint representation.
 *
 * @author Heiko Braun, <heiko@openj.net>
 * @since 29-Nov-2005
 */
public class EndpointReference
{
   private URI address;
   private ReferenceParameters referenceParams;
   private MetaData metadata;

   public EndpointReference()
   {
   }

   public URI getAddress()
   {
      return address;
   }

   public void setAddress(URI address)
   {
      this.address = address;
   }

   public ReferenceParameters getReferenceParams()
   {
      return referenceParams;
   }

   public void setReferenceParams(ReferenceParameters referenceParams)
   {
      this.referenceParams = referenceParams;
   }

   public MetaData getMetadata()
   {
      return metadata;
   }

   public void setMetadata(MetaData metadata)
   {
      this.metadata = metadata;
   }

   public EndpointReferenceImpl toWsaEndpointReference() {

      try
      {
         EndpointReferenceImpl epr = new EndpointReferenceImpl(this.address);
         SOAPFactoryImpl factory = (SOAPFactoryImpl) SOAPFactory.newInstance();
         SOAPElement idEl = factory.createElement("Identifier", "wse", EventingConstants.NS_EVENTING);
         idEl.setValue(this.referenceParams.getIdentifier().toString());
         epr.getReferenceParameters().addElement(idEl);
         return epr;
      }
      catch (SOAPException e)
      {
         throw new RuntimeException(e);
      }
   }
}