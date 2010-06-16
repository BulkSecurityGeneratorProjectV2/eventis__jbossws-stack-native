// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.interop.microsoft.addressing.wsa10;

import com.sun.xml.rpc.server.http.MessageContextProperties;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.soap.streaming.*;
import com.sun.xml.rpc.soap.message.*;
import com.sun.xml.rpc.soap.SOAPVersion;
import com.sun.xml.rpc.soap.SOAPEncodingConstants;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.lang.reflect.*;
import java.lang.Class;
import com.sun.xml.rpc.client.SenderException;
import com.sun.xml.rpc.client.*;
import com.sun.xml.rpc.client.http.*;
import javax.xml.rpc.handler.*;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPFaultException;

public class Echo2_Stub
    extends com.sun.xml.rpc.client.StubBase
    implements org.jboss.test.ws.interop.microsoft.addressing.wsa10.Echo2 {
    
    
    
    /*
     *  public constructor
     */
    public Echo2_Stub(HandlerChain handlerChain) {
        super(handlerChain);
        _setProperty(ENDPOINT_ADDRESS_PROPERTY, "http://131.107.72.15/WSAddressingCR_Service_WCF/WSAddressing10.svc/Soap11/Manual");
    }
    
    
    /*
     *  implementation of echo
     */
    public java.lang.String echo(java.lang.String echoIn)
        throws java.rmi.RemoteException {
        
        try {
            
            StreamingSenderState _state = _start(_handlerChain);
            
            InternalSOAPMessage _request = _state.getRequest();
            _request.setOperationCode(Echo_OPCODE);
            
            
            SOAPBlockInfo _bodyBlock = new SOAPBlockInfo(ns3_Echo_echoIn_QNAME);
            _bodyBlock.setValue(echoIn);
            _bodyBlock.setSerializer(ns2_myns2_string__java_lang_String_String_Serializer);
            _request.setBody(_bodyBlock);
            
            _state.getMessageContext().setProperty(HttpClientTransport.HTTP_SOAPACTION_PROPERTY, "http://example.org/action/echoIn");
            
            _send((java.lang.String) _getProperty(ENDPOINT_ADDRESS_PROPERTY), _state);
            
            java.lang.String _result = null;
            java.lang.Object _responseObj = _state.getResponse().getBody().getValue();
            if (_responseObj instanceof SOAPDeserializationState) {
                _result = (java.lang.String)((SOAPDeserializationState) _responseObj).getInstance();
            } else {
                _result = (java.lang.String)_responseObj;
            }
            
            return _result;
            
        } catch (RemoteException e) {
            // let this one through unchanged
            throw e;
        } catch (JAXRPCException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RemoteException(e.getMessage(), e);
            }
        }
    }
    
    
    /*
     *  this method deserializes the request/response structure in the body
     */
    protected void _readFirstBodyElement(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState  state) throws Exception {
        int opcode = state.getRequest().getOperationCode();
        switch (opcode) {
            case Echo_OPCODE:
                _deserialize_Echo(bodyReader, deserializationContext, state);
                break;
            default:
                throw new SenderException("sender.response.unrecognizedOperation", java.lang.Integer.toString(opcode));
        }
    }
    
    
    
    /*
     * This method deserializes the body of the Echo operation.
     */
    private void _deserialize_Echo(XMLReader bodyReader, SOAPDeserializationContext deserializationContext, StreamingSenderState state) throws Exception {
        java.lang.Object myStringObj =
            ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns3_Echo_echoOut_QNAME,
                bodyReader, deserializationContext);
        
        SOAPBlockInfo bodyBlock = new SOAPBlockInfo(ns3_Echo_echoOut_QNAME);
        bodyBlock.setValue(myStringObj);
        state.getResponse().setBody(bodyBlock);
    }
    
    
    
    protected java.lang.String _getDefaultEnvelopeEncodingStyle() {
        return null;
    }
    
    public java.lang.String _getImplicitEnvelopeEncodingStyle() {
        return "";
    }
    
    public java.lang.String _getEncodingStyle() {
        return SOAPNamespaceConstants.ENCODING;
    }
    
    public void _setEncodingStyle(java.lang.String encodingStyle) {
        throw new UnsupportedOperationException("cannot set encoding style");
    }
    
    
    
    
    
    /*
     * This method returns an array containing (prefix, nsURI) pairs.
     */
    protected java.lang.String[] _getNamespaceDeclarations() {
        return myNamespace_declarations;
    }
    
    /*
     * This method returns an array containing the names of the headers we understand.
     */
    public javax.xml.namespace.QName[] _getUnderstoodHeaders() {
        return understoodHeaderNames;
    }
    
    public void _initialize(InternalTypeMappingRegistry registry) throws Exception {
        super._initialize(registry);
        ns2_myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns2_string_TYPE_QNAME);
    }
    
    private static final javax.xml.namespace.QName _portName = new QName("http://tempuri.org/", "CustomBinding_Echo2");
    private static final int Echo_OPCODE = 0;
    private static final javax.xml.namespace.QName ns3_Echo_echoIn_QNAME = new QName("http://example.org/echo", "echoIn");
    private static final javax.xml.namespace.QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
    private static final javax.xml.namespace.QName ns3_Echo_echoOut_QNAME = new QName("http://example.org/echo", "echoOut");
    private static final java.lang.String[] myNamespace_declarations =
                                        new java.lang.String[] {
                                            
                                        };
    
    private static final QName[] understoodHeaderNames = new QName[] {  };
}