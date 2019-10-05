package me.fwfurtado;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;
import me.fwfurtado.SearchService.RequestType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final SearchService searchService;

    public HomeController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("home/{id}")
    Map<String, Long> show(@PathVariable Long id) {
        return Map.of("id", id);
    }

    @GetMapping(value = "home/search", produces = "application/vnd.search.v1+json")
    ResponseEntity<Map<String, String>> searchFeign() {
        return searchService.requestSearch(RequestType.FEIGN);
    }

    @GetMapping(value = "home/search", produces = "application/vnd.search.v2+json")
    ResponseEntity<Map<String, String>> searchRestTemplate() {
        return searchService.requestSearch(RequestType.REST_TEMPLATE);

    }
}
