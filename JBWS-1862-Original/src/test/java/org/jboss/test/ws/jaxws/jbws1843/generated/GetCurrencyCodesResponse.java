
package org.jboss.test.ws.jaxws.jbws1843.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="response">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="currency" type="{urn:BaseComponents}CurrencyCodeType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response"
})
@XmlRootElement(name = "getCurrencyCodesResponse")
public class GetCurrencyCodesResponse {

    @XmlElement(required = true)
    protected GetCurrencyCodesResponse.Response response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link GetCurrencyCodesResponse.Response }
     *     
     */
    public GetCurrencyCodesResponse.Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCurrencyCodesResponse.Response }
     *     
     */
    public void setResponse(GetCurrencyCodesResponse.Response value) {
        this.response = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="currency" type="{urn:BaseComponents}CurrencyCodeType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "currency"
    })
    public static class Response {

        @XmlElement(required = true, nillable = true)
        protected List<CurrencyCodeType> currency;

        /**
         * Gets the value of the currency property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the currency property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCurrency().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CurrencyCodeType }
         * 
         * 
         */
        public List<CurrencyCodeType> getCurrency() {
            if (currency == null) {
                currency = new ArrayList<CurrencyCodeType>();
            }
            return this.currency;
        }

    }

}