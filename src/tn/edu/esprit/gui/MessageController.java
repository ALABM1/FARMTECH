package tn.edu.esprit.gui;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import tn.edu.esprit.entities.Message;
import tn.edu.esprit.entities.User;
import tn.edu.esprit.services.MessageService;
import tn.edu.esprit.helpers.UIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tn.edu.esprit.services.ServiceUser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


public class MessageController {

    @FXML
    public TextField textTextField;

    @FXML
    public ComboBox<User> destinataireComboBox;
    @FXML
    public ComboBox<User> sourceComboBox;

    @FXML
    public DatePicker datePicker;

    private Message existingMessage;
    private final MessageService messageService = new MessageService();
    private final UIHelper uIHelper = new UIHelper();
    private ServiceUser serviceUser = new ServiceUser();

    public MessageController(){
    }

    @FXML
    public void initialize() {
        List<User> users = serviceUser.getAll();
        destinataireComboBox.setItems(FXCollections.observableArrayList(users));
        sourceComboBox.setItems(FXCollections.observableArrayList(users));

        destinataireComboBox.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNom() + ' ' + item.getPrenom());
            }
        });
        sourceComboBox.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNom() + ' ' + item.getPrenom());
            }
        });

        destinataireComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getNom() + ' ' + user.getPrenom() : "";
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });
        sourceComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getNom() + ' ' + user.getPrenom() : "";
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });
    }

    public void setExistingMessage(Message message) {
        this.existingMessage = message;
        populateFields(message);
    }

    private void populateFields(Message message) {
        textTextField.setText(message.getText());
        destinataireComboBox.setValue(message.getDestinataire());
        sourceComboBox.setValue(message.getSource());
        datePicker.setValue(LocalDate.parse(message.getDate()));
    }


    @FXML
    public void handleAction(ActionEvent event) {
        String text = textTextField.getText();
        User destinataire = destinataireComboBox.getValue();
        User source = sourceComboBox.getValue();
        String dateText = datePicker.getValue().toString();

        if (!isValidInput(text, destinataire, source)) {
            return;
        }

        java.sql.Date sqlDate = parseDate(dateText);
        if (sqlDate == null) {
            return;
        }

        Message message = existingMessage != null ? existingMessage : new Message();
        message.setText(text);
        message.setDestinataire(destinataire);
        message.setSource(source);
        message.setDate(sqlDate.toString());

        try {
            if (existingMessage != null) {
                messageService.update(message);
            } else {
                messageService.create(message);
                
            }
            UIHelper.showAlert("Message traiter avec succer", "Success", Alert.AlertType.INFORMATION);
            uIHelper.navigateTo("GetAllMessage.fxml", event);
        } catch (Exception ex) {
            UIHelper.showAlert("Un erreur est survenu lors l'enregistrement du message.", "Error", Alert.AlertType.ERROR);
        }
    }



    private boolean isValidInput(String text, User destinataire, User source) {

        if (text.isEmpty() || destinataire == null || source == null) {
            UIHelper.showAlert("Text, Destinataire, et Source sont des champs obligatoires.", "Error", Alert.AlertType.ERROR);
            return false;
        }

        if (UIHelper.containsNumbers(text)) {
            UIHelper.showAlert("Text, Destinataire, et Source ne doivent pas contenir de chiffres.", "Error", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private java.sql.Date parseDate(String dateText) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(dateText);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            UIHelper.showAlert("Format du date est incorrect.", "Error", Alert.AlertType.ERROR);
            return null;
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        try {
            uIHelper.navigateTo("GetAllMessage.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
