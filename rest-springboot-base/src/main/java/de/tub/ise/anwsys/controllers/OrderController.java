package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.models.Order;
import de.tub.ise.anwsys.Service.OrderService;
import de.tub.ise.anwsys.repos.OrderRepository;
import de.tub.ise.anwsys.Service.validator.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private OrderRepository orderRepository;
    private ValidateObject validateObject;

    @Autowired
    public OrderController (OrderService orderService, OrderRepository orderRepository, ValidateObject validateObject)
    {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.validateObject = validateObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewOrder(@RequestBody Order order ) {
        try {
            if (validateObject.validateOrder(order)) {
                orderService.addNewOrder(order);
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(order.getId()).toUri();
                return ResponseEntity.created(location).build();
            }
            else
                return ResponseEntity.badRequest().build();
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable (value = "orderId") Long orderId) {

        if (orderService.findOrder(orderId)) {
            return ResponseEntity.status(HttpStatus.FOUND).body(orderService.getOrder(orderId));
        }
        else
            return ResponseEntity.notFound().build();

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getOrderList() {
        //orderService.getOrderList();
        return ResponseEntity.ok(orderService.getOrderList());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId) {
            orderService.deleteOrder(orderId);
            return ResponseEntity.ok(String.format("Welcome back,. How are your studies going?"));
    }


}
