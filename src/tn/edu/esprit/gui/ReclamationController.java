package tn.edu.esprit.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import tn.edu.esprit.entities.Reclamations;
import tn.edu.esprit.helpers.UIHelper;
import tn.edu.esprit.services.*;
import tn.edu.esprit.services.SendMailReclamation;

public class ReclamationController {

    @FXML
    public TextField typeTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField telephoneTextField;

    private Reclamations existingReclamation;
    private final ReclamationService reclamationService = new ReclamationService();
    private final UIHelper uIHelper = new UIHelper();

    public ReclamationController() {
    }



    public void setExistingReclamation(Reclamations reclamation) {
        this.existingReclamation = reclamation;
        populateFields(reclamation);
    }

    private void populateFields(Reclamations reclamation) {
        typeTextField.setText(reclamation.getType());
        descriptionTextField.setText(reclamation.getDescription());
        emailTextField.setText(reclamation.getEmail());
        telephoneTextField.setText(reclamation.getTelephone());
    }

    @FXML
    public void handleAction(ActionEvent event) {
        String type = typeTextField.getText();
        String description = descriptionTextField.getText();
        String email = emailTextField.getText();
        String telephone = telephoneTextField.getText();

        if (type.isEmpty() || description.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
            UIHelper.showAlert("All fields are required.", "Error", Alert.AlertType.ERROR);
            return;
        }

        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)) {
            UIHelper.showAlert("Invalid email format.", "Error", Alert.AlertType.ERROR);
            return;
        }

        Reclamations reclamation = existingReclamation != null ? existingReclamation : new Reclamations();
        reclamation.setType(type);
        reclamation.setDescription(description);
        reclamation.setEmail(email);
        reclamation.setTelephone(telephone);
        try {
            if (existingReclamation != null) {
                reclamationService.update(reclamation);
            } else {
                reclamationService.create(reclamation);
                smsSender2 sms = new smsSender2();
                sms.sendSms(telephone, description);  
                
                showNotification("une ajoute effectuée", "Nouvel reclamation ajouté" );
                SendMailReclamation mail = new SendMailReclamation();
                mail.envoyerEmailReclamation(reclamation);
            }
            UIHelper.showAlert("Reclamation processed successfully", "Success", Alert.AlertType.INFORMATION);
            uIHelper.navigateTo("GetAllReclamation.fxml", event);
        } catch (Exception ex) {
            UIHelper.showAlert("An error occurred while processing the reclamation.", "Error", Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }
    private void showNotification(String title, String text) {
    Notifications.create()
        .title(title)
        .text(text)
        .showInformation();
}

    @FXML
    public void handleCancel(ActionEvent event) {
        try {
            uIHelper.navigateTo("GetAllReclamation.fxml", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
