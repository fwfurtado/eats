package me.fwfurtado;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SearchController {

    private final String profile;

    SearchController(@Value("${spring.profiles.active:default}") String profile) {
        this.profile = profile;
    }

    enum Type {
        ERROR, DEFAULT;
    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "fallback")
    Map<String, String> show(@RequestParam(defaultValue = "DEFAULT") Type type) {

        if (type == Type.ERROR) throw new IllegalStateException("Type error received");

        return Map.of("instance", profile);
    }

    Map<String, String> fallback(Type type) {
        return Map.of("instance", type.name());
    }

}
