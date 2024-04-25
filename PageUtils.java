/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.utils;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author warren
 */
public class PageUtils {
    
    public void loadPage(String FXMLPath) throws IOException{        
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image("sprint2/images/logo.png"));
    }
    
    public static void closePage(Node source){
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public static void showAlertError(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.ERROR);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();
    }
    
    public static void showAlertInformation(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.INFORMATION);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();
    }

    /**
     * 
     * @param header
     * @param context
     * @return a boolean value depending on the answer of the alert 
     */
    public static boolean showAlertConfirmation(String header, String context){
        Alert forgot = new Alert(Alert.AlertType.CONFIRMATION);
        forgot.setHeaderText(header);
        forgot.setContentText(context);
        forgot.showAndWait();
        
        return forgot.getResult() != ButtonType.CANCEL;
    }
    

    
}
