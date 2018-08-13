package com.hand.domain.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(generator = "JDBC")
    private Short addressId;

    private String address;

    private String address2;

    private String district;

    @Column(name = "city_id")
    private Short cityId;

    @Column(name = "postal_code")
    private String postalCode;

    private String phone;

    @Column(name = "last_update")
    private Date lastUpdate;

    private byte[] location;

    /**
     * @return address_id
     */
    public Short getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return city_id
     */
    public Short getCityId() {
        return cityId;
    }

    /**
     * @param cityId
     */
    public void setCityId(Short cityId) {
        this.cityId = cityId;
    }

    /**
     * @return postal_code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return last_update
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return location
     */
    public byte[] getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(byte[] location) {
        this.location = location;
    }
}