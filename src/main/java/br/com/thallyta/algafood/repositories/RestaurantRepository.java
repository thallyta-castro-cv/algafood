package br.com.thallyta.algafood.repositories;

import br.com.thallyta.algafood.model.Restaurant;
import br.com.thallyta.algafood.repositories.queries.restaurant.RestaurantQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantQueries {

    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> findByNameAndKitchen(String name, @Param("id") Long kitchenId);

    List<Restaurant> findTop2ByNameContaining(String name);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    int countByKitchenId(Long kitchen);
}
