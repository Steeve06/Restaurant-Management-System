package sprint2.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sprint2.models.Item;
import sprint2.models.Order;
import sprint2.models.OrderQueue;
import sprint2.utils.FileUtils;
import sprint2.utils.PageUtils;

public class makeOrderPageController implements Initializable{

    @FXML
    private TableColumn<Item, String> TypeCol;

    @FXML
    private ListView<Item> currentOrder;

    @FXML
    private TableColumn<Item, String> idCol;

    @FXML
    private TableView<Item> itemView;

    @FXML
    private TableColumn<Item, String> nameCol;

    @FXML
    private TableColumn<Item, Double> priceCol;

    
    private Order order;
    private OrderQueue orders;
    private ObservableList<Item> itemsOrdered;
    
    HashMap<String, Order> mapOrder;
    
    @FXML
    void addItem(ActionEvent event) {
        Item item = this.itemView.getSelectionModel().getSelectedItem();
        this.itemsOrdered.add(item);
        order.getItems().add(item);        
    }
    

    @FXML
    void submitOrder(ActionEvent event) throws IOException  {
        
        if(PageUtils.showAlertConfirmation("ORDER SUBMISSION", "Would you like to submit the current order?")){
            Node source = (Node) event.getSource();
            PageUtils.closePage(source);
            orders.getOrders().add(order);
            mapOrder.put(FileUtils.getLines("src/sprint2/files/current.txt").get(1), order);
            FileUtils.serializeOrders(orders);
            FileUtils.serializeMapOrders(mapOrder);
        }
        
    }
    
    @FXML
    void deleteItem(ActionEvent event){
        Item item = currentOrder.getSelectionModel().getSelectedItem();
        itemsOrdered.remove(item);
        order.getItems().remove(item);        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            
            HashMap<String,String[]> map = null;
            try {
                map = FileUtils.getItems(FileUtils.getLines("src/sprint2/files/Items.txt"));
            } catch (FileNotFoundException ex) {
                System.out.println("couldnt find item file");
            }
            
            
            List<Item> items = new ArrayList<>();
            for (HashMap.Entry<String,String[]> mapElement : map.entrySet()) {
                String key = mapElement.getKey();
                String values[] = mapElement.getValue();
                items.add(new Item(values[0],key,Double.parseDouble(values[1]),values[2]));
            }
//            ORDERS AND TABLE VIEW INIT
            this.itemView.setItems(FXCollections.observableArrayList(items));
            this.itemsOrdered = FXCollections.observableArrayList();
            this.currentOrder.setItems(this.itemsOrdered);
            String waiterId = null;
            try {
                waiterId = FileUtils.getLines("src/sprint2/files/current.txt").get(0);
            } catch (FileNotFoundException ex) {
                System.out.println("Cannot find waiter ID");
            }
            String tableId = FileUtils.getLines("src/sprint2/files/current.txt").get(1);
            this.order = new Order();
            this.order.setServerId(waiterId);
            this.order.setTableId(tableId);
        } catch (FileNotFoundException ex) {
             System.out.println("Cannot find ORDER");
        }
        ArrayList<Item> items = new ArrayList<>();
        order.setItems(items);
        
        //orderqueue
        orders = new OrderQueue();
        try {
            orders = FileUtils.deserializeOrder();
        } catch (IOException ex) {
            System.out.println("ORDER not found");;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mapOrder = new HashMap<>();
        try {
            mapOrder = FileUtils.deserializeMapOrder();
        } catch (IOException ex) {
            System.out.println("map not found");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(makeOrderPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    

}
