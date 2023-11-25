/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author aladi
 */
public class DisplayQRController implements Initializable {

    @FXML
    private ImageView qrImageView;
   
    private Image qrImage; // Stocker l'image du code QR
    @FXML
    private Button loginButton;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     // Méthode pour afficher l'image du code QR dans qrImageView
    public void displayQRImage(Image image) {
        qrImage = image;
        qrImageView.setImage(qrImage);
    }

   @FXML
    private void telechargerAction(ActionEvent event) {
        if (qrImage != null) {
            // Ouvrez une boîte de dialogue pour choisir l'emplacement de sauvegarde
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Convertissez l'image du code QR en BufferedImage
                BufferedImage bImage = SwingFXUtils.fromFXImage(qrImage, null);

                try {
                    // Enregistrez l'image du code QR dans le fichier spécifié
                    ImageIO.write(bImage, "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        ((Stage) loginButton.getScene().getWindow()).close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
   
}