package me.fwfurtado;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {

    private final RestTemplate rest;
    private final String searchURL;
    private final SearchClient searchClient;

    public HomeController(RestTemplate rest, @Value("${search.url}") String searchURL, SearchClient searchClient) {
        this.rest = rest;
        this.searchURL = searchURL;
        this.searchClient = searchClient;
    }

    @GetMapping("home/{id}")
    Map<String, Long> show(@PathVariable Long id) {
        return Map.of("id", id);
    }

    @GetMapping(value = "home/search", produces = "application/vnd.search.v1+json")
    ResponseEntity<Map<String, String>> searchFeignl() {
        Map<String, String> body = searchClient.find();

        return ok().header("X-provider", "feign").body(body);
    }

    @GetMapping(value = "home/search", produces = "application/vnd.search.v2+json")
    ResponseEntity<Map<String, String>> searchRestTemplate() {
        Map<String, String> body = rest.exchange(searchURL, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {
        }).getBody();

        return ok().header("X-provider", "rest-template").body(body);

    }
}
