package me.fwfurtado.register;

import me.fwfurtado.domain.Restaurant;
import me.fwfurtado.register.RegisterController.RestaurantForm;
import org.springframework.stereotype.Service;

@Service
class RegisterService {

    private final RegisterRepository repository;
    private final RestaurantFormToRestaurant converter;

    RegisterService(RegisterRepository repository, RestaurantFormToRestaurant converter) {
        this.repository = repository;
        this.converter = converter;
    }

    Long register(RestaurantForm form) {
        Restaurant restaurant = converter.convert(form, null);

        repository.save(restaurant);

        return restaurant.getId();
    }
}
