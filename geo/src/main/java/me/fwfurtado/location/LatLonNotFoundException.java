package me.fwfurtado.location;

class LatLonNotFoundException extends IllegalArgumentException {

    LatLonNotFoundException(String zip) {
        super("Cannot find latitude/longitude information about this zipcode " + zip);
    }
}
