/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.entities.UserRole;
import tn.edu.esprit.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author aladi
 */
public class Admin_interfaceController implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private TableView<User> UsersTable;

    @FXML
    private TableColumn<User, String> userNom;
    @FXML
    private TableColumn<User, String> userPrenom;
    @FXML
    private TableColumn<User, String> userEmail;
    @FXML
    private TableColumn<User,String > userNumero;
    @FXML
    private TableColumn<User, UserRole> userRole;
    @FXML
    private TableColumn<User, String> userVille;
    @FXML
    private TableColumn<User, String>userSexe;
    @FXML
    private JFXTextField Filter;
    @FXML
    private JFXButton addPartsButton;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton search;
    @FXML
    private JFXButton deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurez vos colonnes du TableView
    
    userNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    userPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    userEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
    userNumero.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
    userRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    userVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
    userSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));

        // Chargez les utilisateurs depuis la base de données et affichez-les
        loadUsers();
        editData();
    }
     // Méthode pour charger les utilisateurs depuis la base de données
    private void loadUsers() {
        try {
            ServiceUser serviceUser = new ServiceUser();
            List<User> userList = serviceUser.getAll(); // Remplacez cette méthode par celle de votre service

            // Créez une liste observable des utilisateurs pour affichage dans le TableView
            ObservableList<User> userObservableList = FXCollections.observableArrayList(userList);

            // Chargez la liste observable dans le TableView
            UsersTable.setItems(userObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

   @FXML
private void partAddButtonAction(ActionEvent event) {
    try {
        // Charger la vue FXML de l'interface Ajout_user
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajout_user.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle (à partir du bouton cliqué)
        Stage currentStage = (Stage) addPartsButton.getScene().getWindow();

        // Remplacer la scène actuelle par la nouvelle scène (Ajout_user)
        currentStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}





   @FXML
private void deleteUserAction(ActionEvent event) {
    // Get the selected user from the TableView
    User selectedUser = UsersTable.getSelectionModel().getSelectedItem();

    // Check if a user is selected
    if (selectedUser == null) {
        // No user is selected, you can display an error message or handle it as needed
        System.out.println("No user selected for deletion.");
        return;
    }

   
    int userId = selectedUser.getId();

   
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Delete Confirmation");
    alert.setContentText("Are you sure you want to delete this user?");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
        
        ServiceUser serviceUser = new ServiceUser();
        boolean deletionSuccess = serviceUser.delete(userId);

        if (deletionSuccess) {
            
            UsersTable.getItems().remove(selectedUser);
        } else {
            
            System.out.println("User deletion failed.");
        }
    }
}

    @FXML
    private void searchUserAction(ActionEvent event) {
       
       
    // Récupérez le texte entré dans le champ Filter
    String searchName = Filter.getText().trim();

    // Si le champ de recherche n'est pas vide, effectuez la recherche
    if (!searchName.isEmpty()) {
        // Créez une liste observable pour stocker les utilisateurs filtrés
        ObservableList<User> filteredUsers = FXCollections.observableArrayList();

        // Obtenez la liste actuelle des utilisateurs
        ObservableList<User> userObservableList = UsersTable.getItems();

        // Parcourez la liste actuelle des utilisateurs
        for (User user : userObservableList) {
            // Vérifiez si le nom de l'utilisateur contient la chaîne de recherche (insensible à la casse)
            if (user.getNom().toLowerCase().contains(searchName.toLowerCase())) {
                filteredUsers.add(user);
            }
        }

        // Mettez à jour le TableView avec les résultats de la recherche
        UsersTable.setItems(filteredUsers);
    } else {
        // Si le champ de recherche est vide, rechargez tous les utilisateurs
        loadUsers();
    }
}


   


    
    private void editData() {
    // Éditer le nom du parc
    userNom.setCellFactory(TextFieldTableCell.<User>forTableColumn());
    userNom.setOnEditCommit(event -> {
        User user= event.getRowValue(); 
        user.setNom(event.getNewValue());
        System.out.println("Le nom de " +  user.getNom()+ " a été mis à jour à " + event.getNewValue());
        ServiceUser su = new ServiceUser();
        su.update(user);
    }); 
     userPrenom.setCellFactory(TextFieldTableCell.<User>forTableColumn());
     userPrenom.setOnEditCommit(event -> {
        User user= event.getRowValue(); 
        user.setNom(event.getNewValue());
        System.out.println("Le prenom de " +  user.getNom()+ " a été mis à jour à " + event.getNewValue());
        ServiceUser su = new ServiceUser();
        su.update(user);
    });
     userEmail.setCellFactory(TextFieldTableCell.<User>forTableColumn());
     userEmail.setOnEditCommit(event -> {
    User user = event.getRowValue();
    user.setMail(event.getNewValue());
    System.out.println("L'email de " + user.getNom() + " a été mis à jour à " + event.getNewValue());
    ServiceUser su = new ServiceUser();
    su.update(user);
});
     userVille.setCellFactory(TextFieldTableCell.<User>forTableColumn());
     userVille.setOnEditCommit(event -> {
    User user = event.getRowValue();
    user.setVille(event.getNewValue());
    System.out.println("La ville de " + user.getNom() + " a été mise à jour à " + event.getNewValue());
    ServiceUser su = new ServiceUser();
    su.update(user);
});
     userNumero.setCellFactory(TextFieldTableCell.<User>forTableColumn());
     userNumero.setOnEditCommit(event -> {
    User user = event.getRowValue();
    user.setNumeroTelephone(event.getNewValue());
    System.out.println("Le numéro de téléphone de " + user.getNom() + " a été mis à jour à " + event.getNewValue());
    ServiceUser su = new ServiceUser();
    su.update(user);
});

   
  
}

    @FXML
    private void deconnexionAction(ActionEvent event) {
        
        try {
        // Charger la vue FXML de l'interface Ajout_user
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenir la scène actuelle (à partir du bouton cliqué)
        Stage currentStage = (Stage) addPartsButton.getScene().getWindow();

        // Remplacer la scène actuelle par la nouvelle scène (Ajout_user)
        currentStage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }



  
    } 


  
    

