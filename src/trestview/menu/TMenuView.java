package trestview.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import java.io.IOException;
import java.util.ResourceBundle;

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

    public TMenuView(TMenuModel menuModel, TMenuController menuController ) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tMenuView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( menuController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
