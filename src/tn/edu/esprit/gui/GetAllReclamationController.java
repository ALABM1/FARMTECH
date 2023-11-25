package tn.edu.esprit.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Reclamations;
import tn.edu.esprit.helpers.TableUpdater;
import tn.edu.esprit.helpers.UIHelper;
import tn.edu.esprit.services.ReclamationService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GetAllReclamationController implements Initializable {

    @FXML
    public TableView<Reclamations> fxTableReclamation;
    @FXML
    public TableColumn<Reclamations, Integer> fxId;
    @FXML
    public TableColumn<Reclamations, String> fxType;
    @FXML
    public TableColumn<Reclamations, String> fxDescription;
    @FXML
    public TableColumn<Reclamations, String> fxEmail;
    @FXML
    public TableColumn<Reclamations, String> fxTelephone;
    @FXML
    public TableColumn<Reclamations, Void> fxEdit;
    @FXML
    public TextField fxTextChercher;
    @FXML
    public Label fxNotFound;
    @FXML
    public Label fxReclamation;

    private ReclamationService reclamationService = new ReclamationService();
    private UIHelper uIHelper = new UIHelper();
    private TableUpdater<Reclamations> tableUpdater = new TableUpdater<>();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableColumns();
        fxAfficher(null);
    }

    private void initializeTableColumns() {
        fxId.setCellValueFactory(new PropertyValueFactory<>("id"));
        fxType.setCellValueFactory(new PropertyValueFactory<>("type"));
        fxDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        fxEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        fxTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        fxEdit.setCellFactory(this::createEditButtonCell);
    }

/*
    @FXML
    private void fxChercher(ActionEvent event) {
        String searchText = fxTextChercher.getText();
        List<Reclamations> reclamations = reclamationService.searchReclamations(searchText);
        tableUpdater.updateTableWithSearchResult(fxTableReclamation, reclamations);
    }
*/

    @FXML
    public void fxAfficher(ActionEvent event) {
        List<Reclamations> reclamations = reclamationService.getAll();
        ObservableList<Reclamations> reclamationList = FXCollections.observableArrayList(reclamations);
        fxTableReclamation.setItems(reclamationList);
    }

    @FXML
    public void fxSupprimer(ActionEvent event) {
        boolean confirmed = UIHelper.showConfirmationDialog("Vous etes sure de vouloir supprimer cette reclamation?", "Supprimer Reclamation");
        if (confirmed) {
            tableUpdater.handleDeleteAction(fxTableReclamation, reclamationService, fxReclamation);
        }
    }

    @FXML
    public void fxMenuGetALL(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Stage stage = (Stage) sourceButton.getScene().getWindow();

        MenuItem messageListMenuItem = new MenuItem("Message List");
        messageListMenuItem.setOnAction(e -> navigateTo("GetAllMessage.fxml", stage));

        MenuItem reclamationListMenuItem = new MenuItem("Reclamation List");
        reclamationListMenuItem.setOnAction(e -> navigateTo("GetAllReclamation.fxml", stage));

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(messageListMenuItem, reclamationListMenuItem);

        contextMenu.show(sourceButton, Side.BOTTOM, 0, 0);


        contextMenu.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> contextMenu.hide());
    }

    @FXML
    public void fxGoToAddReclamation(ActionEvent event) throws IOException {
        uIHelper.navigateTo("Reclamation.fxml", event);
    }

    private TableCell<Reclamations, Void> createEditButtonCell(TableColumn<Reclamations, Void> param) {
        return new TableCell<Reclamations, Void>() {
            private final Button btn = new Button("Edit");

            {
                btn.setOnAction(event -> {
                    Reclamations reclamation = getTableView().getItems().get(getIndex());
                    fxGoToEditReclamation(event, reclamation);
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        };
    }

    private void fxGoToEditReclamation(ActionEvent event, Reclamations reclamation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
            Parent root = loader.load();
            ReclamationController reclamationController = loader.getController();
            reclamationController.setExistingReclamation(reclamation);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateTo(String fxmlPath, Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/" + fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retourreclamation(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/WPage.fxml"));
        Parent root = loader.load();

        // Obtenez la scène actuelle à partir de l'événement
        Scene scene = ((Node) event.getSource()).getScene();

        // Mettez la nouvelle scène dans la fenêtre principale (Stage)
        Stage stage = (Stage) scene.getWindow();
        stage.setScene(new Scene(root));

    } catch (IOException e) {
        e.printStackTrace();
    }
    }


}
