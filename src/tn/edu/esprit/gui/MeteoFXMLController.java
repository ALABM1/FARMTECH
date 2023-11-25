package tn.edu.esprit.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.net.MalformedURLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.edu.esprit.services.Servicechatgpt;

public class MeteoFXMLController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField question;
    @FXML
    private Label reponse;

  @FXML
    private void backcatgtra(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../gui/tablecatFXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("catégories");
        stage.show();
    
    
    }
     @FXML
    private void getQestion(ActionEvent event) throws IOException {
        Servicechatgpt Servicechatgpt = new Servicechatgpt();

        reponse.setText("");
        if (question.getText().isEmpty()) {
        reponse.setText("Entrez votre question");
        return;
        }else{
        String textInput= question.getText();
        reponse.setText(Servicechatgpt.chatGPT(textInput));        
        }
        
        
        //Transaction transaction = st.getOne(categ_tra);

    /*if (transaction != null) {
        // Mettre à jour la TableView avec les détails du parc trouvé
        if(transaction.getId_tra()!= 0){
            reponse.setText("transaction disponible");  
         } else {
           reponse.setText("transaction non disponible");}
    } else {
        System.out.println("Aucune transaction trouvée avec cet id.");
                tranotfound.setVisible(true);

    }   */
        
        
        
    }

}