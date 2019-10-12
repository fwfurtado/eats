package me.fwfurtado.register;

import me.fwfurtado.domain.Restaurant;

public interface RegisterRepository {

    void save(Restaurant restaurant);
}
