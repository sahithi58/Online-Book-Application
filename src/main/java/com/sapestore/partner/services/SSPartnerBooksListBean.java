
package com.sapestore.partner.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SSPartnerBooksListBean complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SSPartnerBooksListBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookAuthor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookDetailDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookPrice" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bookShortDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryIdpr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fullImageUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publisherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="thumbImageUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SSPartnerBooksListBean", namespace = "http://services.partner.sapestore.com/xsd", propOrder = {
    "active",
    "bookAuthor",
    "bookDetailDesc",
    "bookPrice",
    "bookShortDesc",
    "bookTitle",
    "categoryIdpr",
    "fullImageUrl",
    "isbn",
    "publisherName",
    "quantity",
    "thumbImageUrl"
})
public class SSPartnerBooksListBean {

    @XmlElement(nillable = true)
    protected String active;
    @XmlElement(nillable = true)
    protected String bookAuthor;
    @XmlElement(nillable = true)
    protected String bookDetailDesc;
    protected Integer bookPrice;
    @XmlElement(nillable = true)
    protected String bookShortDesc;
    @XmlElement(nillable = true)
    protected String bookTitle;
    protected Integer categoryIdpr;
    @XmlElement(nillable = true)
    protected String fullImageUrl;
    @XmlElement(nillable = true)
    protected String isbn;
    @XmlElement(nillable = true)
    protected String publisherName;
    protected Integer quantity;
    @XmlElement(nillable = true)
    protected String thumbImageUrl;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActive(String value) {
        this.active = value;
    }

    /**
     * Gets the value of the bookAuthor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * Sets the value of the bookAuthor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookAuthor(String value) {
        this.bookAuthor = value;
    }

    /**
     * Gets the value of the bookDetailDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookDetailDesc() {
        return bookDetailDesc;
    }

    /**
     * Sets the value of the bookDetailDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookDetailDesc(String value) {
        this.bookDetailDesc = value;
    }

    /**
     * Gets the value of the bookPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBookPrice() {
        return bookPrice;
    }

    /**
     * Sets the value of the bookPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBookPrice(Integer value) {
        this.bookPrice = value;
    }

    /**
     * Gets the value of the bookShortDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookShortDesc() {
        return bookShortDesc;
    }

    /**
     * Sets the value of the bookShortDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookShortDesc(String value) {
        this.bookShortDesc = value;
    }

    /**
     * Gets the value of the bookTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * Sets the value of the bookTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookTitle(String value) {
        this.bookTitle = value;
    }

    /**
     * Gets the value of the categoryIdpr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategoryIdpr() {
        return categoryIdpr;
    }

    /**
     * Sets the value of the categoryIdpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategoryIdpr(Integer value) {
        this.categoryIdpr = value;
    }

    /**
     * Gets the value of the fullImageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullImageUrl() {
        return fullImageUrl;
    }

    /**
     * Sets the value of the fullImageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullImageUrl(String value) {
        this.fullImageUrl = value;
    }

    /**
     * Gets the value of the isbn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the isbn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

    /**
     * Gets the value of the publisherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * Sets the value of the publisherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisherName(String value) {
        this.publisherName = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the thumbImageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    /**
     * Sets the value of the thumbImageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThumbImageUrl(String value) {
        this.thumbImageUrl = value;
    }

}
