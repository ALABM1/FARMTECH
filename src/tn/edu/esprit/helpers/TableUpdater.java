package tn.edu.esprit.helpers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import tn.edu.esprit.entities.IEntity;
import tn.edu.esprit.services.GenericService;
import javafx.scene.control.Label;
import java.util.List;
import java.util.Optional;

public class TableUpdater<T extends IEntity> {

    public void updateTableWithSearchResult(TableView<T> table, List<T> entities) {
        ObservableList<T> entityList = FXCollections.observableArrayList(entities);
        table.setItems(entityList);
    }

    public void handleDeleteAction(TableView<T> table, GenericService<T> service, Label feedbackLabel) {
        T selectedEntity = table.getSelectionModel().getSelectedItem();

        if (selectedEntity != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setHeaderText("Delete item?");
            confirmation.setContentText("Are you sure you want to delete this item?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.delete(selectedEntity.getId());
                table.getItems().remove(selectedEntity);
                feedbackLabel.setText("Item deleted");
            }
        } else {
            System.out.println("You need to select an item before deleting.");
            feedbackLabel.setText("Select an item");
        }
    }
}
