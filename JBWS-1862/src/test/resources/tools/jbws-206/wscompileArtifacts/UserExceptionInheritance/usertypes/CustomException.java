// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.webservice.userexceptioninheritance;


public class CustomException extends java.lang.Exception {
    private java.lang.String message;
    private int id;
    
    
    public CustomException(java.lang.String message, int id) {
        super(message);
        this.message = message;
        this.id = id;
    }
    
    public java.lang.String getMessage() {
        return message;
    }
    
    public int getId() {
        return id;
    }
}
