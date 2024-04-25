/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sprint2.models.Order;
import sprint2.utils.FileUtils;

/**
 *
 * @author warre
 */
public class viewOrderPageController implements Initializable{

    @FXML
    private ListView<Order> order;
    
    private Order currentOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        String tableId = null;
        try {
            tableId = FileUtils.getLines("src/sprint2/files/current.txt").get(1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(viewOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            currentOrder = FileUtils.deserializeMapOrder().get(tableId);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(orderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.order.setItems(FXCollections.observableArrayList(currentOrder));
        
        
    }

}
