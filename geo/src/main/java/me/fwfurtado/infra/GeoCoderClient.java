package me.fwfurtado.infra;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${geocoder.url}", name = "geo", decode404 = true)
public interface GeoCoderClient {

    @GetMapping(value = "searchtext", produces = APPLICATION_JSON_VALUE)
    Optional<GeoInfo> searchBy(@RequestParam(name = "searchtext") String searchtext);

    @JsonIgnoreProperties(ignoreUnknown = true)
    class GeoInfo {

        @JsonProperty("Response.View[0].Result[0].Location.DisplayPosition.Latitude")
        private double latitude;

        @JsonProperty("Response.View[0].Result[0].Location.DisplayPosition.Longitude")
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
