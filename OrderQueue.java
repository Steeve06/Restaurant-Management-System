/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author warre
 */
public class OrderQueue implements Serializable{
    
    private ArrayList<Order> orders;

    public OrderQueue() {
        this.orders = new ArrayList<>();
    }

    public OrderQueue(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    
    
    
    
    
}
