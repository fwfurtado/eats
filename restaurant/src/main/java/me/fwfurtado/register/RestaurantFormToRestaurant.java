package me.fwfurtado.register;

import me.fwfurtado.domain.Restaurant;
import me.fwfurtado.infra.PasswordHelper;
import me.fwfurtado.register.RegisterController.RestaurantForm;
import org.springframework.stereotype.Component;

@Component
class RestaurantFormToRestaurant {

    private final PasswordHelper passwordHelper;

    RestaurantFormToRestaurant(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    Restaurant convert(RestaurantForm form, Location location) {
        return new Restaurant(form.getName(), form.getEmail(), passwordHelper.encode(form.getPassword()), null);
    }
}
