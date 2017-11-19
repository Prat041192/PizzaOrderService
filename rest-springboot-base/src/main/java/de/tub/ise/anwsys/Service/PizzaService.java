package de.tub.ise.anwsys.Service;

import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.repos.PizzaRepository;
import org.hibernate.type.FloatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;
    private ToppingService toppingService;
    @Autowired
    private  PizzaService(PizzaRepository pizzaRepository, ToppingService toppingService){
        this.toppingService = toppingService;
        this.pizzaRepository = pizzaRepository;
    }

    public void addNewPizza(Pizza pizza)
    {
        pizza.setPrice(pizza.getPrice() + calculatePrice(pizza));
        pizzaRepository.save(pizza);
    }

    public Collection<Pizza> getPizzaList() {
        Collection<Pizza> cp = pizzaRepository.findAll();
        return cp;
    }

    public  Pizza getPizza(Long Id) {
        return pizzaRepository.getOne(Id);
    }

    public void updateExitingPizza (Pizza pizza, Long pizzaId)
    {
        Pizza pizzaToUpdate = pizzaRepository.getOne(pizzaId);
        pizzaToUpdate.setName(pizza.getName());
        pizzaToUpdate.setSize(pizza.getSize());
        pizzaRepository.save(pizzaToUpdate);
    }
    public void deletePizza(Long Id)
    {
        pizzaRepository.delete(Id);
    }

    public boolean findPizza(Long id)
    {
        if (pizzaRepository.findOne(id) != null)
            return true;
        else
            return  false;
    }

    public Collection<Topping> getGetToppingListOfAPizza(Long pizzaId) {
        Collection<Topping> toppings = new ArrayList<Topping>();
        Pizza pizza = pizzaRepository.findOne(pizzaId);
        if(pizza.getToppings()!= null) {
            for (Long id : pizza.getToppings()
                    ) {
                toppings.add(toppingService.getTopping(id));
            }
        }
        return toppings;
    }

    public void addTopping(Topping topping, Long id) {
        Pizza pizza = pizzaRepository.findOne(id);
        Topping topping1 = toppingService.addTopping(topping);
        pizza.addToppings(topping1);
        pizzaRepository.save(pizza);
    }

    public Float calculatePrice(Pizza pizza)
    {
        Float price = 0.0F;
        if(pizza.getToppings() !=null && pizza.getToppings().size()>0) {
            for (Long id : pizza.getToppings()
                    ) {
                price = price + toppingService.getTopping(id).getPrice();
            }
        }
        return price;
    }

    public void addToppingToToppingList(Topping topping, Long pizzaId) {

        Pizza pizza = pizzaRepository.findOne(pizzaId);
        topping = toppingService.addTopping(topping);
        pizza.addToppings(topping);
        pizzaRepository.save(pizza);

    }
}
