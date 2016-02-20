package trestui.rootPane.menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCombination;
import trestui.Controller;
import java.io.IOException;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuView extends MenuBar  {

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem newItem;

    @FXML
    private Menu OpenPerspective;

    public TMenuView() {
    }

    public TMenuView(TMenuModel menuModel) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tMenuView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        //   fxmlLoader.setController(this);        // or  fx:controller="ui.rootPane.menu.TMenuController"
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
