package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class OrderItem implements  Serializable{

    private static final long serialVersionUID = -6640481949420444264L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="orderitem_entity_seq_gen")
    @SequenceGenerator(name="orderitem_entity_seq_gen", sequenceName="ORDERITEM_ENTITY_SEQ")
    Long Id;

    Long pizzaId;
    int quantity;

    @ManyToOne
    private Order order;
    
    public Long getId() {
        return Id;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public int getQuantity() {
        return quantity;
    }

    protected OrderItem(){
        // Empty constructor required by JPA
    }

    public void setPizzaId(Long pizzaId)
    {
        this.pizzaId = pizzaId;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
