/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import sprint2.utils.Status;

/**
 *
 * @author warren
 */
public class Table {
    
    String id;
    Status status;
    Order order;

    public Table(String id, Status status, Order order) {
        this.id = id;
        this.status = status;
        this.order = order;
    }

    public Table(String id) {
        this.id = id;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    
    
}
