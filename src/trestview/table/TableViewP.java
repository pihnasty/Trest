package trestview.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import trestview.table.tablemodel.TableModel;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TableViewP<cL> extends TableView implements Observer {

    private TableModel tableModel;
    private ObservableList data;


    public TableViewP() {
    }

    public TableViewP(TableModel tableModel, TableController tableController) {
        this.tableModel = tableModel;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        //   getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(tableController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
        // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        data = FXCollections.observableArrayList();
        for (Object row : this.tableModel.getTab()) data.add(row);

        setItems(data);

        for (Object name : this.tableModel.getNameColumns()) {
            TableColumn tableColumn = new TableColumn(name.toString());
            getColumns().addAll(tableColumn);
            tableColumn.setCellValueFactory(new PropertyValueFactory(name.toString()));

        }



/*
        private final ObservableList<Person> data =
                FXCollections.observableArrayList(
                        new Person("Jacob", "Smith", "jacob.smith@example.com"),
                        new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                        new Person("Ethan", "Williams", "ethan.williams@example.com"),
                        new Person("Emma", "Jones", "emma.jones@example.com"),
                        new Person("Michael", "Brown", "michael.brown@example.com")
                );


*/


    }


    @Override
    public void update(Observable o, Object arg) {
        tableModel = (TableModel) o;
        System.out.println("New model");
    }
}
