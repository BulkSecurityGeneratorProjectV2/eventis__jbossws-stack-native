
package org.jboss.test.ws.jaxws.jbws1857.types;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1-b03-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "Stammdaten", targetNamespace = "http://example.com")
public interface Stammdaten {


    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.GlaeubigerResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteGlaeubiger", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteGlaeubiger")
    @ResponseWrapper(localName = "deleteGlaeubigerResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteGlaeubigerResponse")
    public GlaeubigerResult deleteGlaeubiger(
        @WebParam(name = "arg0", targetNamespace = "")
        Glaeubiger arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteMandant", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteMandant")
    @ResponseWrapper(localName = "deleteMandantResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteMandantResponse")
    public MandantResult deleteMandant(
        @WebParam(name = "arg0", targetNamespace = "")
        Mandant arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantUserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteMandantUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteMandantUser")
    @ResponseWrapper(localName = "deleteMandantUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteMandantUserResponse")
    public MandantUserResult deleteMandantUser(
        @WebParam(name = "arg0", targetNamespace = "")
        MandantUser arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.NatPersonResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteNatPerson", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteNatPerson")
    @ResponseWrapper(localName = "deleteNatPersonResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteNatPersonResponse")
    public NatPersonResult deleteNatPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        NatuerlichePerson arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.UserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteUser")
    @ResponseWrapper(localName = "deleteUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.DeleteUserResponse")
    public UserResult deleteUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.GlaeubigerArrayResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchGlaeubiger", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchGlaeubiger")
    @ResponseWrapper(localName = "fetchGlaeubigerResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchGlaeubigerResponse")
    public GlaeubigerArrayResult fetchGlaeubiger(
        @WebParam(name = "arg0", targetNamespace = "")
        Glaeubiger arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantArrayResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchMandant", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchMandant")
    @ResponseWrapper(localName = "fetchMandantResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchMandantResponse")
    public MandantArrayResult fetchMandant(
        @WebParam(name = "arg0", targetNamespace = "")
        Mandant arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantUserArrayResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchMandantUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchMandantUser")
    @ResponseWrapper(localName = "fetchMandantUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchMandantUserResponse")
    public MandantUserArrayResult fetchMandantUser(
        @WebParam(name = "arg0", targetNamespace = "")
        MandantUser arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.NatPersonResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchNatPerson", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchNatPerson")
    @ResponseWrapper(localName = "fetchNatPersonResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchNatPersonResponse")
    public NatPersonResult fetchNatPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        NatuerlichePerson arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.UserArrayResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "fetchUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchUser")
    @ResponseWrapper(localName = "fetchUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.FetchUserResponse")
    public UserArrayResult fetchUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.GlaeubigerResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertGlaeubiger", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertGlaeubiger")
    @ResponseWrapper(localName = "insertGlaeubigerResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertGlaeubigerResponse")
    public GlaeubigerResult insertGlaeubiger(
        @WebParam(name = "arg0", targetNamespace = "")
        Glaeubiger arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertMandant", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertMandant")
    @ResponseWrapper(localName = "insertMandantResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertMandantResponse")
    public MandantResult insertMandant(
        @WebParam(name = "arg0", targetNamespace = "")
        Mandant arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantUserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertMandantUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertMandantUser")
    @ResponseWrapper(localName = "insertMandantUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertMandantUserResponse")
    public MandantUserResult insertMandantUser(
        @WebParam(name = "arg0", targetNamespace = "")
        MandantUser arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.NatPersonResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertNatPerson", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertNatPerson")
    @ResponseWrapper(localName = "insertNatPersonResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertNatPersonResponse")
    public NatPersonResult insertNatPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        NatuerlichePerson arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.UserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertUser")
    @ResponseWrapper(localName = "insertUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.InsertUserResponse")
    public UserResult insertUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.GlaeubigerResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateGlaeubiger", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateGlaeubiger")
    @ResponseWrapper(localName = "updateGlaeubigerResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateGlaeubigerResponse")
    public GlaeubigerResult updateGlaeubiger(
        @WebParam(name = "arg0", targetNamespace = "")
        Glaeubiger arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateMandant", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateMandant")
    @ResponseWrapper(localName = "updateMandantResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateMandantResponse")
    public MandantResult updateMandant(
        @WebParam(name = "arg0", targetNamespace = "")
        Mandant arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.MandantUserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateMandantUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateMandantUser")
    @ResponseWrapper(localName = "updateMandantUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateMandantUserResponse")
    public MandantUserResult updateMandantUser(
        @WebParam(name = "arg0", targetNamespace = "")
        MandantUser arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.NatPersonResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateNatPerson", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateNatPerson")
    @ResponseWrapper(localName = "updateNatPersonResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateNatPersonResponse")
    public NatPersonResult updateNatPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        NatuerlichePerson arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns org.jboss.test.ws.jaxws.jbws1857.types.UserResult
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://example.com", className = "org.jboss.test.ws.jaxws.jbws1857.types.UpdateUserResponse")
    public UserResult updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

}