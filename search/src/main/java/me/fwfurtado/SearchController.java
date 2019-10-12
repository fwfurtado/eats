package me.fwfurtado;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
class SearchController {

    private final String profile;
    private final String greeting;

    SearchController(@Value("${spring.profiles.active:default}") String profile, @Value("${search.greeting:''}") String greeting) {
        this.profile = profile;
        this.greeting = greeting;
    }

    enum Type {
        ERROR, DEFAULT;
    }

    @GetMapping("greet")
    Map<String, String> refresh() {
        return Map.of("search.greeting", greeting);
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
