package de.tub.ise.anwsys.Service;

import de.tub.ise.anwsys.models.Order;
import de.tub.ise.anwsys.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    private OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}
    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public boolean findOrder(Long Id){
        if(orderRepository.findOne(Id) != null)
        {
            return  true;
        }
        else  return false;
    }

    public Order getOrder(Long id)
    {
        return orderRepository.getOne(id);
    }

    public Collection<Order> getOrderList()
    {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id)
    {
        orderRepository.delete(id);
    }

}
