/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sprint2.models.Table;
import sprint2.models.Waiter;
import sprint2.utils.FileUtils;
import sprint2.utils.PageUtils;

/**
 *
 * @author warren
 */
public class floorPageController implements Initializable{
    
    
     @FXML
    private Button A1;

    @FXML
    private Button A11;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button A4;

    @FXML
    private Button A5;

    @FXML
    private Button B1;

    @FXML
    private Button B11;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private Button B5;

    @FXML
    private Button C4;

    @FXML
    private Button C5;

    @FXML
    private Button D4;

    @FXML
    private Button D5;

    @FXML
    private Button E1;

    @FXML
    private Button E11;

    @FXML
    private Button E2;

    @FXML
    private Button E3;

    @FXML
    private Button E4;

    @FXML
    private Button E5;

    @FXML
    private Button F1;

    @FXML
    private Button F11;

    @FXML
    private Button F2;

    @FXML
    private Button F3;

    @FXML
    private Button F4;

    @FXML
    private Button F5;

    @FXML
    private Button bar;

    @FXML
    private Button logoutButton;

    
  
        
    
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
    /**
     * Perform a waiter interaction based on the color of the tables
     * White -> cannot act
     * Green -> make an order
     * Yellow -> Watch an order
     * Red -> Clean a table
     * @param event
     * @throws IOException 
     */
    @FXML
    void waiterInteraction(ActionEvent event) throws IOException {
        
        Node node = (Node) event.getSource();
        String user = FileUtils.getLines("src/sprint2/files/current.txt").get(0);
        //load order page
        if(node.getStyle().equals("-fx-background-color: LIGHTGREEN;")){
            FileUtils.writeCurrentTable(node.getId(),user);
            new PageUtils().loadPage("/sprint2/views/makeOrderPage.fxml");
            
                        
            node.setStyle("-fx-background-color: YELLOW;");
        }
        
        //see order
        else if (node.getStyle().equals("-fx-background-color: YELLOW;")){
            FileUtils.writeCurrentTable(node.getId(),user);
            new PageUtils().loadPage("/sprint2/views/viewOrderPage.fxml");
        }
        
        //clean table
        else if (node.getStyle().equals("-fx-background-color: RED;")){
            
            if(PageUtils.showAlertConfirmation("DIRTY TABLE", "Would you like to call and clean the table?")) 
                node.setStyle("-fx-background-color: LIGHTGREEN;");
        }
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String user = null;
        String tablesId [] = null;
        
        //load current employee
        try {
            //To change body of generated methods, choose Tools | Templates.
            user = FileUtils.getLines("src/sprint2/files/current.txt").get(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(floorPageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot find current file");
        }
        //load the ids of its table
        try {
            tablesId = FileUtils.getEmployeeTables
                                (FileUtils.getTables(FileUtils.getLines("src/sprint2/files/waiters.txt")), user);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(floorPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //put the accessible table
        List ids = Arrays.asList(tablesId);
        for(Node node: A1.getParent().getChildrenUnmodifiable()){
            if (ids.contains(node.getId())) node.setStyle("-fx-background-color: LIGHTGREEN;");           
    }
        F1.setStyle("-fx-background-color: RED;");
        
    }
    
}
