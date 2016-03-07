package trestview.table;

import entityProduction.Work;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.loader.XmlRW;
import trestview.hboxpane.HboxpaneModel;
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

    public TableViewP() {}

    public TableViewP(TableModel tableModel, TableController tableController) {
        this.tableModel = tableModel;
        XmlRW.fxmlLoad(this,tableController, "TableView.fxml","resources.ui", "stylesMenu.css");

        for (Object name : this.tableModel.getNameColumns()) {
            TableColumn tableColumn = new TableColumn(name.toString());
            getColumns().addAll(tableColumn);
            tableColumn.setCellValueFactory(new PropertyValueFactory<Object,String>(name.toString()));
        }
        repaintTable();
    }
    @Override
    public void update(Observable o, Object arg) {
        tableModel = (TableModel) o;
        repaintTable();
    }
    // Repaints table after the data changes
    public void repaintTable() {
        getItems().clear();
        data = FXCollections.observableArrayList();
        for (Object row : this.tableModel.getTab()) data.add(row);
        setItems(data);
    }
}


