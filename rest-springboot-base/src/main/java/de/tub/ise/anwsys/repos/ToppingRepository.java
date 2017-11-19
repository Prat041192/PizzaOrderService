package de.tub.ise.anwsys.repos;

import de.tub.ise.anwsys.models.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping,Long>{
}
