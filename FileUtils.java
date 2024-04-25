/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprint2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import sprint2.models.Order;
import sprint2.models.OrderQueue;

/**
 *
 * @author warren
 */
public class FileUtils {
    
    
    /**
     * 
     * @param url URL of the page to open
     * @return ArrayList lines
     * @throws FileNotFoundException
     * Get the lines of a text File
     */
    public static ArrayList<String> getLines(String url) throws FileNotFoundException{
        
        ArrayList<String> lines = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(url));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext()){
            String line = scanner.next();
            lines.add(line);
        }
        return lines;      
    }
    
    /**
     * 
     * 
     * @param lines: lines gotten from the reading of a text file
     * @return HashMap with key = username and value = password  
     */
    public static HashMap<String,String> getCredentials(ArrayList<String> lines){
        
        HashMap<String, String> credentials = new HashMap<>();
        
        for (String line: lines){
            String[] values = line.split(",");
            credentials.put(values[0], values[1]);
        }
        return credentials;
    }
    
    /**
     * 
     * @param lines
     * @return HashMap with key=username and value = information
     * information are: firstName, lastName,Job
     */
    public static HashMap<String, String[]> getEmployees(ArrayList<String> lines){
        
            HashMap<String, String[]> employees = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information
                String[] values = line.split(":");
                String username = values[0];
                String information [] = values[1].split(",");
                employees.put(username, information);
            }
            
            
            return employees;
        }
    
    public static HashMap<String, String[]> getItems(ArrayList<String> lines){
        
            HashMap<String, String[]> employees = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information               
                String[] values = line.split(":");                
                String username = values[0];
                String information [] = values[1].split(",");
                employees.put(username, information);
            }
            return employees;
        }
    
    
    /**
     * 
     * @param lines lines got from reading a text file
     * @return HashMap of key,value = username,tablesId
     */
    public static HashMap<String, String[]> getTables(ArrayList<String> lines){
        
            HashMap<String, String[]> employeeTable = new HashMap<>();
            
            for (String line: lines){
                //split first into username:information
                String[] values = line.split(":");
                String username = values[0];
                String information [] = values[1].split(",");
                employeeTable.put(username, information);
            }
            return employeeTable;
        }
    /**
     * 
     * @param t the hashMap of username:tablesId
     * @param username the username from which we should get the table
     * @return 
     */
    public static String[] getEmployeeTables(HashMap<String, String[]> t, String username){
        return t.get(username);
    }
    
    /**
     * Get the current employee in a file called current
     * @param username: username currently working
     * @throws FileNotFoundException 
     */
    public static void writeCurrentEmployee(String username) throws FileNotFoundException{
        java.io.File file = new java.io.File("src/sprint2/files/current.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        output.print(username);
        output.close();
    }
    
    public static void writeCurrentTable(String tableId, String userId) throws FileNotFoundException{
        java.io.File file = new java.io.File("src/sprint2/files/current.txt");
        java.io.PrintWriter output = new java.io.PrintWriter(file);
        output.println(userId);
        output.print(tableId);
        output.close();
        
    }
    
    
    
   //Order queue serialization
    public static void serializeOrders(OrderQueue o) throws FileNotFoundException, IOException{

        // The "ObjectOutputStream" class has the default 
        // definition to serialize an object.
        
        // By using "FileOutputStream" we will 
        // Write it to a File in the file system
        // It could have been a Socket to another 
        // machine, a database, an in memory array, etc.
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/sprint2/files/orderQueue.ser")));

        // do the magic  
        oos.writeObject(o);
        // close the writing.
        oos.close();
        
    }
    
    // Method for deserialization of object
    public static OrderQueue deserializeOrder() throws FileNotFoundException, IOException, ClassNotFoundException{
            FileInputStream file = new FileInputStream("src/sprint2/files/orderQueue.ser");
            ObjectInputStream in = new ObjectInputStream(file);
              
            
            OrderQueue o = null;
            o = (OrderQueue)in.readObject();
              
            in.close();
            file.close();
              
            return o;
    }
    
        public static void serializeMapOrders(HashMap<String,Order> o) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/sprint2/files/mapOrder.ser")));
 
        oos.writeObject(o);
        oos.close();
        
    }
    
    // Method for deserialization of object
    public static HashMap<String,Order> deserializeMapOrder() throws FileNotFoundException, IOException, ClassNotFoundException{
            FileInputStream file = new FileInputStream("src/sprint2/files/mapOrder.ser");
            ObjectInputStream in = new ObjectInputStream(file);
              
            
            HashMap<String,Order> o = null;
            o = (HashMap<String,Order>)in.readObject();
              
            in.close();
            file.close();
              
            return o;
    }
    
    
}


    

