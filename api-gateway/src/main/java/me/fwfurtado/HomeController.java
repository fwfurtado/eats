package me.fwfurtado;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("home/{id}")
    Map<String, Long> show(@PathVariable Long id) {
        return Map.of("id", id);
    }

}
