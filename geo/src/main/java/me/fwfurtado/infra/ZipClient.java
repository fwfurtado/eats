package me.fwfurtado.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${zipcode.url}", name = "zip", decode404 = true)
public interface ZipClient {

    @GetMapping("{cep}/json")
    Optional<ZipInfo> fetchInfoBy(@PathVariable String cep);

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ZipInfo {
        @JsonProperty("logradouro")
        private String street;

        @JsonProperty("cep")
        private String zipCode;

        @JsonProperty("complemento")
        private String complement;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getComplement() {
            return complement;
        }

        public void setComplement(String complement) {
            this.complement = complement;
        }
    }
}
