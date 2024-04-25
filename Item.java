/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import java.io.Serializable;

/**
 *
 * @author warre
 */
public class Item implements Serializable{
    
    String name;
    String id;
    double price;
    String type;

    public Item(String name, String id, double Price, String type) {
        this.name = name;
        this.id = id;
        this.price = Price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double Price) {
        this.price = Price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item() {
    }
    
    @Override
    public String toString(){
        return "1x "+this.getName() + ": $" + String.valueOf(this.getPrice()) +"\n";
    }
    
    
}
