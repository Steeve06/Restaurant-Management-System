/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.models;

import java.util.ArrayList;

/**
 *
 * @author warre
 */
public class Waiter extends Employee{
    
    String[] tables;

    public Waiter(String id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    
    
    
    
    
}
