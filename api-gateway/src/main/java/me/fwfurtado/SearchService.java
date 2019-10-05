package me.fwfurtado;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class SearchService {

    private final RestTemplate rest;
    private final String searchURL;
    private final SearchClient searchClient;

    SearchService(RestTemplate rest, @Value("${search.url}") String searchURL, SearchClient searchClient) {
        this.rest = rest;
        this.searchURL = searchURL;
        this.searchClient = searchClient;
    }

    enum RequestType {
        FEIGN,
        REST_TEMPLATE
    }

    @HystrixCommand(fallbackMethod = "fallback")
    ResponseEntity<Map<String, String>> requestSearch(RequestType type) {
        Map<String, String> body;

        if (type == RequestType.FEIGN) {
            body = searchClient.find();
        } else {
            body = rest.exchange(searchURL, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {
            }).getBody();
        }

        return ok().header("X-provider", type.name())
            .header("Fallback-Status", "disable").body(body);
    }

    ResponseEntity<Map<String, String>> fallback(RequestType type) {
        return notFound().header("X-Provider", type.name())
            .header("Fallback-Status", "enable").build();
    }
}
