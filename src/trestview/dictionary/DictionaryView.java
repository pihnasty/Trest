package trestview.dictionary;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class DictionaryView extends Dialog implements Observer {

    private DictionaryModel dictionaryModel;



    public DictionaryView() {
    }

    public DictionaryView (DictionaryModel dictionaryModel, DictionaryController dictionaryController ) {
        this.dictionaryModel =dictionaryModel;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dictionaryView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
     //   getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( dictionaryController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        setTitle("Search");
        setHeaderText("Enter search parameters");




        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(searchButtonType,ButtonType.CANCEL);        // Create the layout for the controls.

        Optional<Pair<String, Boolean>> result = showAndWait();        // Display dialog box and wait for user response.

        // If the user closed the dialog box via the search button, output the
        // chosen search text and case-sensitive search status.



    }










    @Override
    public void update(Observable o, Object arg) {
        dictionaryModel = (DictionaryModel)o;
        System.out.println("New model");
    }
}
