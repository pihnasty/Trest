package trestview.hboxpane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import resources.images.icons.IconT;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class HboxpaneView extends HBox implements Observer {



    private HboxpaneModel hboxpaneModel;


    public HboxpaneView() {
    }

    public HboxpaneView(HboxpaneModel hboxpaneModel, HboxpaneController hboxpaneController ) {
        this.hboxpaneModel =hboxpaneModel;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hboxpaneView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
     //   getStylesheets().add((getClass().getResource("stylesHboxPane.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( hboxpaneController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        hboxpaneModel = (HboxpaneModel)o;
        System.out.println("New model HBox");
    }
}
