/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.controllers;

/**
 *
 * @author warren
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import sprint2.models.Order;
import sprint2.models.OrderQueue;
import sprint2.utils.FileUtils;
import sprint2.utils.PageUtils;

public class orderPageController implements Initializable{

    @FXML
    private Button logoutButton;

    @FXML
    private ListView<Order> orderQueue;
    
    private OrderQueue orders;
    
    @FXML
    void logout(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        boolean decision = PageUtils.showAlertConfirmation("DISCONNECTION", "Do you really want to log out?");
        
        if(decision){
            page.loadPage("/sprint2/views/loginPage.fxml");
            PageUtils.closePage(source);
        }

        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            orders = FileUtils.deserializeOrder();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(orderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.orderQueue.setItems(FXCollections.observableArrayList(orders.getOrders()));

            

        
    }

}
