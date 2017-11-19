package de.tub.ise.anwsys.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Topping implements Serializable {

    private static final long serialVersionUID = -6640481949420444264L;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="topping_entity_seq_gen")
    @SequenceGenerator(name="topping_entity_seq_gen", sequenceName="TOPPING_ENTITY_SEQ")
    Long id;

    String name = new String();

    Float price;
    protected Topping(){
        // Empty constructor required by JPA
    }

    public Topping(String name, Float price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public Long getId() {
        return id;
    }



}
