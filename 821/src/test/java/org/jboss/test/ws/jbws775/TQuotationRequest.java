// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.jbws775;


public class TQuotationRequest {
    protected java.lang.String clientName;
    protected java.lang.String targetLanguage;
    protected org.jboss.test.ws.jbws775.TDocument document;
    
    public TQuotationRequest() {
    }
    
    public TQuotationRequest(java.lang.String clientName, java.lang.String targetLanguage, org.jboss.test.ws.jbws775.TDocument document) {
        this.clientName = clientName;
        this.targetLanguage = targetLanguage;
        this.document = document;
    }
    
    public java.lang.String getClientName() {
        return clientName;
    }
    
    public void setClientName(java.lang.String clientName) {
        this.clientName = clientName;
    }
    
    public java.lang.String getTargetLanguage() {
        return targetLanguage;
    }
    
    public void setTargetLanguage(java.lang.String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
    
    public org.jboss.test.ws.jbws775.TDocument getDocument() {
        return document;
    }
    
    public void setDocument(org.jboss.test.ws.jbws775.TDocument document) {
        this.document = document;
    }
}