package ui.rootPane.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuController implements Initializable {

    @FXML
    private Menu fileMenu;

    @FXML   private MenuItem newItem;
    @FXML   private MenuItem openItem;
    @FXML   private MenuItem saveItem;
    @FXML   private MenuItem saveAsItem;
    @FXML   private MenuItem saveAllItem;
    @FXML   private MenuItem exitItem;

    @FXML   private MenuItem cutItem;
    @FXML   private MenuItem copyItem;
    @FXML   private MenuItem pasteItem;

    @FXML   private MenuItem defaultPerspectiveItem;
    @FXML   private MenuItem ordersPerspectiveItem;
    @FXML   private MenuItem routesPerspectiveItem;
    @FXML   private MenuItem machinesLocationPerspectiveItem;


    @FXML
    private void handleExitAction(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAsItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        saveAllItem.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

        cutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));

        defaultPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        ordersPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        routesPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        machinesLocationPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
    }
}
