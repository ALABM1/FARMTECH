package tn.edu.esprit.gui;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.edu.esprit.entities.Message;
import tn.edu.esprit.helpers.TableUpdater;
import tn.edu.esprit.helpers.UIHelper;
import tn.edu.esprit.services.MessageService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class GetAllMessageController implements Initializable {

    @FXML
    public TableView<Message> fxTableMessage;
    @FXML
    public TableColumn<Message, Integer> fxId;
    @FXML
    public TableColumn<Message, String> fxText;
    @FXML
    public TableColumn<Message, String> fxDestinataire;
    @FXML
    public TableColumn<Message, String> fxSource;
    @FXML
    public TableColumn<Message, String> fxDate;
    @FXML
    public TableColumn<Message, Void> fxEdit;
    @FXML
    public TextField fxTextChercher;
    @FXML
    public Label fxNotFound;
    @FXML
    public Label fxmessage;

    private MessageService messageService = new MessageService();
    private UIHelper uIHelper = new UIHelper();
    private TableUpdater<Message> tableUpdater = new TableUpdater<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableColumns();
        fxAfficher(null);
    }

    private void initializeTableColumns() {
        fxId.setCellValueFactory(new PropertyValueFactory<>("id"));
        fxText.setCellValueFactory(new PropertyValueFactory<>("text"));
        fxDestinataire.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestinataire().getNom() + ' ' + cellData.getValue().getDestinataire().getPrenom()));
        fxSource.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSource().getNom() + ' ' + cellData.getValue().getSource().getPrenom()));
        fxDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        fxEdit.setCellFactory(this::createEditButtonCell);
    }


    @FXML
    private void fxChercher(ActionEvent event) {
        String searchText = fxTextChercher.getText();
        List<Message> messages = messageService.searchMessages(searchText);
        tableUpdater.updateTableWithSearchResult(fxTableMessage, messages);
    }

    @FXML
    public void fxAfficher(ActionEvent event) {
        List<Message> messages = messageService.getAll();
        ObservableList<Message> messageList = FXCollections.observableArrayList(messages);
        fxTableMessage.setItems(messageList);
    }

    @FXML
    public void fxSupprimer(ActionEvent event) {
        boolean confirmed = UIHelper.showConfirmationDialog("Vous etes sure de vouloir supprimer ce message?", "Supprimer Message");
        if (confirmed) {
            tableUpdater.handleDeleteAction(fxTableMessage, messageService, fxmessage);
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
    public void fxGoToAddMessage(ActionEvent event) throws IOException {
        uIHelper.navigateTo("Message.fxml", event);
    }

    private TableCell<Message, Void> createEditButtonCell(TableColumn<Message, Void> param) {
      return new TableCell<Message, Void>() {
            private final Button btn = new Button("Edit");

            {
                btn.setOnAction(event -> {
                    Message message = getTableView().getItems().get(getIndex());
                    fxGoToEditMessage(event, message);
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        };
    }

    private void fxGoToEditMessage(ActionEvent event, Message message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root = loader.load();
            MessageController messageController = loader.getController();
            messageController.setExistingMessage(message);
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



