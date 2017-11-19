package de.tub.ise.anwsys.Service.validator;

import de.tub.ise.anwsys.models.Order;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import org.springframework.stereotype.Service;

@Service
public interface ValidateObject {
    public boolean validatePizza(Pizza pizza);

    public boolean validateOrder(Order order);

    public boolean validateTopping(Topping topping);

}
