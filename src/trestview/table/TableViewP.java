package trestview.table;

import javafx.application.Platform;
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
public class TableViewP extends TableView implements Observer {

    private TableModel tableModel;



    public TableViewP() {
    }

    public TableViewP(TableModel tableModel, TableController tableController ) {
        this.tableModel =tableModel;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
     //   getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( tableController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }










    @Override
    public void update(Observable o, Object arg) {
        tableModel = (TableModel)o;
        System.out.println("New model");
    }
}
