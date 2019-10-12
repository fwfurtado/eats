package me.fwfurtado.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LocationController {

    private final LocationService service;

    LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("resolve/{zip}")
    LocationResponse geoResolve(@PathVariable String zip) {

        return service.searchLocationBy(zip);

    }

}
