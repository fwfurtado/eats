package me.fwfurtado;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SearchController {

    private final String profile;

    SearchController(@Value("${spring.profiles.active:default}") String profile) {
        this.profile = profile;
    }

    @GetMapping
    Map<String, String> show() {
        return Map.of("instance", profile);
    }

}
