// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.client.ServiceExceptionImpl;
import com.sun.xml.rpc.util.exception.*;
import com.sun.xml.rpc.soap.SOAPVersion;
import com.sun.xml.rpc.client.HandlerChainImpl;
import javax.xml.rpc.*;
import javax.xml.rpc.encoding.*;
import javax.xml.rpc.handler.HandlerChain;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.namespace.QName;

public class ComplexDataTypesDocLitWService_Impl extends com.sun.xml.rpc.client.BasicService implements ComplexDataTypesDocLitWService {
    private static final QName serviceName = new QName("http://tempuri.org/", "ComplexDataTypesDocLitWService");
    private static final QName ns1_BasicHttpBinding_IComplexDataTypesDocLitW_QNAME = new QName("http://tempuri.org/", "BasicHttpBinding_IComplexDataTypesDocLitW");
    private static final Class IComplexDataTypesDocLitW_PortClass = org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.IComplexDataTypesDocLitW.class;
    
    public ComplexDataTypesDocLitWService_Impl() {
        super(serviceName, new QName[] {
                        ns1_BasicHttpBinding_IComplexDataTypesDocLitW_QNAME
                    },
            new org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.ComplexDataTypesDocLitWService_SerializerRegistry().getRegistry());
        
    }
    
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, java.lang.Class serviceDefInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (portName.equals(ns1_BasicHttpBinding_IComplexDataTypesDocLitW_QNAME) &&
                serviceDefInterface.equals(IComplexDataTypesDocLitW_PortClass)) {
                return getBasicHttpBinding_IComplexDataTypesDocLitW();
            }
        } catch (Exception e) {
            throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
        }
        return super.getPort(portName, serviceDefInterface);
    }
    
    public java.rmi.Remote getPort(java.lang.Class serviceDefInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (serviceDefInterface.equals(IComplexDataTypesDocLitW_PortClass)) {
                return getBasicHttpBinding_IComplexDataTypesDocLitW();
            }
        } catch (Exception e) {
            throw new ServiceExceptionImpl(new LocalizableExceptionAdapter(e));
        }
        return super.getPort(serviceDefInterface);
    }
    
    public org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.IComplexDataTypesDocLitW getBasicHttpBinding_IComplexDataTypesDocLitW() {
        java.lang.String[] roles = new java.lang.String[] {};
        HandlerChainImpl handlerChain = new HandlerChainImpl(getHandlerRegistry().getHandlerChain(ns1_BasicHttpBinding_IComplexDataTypesDocLitW_QNAME));
        handlerChain.setRoles(roles);
        org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.IComplexDataTypesDocLitW_Stub stub = new org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.IComplexDataTypesDocLitW_Stub(handlerChain);
        try {
            stub._initialize(super.internalTypeRegistry);
        } catch (JAXRPCException e) {
            throw e;
        } catch (Exception e) {
            throw new JAXRPCException(e.getMessage(), e);
        }
        return stub;
    }
}