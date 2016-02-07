package ui.rootPane.menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuView extends MenuBar {

    @FXML
    private Menu OpenPerspective;

    public TMenuView() {
    }

    public TMenuView(TMenuModel menuModel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tMenuView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


    }
}
