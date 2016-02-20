package trestui.rootPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import loader.DataSet;
import trestui.rootPane.menu.TMenuController;
import trestui.rootPane.menu.TMenuModel;
import trestui.rootPane.menu.TMenuView;


import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Sample custom control hosting a text field and a button.
 */
public class RootPane extends BorderPane {

    public RootPane() {
    }

    public RootPane(DataSet dataSet) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rootPane.fxml"));
     //   fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        TMenuController tMenuController = new TMenuController();
        TMenuModel menuModel = new TMenuModel();
        MenuBar tMenuView = new TMenuView(menuModel);
        this.setTop(tMenuView);
    }
}
