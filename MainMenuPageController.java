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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import sprint2.utils.PageUtils;

public class MainMenuPageController {

    @FXML
    private Button floorButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button profileButton;

    @FXML
    void loadOrder(ActionEvent event) throws IOException {
        
        PageUtils page = new PageUtils();
        page.loadPage("/sprint2/views/orderPage.fxml");
    }

    @FXML
    void loadProfile(ActionEvent event) {
        
    }

    @FXML
    void loadStore(ActionEvent event) throws IOException {
        PageUtils page = new PageUtils();
        page.loadPage("/sprint2/views/floorPage.fxml");
    }

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

}
