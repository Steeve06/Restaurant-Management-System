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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sprint2.models.Cook;
import sprint2.models.Manager;
import sprint2.models.Waiter;
import sprint2.utils.FileUtils;
import sprint2.utils.PageUtils;

public class loginPageController {

    @FXML
    private Button forgot_button;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    

    
    @FXML
    void forgotPassword(ActionEvent event) {
        PageUtils.showAlertInformation("CREDENTIALS FORGOTTEN", "MESSAGE AN ADMIN TO RECOVER YOUR CREDENTIALS");
    }

    
    /**
     * login based on the data entered in the input fields
     * @param event
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @FXML
    void login(ActionEvent event) throws FileNotFoundException, IOException {
        
        ArrayList<String> lines= FileUtils.getLines("src/sprint2/files/login.txt");
        HashMap<String, String> credentials = FileUtils.getCredentials(lines);
        String user = username.getText();
        String pass = password.getText();
        
        Node source = (Node) event.getSource();
        PageUtils page = new PageUtils();
        
        //username does not exist
        if(!credentials.containsKey(user)){
            PageUtils.showAlertError("LOGIN ERROR", "Username does not exist!!!");
        }
        
        //username valid but password not valid
        if(credentials.containsKey(user) && !credentials.get(user).equals(pass) ){
            PageUtils.showAlertError("LOGIN ERROR", "Password does not match!!!");
        }
        
        //username and password valid
        if(credentials.containsKey(user) && credentials.get(user).equals(pass) ){
            
            lines = FileUtils.getLines("src/sprint2/files/employees.txt");
            HashMap<String, String[]> employees = FileUtils.getEmployees(lines);
            
            String[] information = employees.get(user);
            String firstName = information[0];
            String lastName = information[1];
            String Job = information[2];
            
            PageUtils.showAlertInformation("CONNECTION SUCCESSFUL", "Welcome back "+firstName+" !");
            
            
            switch (Job) {
                case "M":
                    FileUtils.writeCurrentEmployee(user);
                    page.loadPage("/sprint2/views/mainMenuPage.fxml");
                    break;
                case "C":
                    page.loadPage("/sprint2/views/orderPage.fxml");
                    break;
                case "W":
                    FileUtils.writeCurrentEmployee(user);
                    page.loadPage("/sprint2/views/floorPage.fxml");
                default:
                    break;
            }
            
            PageUtils.closePage(source);
            
        }
        
    }

}
