package me.fwfurtado.location;

class ZipCodeNotFoundException extends IllegalArgumentException {

    ZipCodeNotFoundException(String zip) {
        super("Cannot find information about this zipcode" + zip);
    }
}
