package me.fwfurtado.location;

class LocationResponse {

    private String street;
    private String complement;
    private String zipCode;
    private Double latitude;
    private Double longitude;

    public LocationResponse(String street, String complement, String zipCode, Double latitude, Double longitude) {
        this.street = street;
        this.complement = complement;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
