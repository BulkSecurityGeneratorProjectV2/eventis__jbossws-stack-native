// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.ws.jaxrpc.jbws663;

public interface SMSTextMessagingSoapBareBound extends java.rmi.Remote {
    public org.jboss.test.ws.jaxrpc.jbws663.SendMessageResponse sendMessage(org.jboss.test.ws.jaxrpc.jbws663.SendMessage parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.ResponseInfoHolder responseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.SendMessagesBulkResponse sendMessagesBulk(org.jboss.test.ws.jaxrpc.jbws663.SendMessagesBulk parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.ResponseInfoHolder responseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.TrackMessageResponse trackMessage(org.jboss.test.ws.jaxrpc.jbws663.TrackMessage parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.ResponseInfoHolder responseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.TrackMessagesBulkResponse trackMessagesBulk(org.jboss.test.ws.jaxrpc.jbws663.TrackMessagesBulk parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.ResponseInfoHolder responseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.GetSupportedCarriersResponse getSupportedCarriers(org.jboss.test.ws.jaxrpc.jbws663.GetSupportedCarriers parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.GetCountryCodesResponse getCountryCodes(org.jboss.test.ws.jaxrpc.jbws663.GetCountryCodes parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
    public org.jboss.test.ws.jaxrpc.jbws663.GetRemainingHitsResponse getRemainingHits(org.jboss.test.ws.jaxrpc.jbws663.GetRemainingHits parameters, org.jboss.test.ws.jaxrpc.jbws663.LicenseInfo licenseInfo, org.jboss.test.ws.jaxrpc.jbws663.holders.SubscriptionInfoHolder subscriptionInfo) throws 
         java.rmi.RemoteException;
}