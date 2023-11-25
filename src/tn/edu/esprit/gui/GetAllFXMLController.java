package tn.edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import tn.edu.esprit.entities.Materiel;
import tn.edu.esprit.entities.Parc;
import tn.edu.esprit.services.ExporterPDF;
import tn.edu.esprit.services.MaterielGraph;
import tn.edu.esprit.services.ServiceMateriel;
import tn.edu.esprit.services.ServiceParc;

public class GetAllFXMLController implements Initializable {

    @FXML
    private TableView<Parc> fxTableParc;
    @FXML
    private TableColumn<Parc, String> fxNom;
    @FXML
    private TableColumn<Parc, String> fxAdresse;
    @FXML
    private TableColumn<Parc, Float> fxSuperficie;

    private List<Parc> data;
    @FXML
    private TextField fxtextchercher;
        ServiceParc sp = new ServiceParc();
    @FXML
    private Button fxTransferButton;
    private Parc selectedParc;
    @FXML
    private Button fxAjouterduMat;
    @FXML
    private Button fxsupp;
    

    
   @Override
public void initialize(URL url, ResourceBundle rb) {
    fxAfficher(new ActionEvent());
    //fxChercher(new ActionEvent()); 
    editData();
     fxTableParc.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            fxsupp.setVisible(true);
        } else {
            fxsupp.setVisible(false);
        }
  });
    

    fxTableParc.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            fxTransferButton.setVisible(true);
        } else fxTransferButton.setVisible(false);
              });
        fxTableParc.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Un élément a été sélectionné
            fxAjouterduMat.setVisible(true);
        } else fxAjouterduMat.setVisible(false);

        // Set the selectedParc here
        selectedParc = newSelection;
    });
}




    @FXML
    private void fxAfficher(ActionEvent event) {
        ServiceParc sp = new ServiceParc();
        data = sp.getAll(); // Assurez-vous que votre ServiceParc retourne une List<Parc>

        fxTableParc.setItems(FXCollections.observableArrayList(data));
        
        fxNom.setCellValueFactory(new PropertyValueFactory<Parc , String>("nomParc"));
        fxAdresse.setCellValueFactory(new PropertyValueFactory<Parc , String>("adresseParc"));
        fxSuperficie.setCellValueFactory(new PropertyValueFactory<Parc , Float>("SuperficieParc"));

    }

   
@FXML
private void fxSupprimer(ActionEvent event) {
    ServiceParc sp = new ServiceParc();
    Parc parcSelectionne = fxTableParc.getSelectionModel().getSelectedItem();

    if (parcSelectionne != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce parc ?");

        // Options de boutons
        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non");
        alert.getButtonTypes().setAll(boutonOui, boutonNon);

        // Récupérer la réponse de l'utilisateur
        alert.showAndWait().ifPresent(reponse -> {
            if (reponse == boutonOui) {
                // Utilisateur a cliqué sur "Oui", donc supprimez l'élément
                sp.supprimer(parcSelectionne.getIdParc());

                // Mettez à jour la TableView
                data.remove(parcSelectionne);
                fxTableParc.getItems().setAll(data);
            }
        });
    } else {
        System.out.println("Vous devez sélectionner un élément avant de le supprimer.");
    }
}
    
private void editData() {
    // Éditer le nom du parc
    fxNom.setCellFactory(TextFieldTableCell.<Parc>forTableColumn());
    fxNom.setOnEditCommit(event -> {
        Parc parc = event.getRowValue(); 
        parc.setNomParc(event.getNewValue());
        System.out.println("Le nom de " + parc.getNomParc() + " a été mis à jour à " + event.getNewValue());
        sp.modifier(parc);
    });

    // Éditer l'adresse du parc
    fxAdresse.setCellFactory(TextFieldTableCell.<Parc>forTableColumn());
    fxAdresse.setOnEditCommit(event -> {
        Parc parc = event.getRowValue();
        parc.setAdresseParc(event.getNewValue());
        System.out.println("L'adresse de " + parc.getAdresseParc() + " a été mise à jour à " + event.getNewValue());
        sp.modifier(parc);
    });

    // Éditer la superficie du parc
    fxSuperficie.setCellFactory(TextFieldTableCell.<Parc, Float>forTableColumn(new FloatStringConverter()));
    fxSuperficie.setOnEditCommit(event -> {
        Parc parc = event.getRowValue();
        float newValue = event.getNewValue();
        parc.setSuperficieParc(newValue);

        System.out.println("La superficie de " + parc.getSuperficieParc() + " a été mise à jour à " + newValue);

        sp.modifier(parc);
    });
}
    



@FXML
private void fxChercher(ActionEvent event) {
   String nomCherche = fxtextchercher.getText(); // Récupérer le texte du champ de recherche

   // Appeler la méthode getOne avec le nom cherché
   Parc parc = sp.getOne(nomCherche);

   if (parc != null) {
       // Mettre à jour la TableView avec les détails du parc trouvé
       fxTableParc.getItems().setAll(parc);
   } else {
       showAlert(AlertType.ERROR, "Aucun Résultat", "Aucun parc trouvé avec ce nom.");
   }
}

private void showAlert(AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

     

@FXML
private void fxTranfserpage(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/TableViewMateriel.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Parc selectedParc = fxTableParc.getSelectionModel().getSelectedItem();
        if (selectedParc != null) {
        // Set the selected parc's name to the test TextField
        TableViewMaterielController t = loader.getController();
        t.initData(selectedParc);
        SelectedParcManager.setSelectedParc(selectedParc);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Liste de materiels");
        stage.show();
        } else {
            // Aucun parc sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun parc sélectionné.\nVeuillez séelectionner une parc de la liste.");
            alert.showAndWait();
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

 @FXML
private void fxAjouterduMat(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterMaterielFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Parc selectedParc = fxTableParc.getSelectionModel().getSelectedItem();

        if (selectedParc != null) {
            SelectedParcManager.setSelectedParc(selectedParc);

            AjouterMaterielFXMLController controller = loader.getController();
            controller.initData(selectedParc);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Ajouter materiel ");
            stage.show();
        } else {
            // Aucun parc sélectionné, afficher une alerte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun parc sélectionné.\nVeuillez séelectionner une parc de la liste.");
            alert.showAndWait();
        }
    } catch (IOException ex) {
        System.out.println("Erreur lors du chargement de l'interface utilisateur : " + ex.getMessage());
    }
}

    @FXML
    private void fxMenuGetALL(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/WelcomePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GetAllFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fxGoToAddParc(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterParcFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMaterielFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fxGoToAllMateriel(ActionEvent event) {
             
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/TableAllMateriel.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Liste de materiels");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterMaterielFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
@FXML
private void fxexportToPDF(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation d'exportation PDF");
    alert.setHeaderText("Voulez-vous exporter le PDF ?");
    alert.setContentText("Êtes-vous sûr de vouloir exporter le PDF ?");

    ButtonType buttonTypeOui = new ButtonType("Oui");
    ButtonType buttonTypeNon = new ButtonType("Non");

    alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

    alert.showAndWait().ifPresent(response -> {
        if (response == buttonTypeOui) {
            ServiceParc sp = new ServiceParc();
            List<Parc> parcs = sp.getAll();
            ExporterPDF pdf = new ExporterPDF();
            pdf.exportToPDF(parcs);
        }
    });

    }

    @FXML
    private void AfficherCourbe(ActionEvent event) {
        
          ServiceMateriel serviceMateriel = new ServiceMateriel();
          // Obtenez tous les matériaux
          List<Materiel> materiels = serviceMateriel.getAllMateriels();
          // Créez le graphique pour le nombre de matériaux par parc
          MaterielGraph graph = new MaterielGraph();
          JFreeChart chart = graph.createParcMaterielChart(materiels);
          // Créez le panneau de graphique
          ChartPanel chartPanel = graph.createChartPanel(chart);
          // Affichez le graphique dans une fenêtre
          javax.swing.JFrame frame = new javax.swing.JFrame("Nombre de Matériaux par Parc");
          frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
          frame.getContentPane().add(chartPanel);
          frame.pack();
          frame.setVisible(true);
    }
}









