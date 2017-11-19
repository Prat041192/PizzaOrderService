package de.tub.ise.anwsys.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class Pizza implements Serializable {

    private static final long serialVersionUID = -6640481949420444264L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="pizza_entity_seq_gen")
    @SequenceGenerator(name="pizza_entity_seq_gen", sequenceName="PIZZA_ENTITY_SEQ")
    Long Id;

    String Name ;

    public enum size {
        Standard(1),
        Large(2);
        private int value;
        private size(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
    @Autowired
    size Size ;

    Float Price;

    @ElementCollection(targetClass=Long.class)
    List<Long> toppings;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public size getSize() {
        return Size;
    }

    public void setSize(size size) {
        this.Size = size;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        this.Price = price;
    }

    public Collection<Long> getToppings() {
        return this.toppings; //toppings;
    }

    public void setToppings(List<Long> toppings) {
        this.toppings = toppings;
    }
    public void addToppings(Topping topping) {
        this.toppings.add(topping.getId());
    }

    protected Pizza(){
        // Empty constructor required by JPA
    }

    public Long getId() {
        return Id;
    }
}
