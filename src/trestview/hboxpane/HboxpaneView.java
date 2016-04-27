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
import persistence.loader.XmlRW;
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
    public HboxpaneView() {}

    public HboxpaneView(HboxpaneModel hboxpaneModel, HboxpaneController hboxpaneController ) {
        this.hboxpaneModel =hboxpaneModel;
        FXMLLoader fxmlLoader =   XmlRW.fxmlLoad(this,hboxpaneController, "hboxpaneView.fxml","resources.ui", "");
     //   getStylesheets().add((getClass().getResource("stylesHboxPane.css")).toExternalForm());
    }

    @Override
    public void update(Observable o, Object arg) {   hboxpaneModel = (HboxpaneModel)o;  }
}
