/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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

// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.jaxrpc.jbws775;

public interface TextTranslator extends java.rmi.Remote {
    public java.lang.String translate(java.lang.String text, java.lang.String sourceLanguage, java.lang.String targetLanguage) throws 
        org.jboss.test.ws.jaxrpc.jbws775.TDictionaryNotAvailable, org.jboss.test.ws.jaxrpc.jbws775.TTextNotTranslatable,  java.rmi.RemoteException;
    public void quoteTranslation(java.lang.String clientName, java.lang.String text, java.lang.String sourceLanguage, java.lang.String targetLanguage) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws775.TQuoteStatus getQuotationStatus(java.lang.String clientName) throws 
         java.rmi.RemoteException;
}