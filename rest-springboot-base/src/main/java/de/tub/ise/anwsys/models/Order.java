package de.tub.ise.anwsys.models;

import org.hibernate.type.FloatType;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Order implements  Serializable{

    private static final long serialVersionUID = -6640481949420444264L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="order_entity_seq_gen")
    @SequenceGenerator(name="order_entity_seq_gen", sequenceName="ORDER_ENTITY_SEQ")
    Long id;

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @ElementCollection
    @CollectionTable(name="OrdetItem")
    Collection<OrderItem> orderItems;

    Float totalPrice;
    String recipient ;

    protected Order(){
        // Empty constructor required by JPA
    }

    public void setTotalPrice(float totalPrice){
        this.totalPrice = totalPrice;
    }

    public Float getTotalPrice(){
        return this.totalPrice;
    }

    public void setRecipient(String recipient){
        this.recipient = recipient;
    }

    public String getRecipient(){
        return this.recipient;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }

    public Collection<OrderItem> getOrderItems(){
        return this.orderItems;
    }

    public Long getId() {
        return id;
    }


}
