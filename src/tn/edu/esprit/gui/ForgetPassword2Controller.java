package tn.edu.esprit.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tn.edu.esprit.tools.DataSource;

public class ForgetPassword2Controller implements Initializable {

    @FXML
    private JFXTextField codeField;
    @FXML
    private JFXButton validerButton;
    @FXML
    private JFXTextField numberField;
    @FXML
    private JFXButton annulerButton;

    private int maxTries = 20; // Nombre maximum de tentatives
    private int remainingTries = maxTries; // Nombre de tentatives restantes

    public void setNumero(String numero) {
        numberField.setText(numero);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation, par exemple, vous pouvez afficher le nombre de tentatives restantes
        // Dans une étiquette sur l'interface utilisateur
        displayRemainingTries();
    }

    @FXML
    private void validerAction(ActionEvent event) {
        String codeSaisi = codeField.getText();
        String numero = numberField.getText();

        if (remainingTries > 0) {
            if (verifyResetCode(codeSaisi)) {
                loadForgetPassword3Screen();
                // Le code est correct, passez à l'interface ForgetPassword3
                // Vous pouvez implémenter le chargement de ForgetPassword3 ici
            } else {
                // Affichez un message d'erreur
                remainingTries--; // Réduisez le nombre de tentatives restantes
                displayRemainingTries();
                showErrorMessage("Code incorrect", "Le code saisi est incorrect. Il vous reste " + remainingTries + " tentatives.");
            }
        } else {
            // Bloquez l'accès après avoir dépassé le nombre de tentatives maximum
            showErrorMessage("Tentatives épuisées", "Vous avez dépassé le nombre maximum de tentatives. Veuillez réessayer plus tard.");
        }
    }

   private boolean verifyResetCode(String code) {
    try {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "SELECT reset_code FROM password_reset1 ORDER BY date DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String resetCode = resultSet.getString("reset_code");
            return code.equals(resetCode);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}







    public void showErrorMessage(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayRemainingTries() {
        // Vous pouvez afficher le nombre de tentatives restantes dans une étiquette
        // ou tout autre élément de l'interface utilisateur.
    }

    @FXML
    private void annulerAction(ActionEvent event) {
        // Chargez l'interface de connexion (sign in)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtenez la scène actuelle (la scène de l'interface de réinitialisation de mot de passe)
            Scene currentScene = validerButton.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérez l'exception correctement en cas d'erreur de chargement de la scène
        }
    }
    private void loadForgetPassword3Screen() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword3.fxml"));
        Parent root = loader.load();
        // Chargez l'interface ForgetPassword3 ici, si nécessaire
        Scene scene = new Scene(root);
        Stage stage = (Stage) validerButton.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
        // Gérez l'exception correctement en cas d'erreur de chargement de la scène
    }
}
}
