package de.tub.ise.anwsys.Service.validator;

import de.tub.ise.anwsys.models.Order;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateObjectImpl implements ValidateObject {
    @Autowired
    ValidateObject validateObject;

    public boolean validatePizza(Pizza pizza)
    {
        if(pizza.getName()== null || pizza.getName().isEmpty())
        {
            return false;
        }
        //else if (pizza. )
        {

        }
        return  true;
    }
    public boolean validateOrder(Order order)
    {
        if (order.getOrderItems().size()==0 || order.getRecipient() == null || order.getRecipient().isEmpty())
        {
            return  false;
        }
        else
            return  true;
    }
    public boolean validateTopping(Topping topping)
    {
        if (topping.getName().isEmpty()|| topping.getName() ==null|| topping.getPrice() == null)
        {
            return  false;
        }
        else
            return true;
    }
}
