package me.fwfurtado.register;

import static org.springframework.http.ResponseEntity.created;

import java.net.URI;
import javax.validation.Valid;
import me.fwfurtado.domain.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("restaurants")
class RegisterController {

    private final RegisterService service;

    RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid RestaurantForm form, UriComponentsBuilder uriBuilder) {

        Long restaurantId = service.register(form);

        URI uri = uriBuilder.path("restaurants/{id}").build(restaurantId);

        return created(uri).build();
    }

    static class RestaurantForm {
        private String name;
        private String email;
        private String password;
        private String zipCode;

        /**
         * @deprecated frameworks only
         */
        @Deprecated
        private RestaurantForm() {
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getZipCode() {
            return zipCode;
        }
    }

}
