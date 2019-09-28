package me.fwfurtado;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "search")
interface SearchClient {

    @GetMapping
    Map<String, String> find();
}
