package me.fwfurtado.infra;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "zip-lookup")
public interface ZipCodeResolutionClient {

}
