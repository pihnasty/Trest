package ui.rootPane.menu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuView extends MenuBar {

    @FXML
    private Menu OpenPerspective;

    @FXML
    private MenuItem newItem;

    public TMenuView() {
    }

    public TMenuView(TMenuModel menuModel) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tMenuView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);




        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }





        newItem = new MenuItem();

        /**/



        newItem.setGraphic(new ImageView(
                        new Image (
                                getClass().getResource("new.png").toExternalForm())
                )
        );


        System.out.println(newItem.getGraphic()+"------------------------------------------------------------------------");





    }
}
