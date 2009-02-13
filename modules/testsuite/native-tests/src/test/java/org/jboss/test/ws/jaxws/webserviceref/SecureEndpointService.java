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
package org.jboss.test.ws.jaxws.webserviceref;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebServiceClient(name = "SecureEndpointService", targetNamespace = "http://org.jboss.ws/wsref", wsdlLocation = "http://localhost.localdomain:8080/jaxws-webserviceref-secure/SecureEndpoint?wsdl")
public class SecureEndpointService
    extends Service
{

    private final static URL WSDL_LOCATION;
    private final static QName SECUREENDPOINTSERVICE = new QName("http://org.jboss.ws/wsref", "SecureEndpointService");
    private final static QName SECUREENDPOINTPORT = new QName("http://org.jboss.ws/wsref", "SecureEndpointPort");

    static {
        URL url = null;
        try {
            url = new URL("http://localhost.localdomain:8080/jaxws-webserviceref-secure/SecureEndpoint?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SecureEndpointService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SecureEndpointService() {
        super(WSDL_LOCATION, SECUREENDPOINTSERVICE);
    }

    /**
     * 
     * @return
     *     returns SecureEndpoint
     */
    @WebEndpoint(name = "SecureEndpointPort")
    public SecureEndpoint getSecureEndpointPort() {
        return (SecureEndpoint)super.getPort(SECUREENDPOINTPORT, SecureEndpoint.class);
    }

}