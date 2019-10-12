package me.fwfurtado.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private Integer number;
    private String complement;
    private String zipCode;

    /**
     * @deprecated frameworks only
     */
    @Deprecated
    private Address() {
    }

    public Address(String street, Integer number, String complement, String zipCode) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getZipCode() {
        return zipCode;
    }
}
