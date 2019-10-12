package me.fwfurtado.infra;

import me.fwfurtado.domain.Restaurant;
import me.fwfurtado.register.RegisterRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Restaurant.class, idClass = Long.class)
public interface RestaurantRepository extends RegisterRepository {

}
