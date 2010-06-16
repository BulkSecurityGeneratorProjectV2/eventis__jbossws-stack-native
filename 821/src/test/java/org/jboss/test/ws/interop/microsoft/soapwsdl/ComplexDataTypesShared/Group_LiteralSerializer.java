// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.xsd.XSDConstants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.encoding.literal.DetailFragmentDeserializer;
import com.sun.xml.rpc.encoding.simpletype.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;

public class Group_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable  {
    private static final javax.xml.namespace.QName ns5_members_QNAME = new QName("http://schemas.datacontract.org/2004/07/XwsInterop.SoapWsdl.ComplexDataTypes.XmlFormatter.Service.Indigo", "members");
    private static final javax.xml.namespace.QName ns5_ArrayOfPerson_TYPE_QNAME = new QName("http://schemas.datacontract.org/2004/07/XwsInterop.SoapWsdl.ComplexDataTypes.XmlFormatter.Service.Indigo", "ArrayOfPerson");
    private CombinedSerializer ns5_myArrayOfPerson_LiteralSerializer;
    private static final javax.xml.namespace.QName ns5_name_QNAME = new QName("http://schemas.datacontract.org/2004/07/XwsInterop.SoapWsdl.ComplexDataTypes.XmlFormatter.Service.Indigo", "name");
    private static final javax.xml.namespace.QName ns2_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns2_myns2_string__java_lang_String_String_Serializer;
    
    public Group_LiteralSerializer(javax.xml.namespace.QName type, java.lang.String encodingStyle) {
        this(type, encodingStyle, false);
    }
    
    public Group_LiteralSerializer(javax.xml.namespace.QName type, java.lang.String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns5_myArrayOfPerson_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.ArrayOfPerson.class, ns5_ArrayOfPerson_TYPE_QNAME);
        ns2_myns2_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns2_string_TYPE_QNAME);
    }
    
    public java.lang.Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws java.lang.Exception {
        org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group instance = new org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group();
        java.lang.Object member=null;
        javax.xml.namespace.QName elementName;
        java.util.List values;
        java.lang.Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns5_members_QNAME)) {
                member = ns5_myArrayOfPerson_LiteralSerializer.deserialize(ns5_members_QNAME, reader, context);
                instance.setMembers((org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.ArrayOfPerson)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns5_name_QNAME)) {
                member = ns2_myns2_string__java_lang_String_String_Serializer.deserialize(ns5_name_QNAME, reader, context);
                instance.setName((java.lang.String)member);
                reader.nextElementContent();
            }
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (java.lang.Object)instance;
    }
    
    public void doSerializeAttributes(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group instance = (org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group)obj;
        
    }
    public void doSerialize(java.lang.Object obj, XMLWriter writer, SOAPSerializationContext context) throws java.lang.Exception {
        org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group instance = (org.jboss.test.ws.interop.microsoft.soapwsdl.ComplexDataTypesShared.Group)obj;
        
        ns5_myArrayOfPerson_LiteralSerializer.serialize(instance.getMembers(), ns5_members_QNAME, null, writer, context);
        ns2_myns2_string__java_lang_String_String_Serializer.serialize(instance.getName(), ns5_name_QNAME, null, writer, context);
    }
}