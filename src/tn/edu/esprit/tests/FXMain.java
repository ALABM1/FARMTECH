/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.edu.esprit.tools.DataSource;

/**
 *
 * @author rihab
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                        DataSource dataSource = DataSource.getInstance();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/LandingPage.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("/*******FARMTECH /******");
            primaryStage.show();
            
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
