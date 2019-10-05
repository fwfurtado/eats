package me.fwfurtado;

import java.util.Map;
import me.fwfurtado.SearchClient.SearchClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "search", fallback = SearchClientFallback.class)
interface SearchClient {

    @GetMapping
    Map<String, String> find();

    @Component
    class SearchClientFallback implements SearchClient {

        @Override
        public Map<String, String> find() {
            return Map.of("instance", "unknown");
        }
    }
}
