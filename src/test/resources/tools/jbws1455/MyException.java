// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.jbws1455;


public class MyException extends java.lang.Exception {
    private java.lang.String comment;
    
    
    public MyException(java.lang.String comment) {
        super(comment);
        this.comment = comment;
    }
    
    public java.lang.String getComment() {
        return comment;
    }
}