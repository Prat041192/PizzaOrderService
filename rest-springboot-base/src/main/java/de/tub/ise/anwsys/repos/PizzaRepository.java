package de.tub.ise.anwsys.repos;

import de.tub.ise.anwsys.models.User;
import de.tub.ise.anwsys.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {
    //Optional<Pizza> findById(Long id);
}