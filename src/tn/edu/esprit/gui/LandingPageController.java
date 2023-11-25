/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author aladi
 */
public class LandingPageController implements Initializable {

    @FXML
    private JFXButton sinscrireButton;
    @FXML
    private JFXButton LoginButton;
    @FXML
    private Label userCountLabel;
    @FXML
    private Label agriCountLabel;
    @FXML
    private Label vetCountLabel;
    @FXML
    private Label ouvCountLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Appelez la méthode pour obtenir le nombre d'utilisateurs depuis UserService
    ServiceUser userService = new ServiceUser();
    int userCount = userService.getNumberOfUsers();

    // Mettez à jour l'étiquette avec le nombre d'utilisateurs
    userCountLabel.setText("Nombre de nos clients est : " + userCount);
    //ServiceUser userService = new ServiceUser();
    
    int agriculteurCount = userService.getNumberOfUsersByRole(UserRole.AGRICULTEUR);
    int veterinaireCount = userService.getNumberOfUsersByRole(UserRole.VETERINAIRE);
    int ouvrierCount = userService.getNumberOfUsersByRole(UserRole.OUVRIER);

    // Mettez à jour les étiquettes avec les nombres d'utilisateurs
    
    agriCountLabel.setText("Agriculteurs : " + agriculteurCount);
    vetCountLabel.setText("Vétérinaires : " + veterinaireCount);
    ouvCountLabel.setText("Ouvriers : " + ouvrierCount);
    }    

   @FXML
private void sinscrireAction(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/signup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        // Fermez la fenêtre actuelle si nécessaire
        ((Stage) sinscrireButton.getScene().getWindow()).close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void LoginAction(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        // Fermez la fenêtre actuelle si nécessaire
        ((Stage) LoginButton.getScene().getWindow()).close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
