package me.fwfurtado.location;

import java.util.LinkedList;
import java.util.Optional;
import me.fwfurtado.infra.GeoCoderClient;
import me.fwfurtado.infra.GeoCoderClient.GeoInfo;
import me.fwfurtado.infra.ZipClient;
import me.fwfurtado.infra.ZipClient.ZipInfo;
import org.springframework.stereotype.Service;

@Service
class LocationService {

    private final GeoCoderClient geoCoderClient;
    private final ZipClient zipClient;

    LocationService(GeoCoderClient geoCoderClient, ZipClient zipClient) {
        this.geoCoderClient = geoCoderClient;
        this.zipClient = zipClient;
    }

    LocationResponse searchLocationBy(String zip) {
        ZipInfo zipInfo = zipClient.fetchInfoBy(zip).orElseThrow(() -> new ZipCodeNotFoundException(zip));

        GeoInfo geoInfo = geoCoderClient.searchBy(makeRequestBy(zip)).orElseThrow(() -> new LatLonNotFoundException(zip));

        return  new LocationResponse(zipInfo.getStreet(), zipInfo.getComplement(), zipInfo.getZipCode(), geoInfo.getLatitude(), geoInfo.getLongitude());
    }

    private String makeRequestBy(String zip) {
        return "'" + zip + " Brazil'";
    }
}
