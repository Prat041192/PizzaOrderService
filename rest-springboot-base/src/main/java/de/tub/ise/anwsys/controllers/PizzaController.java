package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.Service.PizzaService;
import de.tub.ise.anwsys.Service.ToppingService;
import de.tub.ise.anwsys.models.Pizza;
import de.tub.ise.anwsys.models.Topping;
import de.tub.ise.anwsys.Service.validator.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.CollationElementIterator;
import java.util.Collection;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaService pizzaService;
    private ToppingService toppingService;
    private ValidateObject validateObject;
    @Autowired
    private PizzaController(PizzaService pizzaService, ToppingService toppingService, ValidateObject validateObject) {
        this.pizzaService = pizzaService;
        this.toppingService = toppingService;
        this.validateObject = validateObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewPizza(@RequestBody Pizza pizza) {
        try {
            if (validateObject.validatePizza(pizza)) {
                pizzaService.addNewPizza(pizza);
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(pizza.getId()).toUri();
                return ResponseEntity.created(location).body(String.format("Created new Pizza"));
            }
            else
                return ResponseEntity.badRequest().body(String.format("Invalid Input"));
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPizzaList() {
        return ResponseEntity.ok(pizzaService.getPizzaList());
    }

    @RequestMapping(value="/{pizzaId}",method = RequestMethod.GET)
    public ResponseEntity<?> getPizza(@PathVariable("pizzaId" ) Long pizzaId) {
        //check whether the pizza exist
        if(pizzaService.findPizza(pizzaId))
        {
            return ResponseEntity.ok(pizzaService.getPizza(pizzaId));
        }
        else
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{pizzaId}")
    public ResponseEntity<?> updateExitingPizza(@PathVariable("pizzaId" ) Long pizzaId, @RequestBody Pizza pizza) {
        if (pizzaService.findPizza(pizzaId)) {
            if (validateObject.validatePizza(pizza)) {
                pizzaService.updateExitingPizza(pizza, pizzaId);
                return ResponseEntity.accepted().build();
            }
            else
            {
                return ResponseEntity.badRequest().build();

            }
        }
        else
            return ResponseEntity.notFound().build();

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{pizzaId}")
    public ResponseEntity<?> deletePizza(@PathVariable("pizzaId" ) Long pizzaId) {
        if (pizzaService.findPizza(pizzaId)) {
            try {
                pizzaService.deletePizza(pizzaId);
                return ResponseEntity.accepted().body("deleted");
            }catch (Exception e)
            {
                return ResponseEntity.badRequest().body("Invalid IDs");
            }
        }
        else
            return ResponseEntity.notFound().build();

    }

    @RequestMapping(method = RequestMethod.POST, value = "/{pizzaId}/topping")
    public ResponseEntity<?> addToppingToToppingList(@PathVariable("pizzaId" ) Long pizzaId, @RequestBody Topping topping) {
        if(validateObject.validateTopping(topping) && pizzaService.findPizza(pizzaId)) {
            pizzaService.addToppingToToppingList(topping,pizzaId);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(topping.getId()).toUri();
            return ResponseEntity.created(location).body("Created new Topping for pizza.");
        }
        else
            return ResponseEntity.badRequest().body("Invalid input");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pizzaId}/topping")
    public ResponseEntity<?> getToppingListOfAPizza(@PathVariable("pizzaId" ) Long pizzaId) {
        if (pizzaService.findPizza(pizzaId)) {
            try {
                Collection<Topping> collection =  pizzaService.getGetToppingListOfAPizza(pizzaId);
             if(collection.size()>0) {
                 return ResponseEntity.status(HttpStatus.FOUND).body(collection);
             }
             else {
                 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Toppings found for this Pizza");
                }
            }
            catch (Exception e)
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Occured.");
            }
        }
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pizzaId}/topping/{toppingId}")
    public ResponseEntity<?> getTopping(@PathVariable("toppingId" ) Long toppingId) {
        if (toppingService.findTopping(toppingId)) {
            return ResponseEntity.ok(toppingService.getTopping(toppingId));
        } else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{pizzaId}/topping/{toppingId}")
    public ResponseEntity<?> deleteTopping(@PathVariable("toppingId" ) Long toppingId) {
        if (toppingService.findTopping(toppingId)) {
            try {
                toppingService.deleteTopping(toppingId);
                return ResponseEntity.accepted().body("deleted");
            } catch (Exception e)
            {
                return ResponseEntity.badRequest().body("Invalid IDs");
            }

        } else
            return ResponseEntity.notFound().build();
    }


}
