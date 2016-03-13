package trestview.table;

import entityProduction.Work;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowIdId2;
import persistence.loader.tabDataSet.RowIdNameDescription;
import persistence.loader.tabDataSet.RowWork;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;
import trestview.table.tablemodel.TableModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TableViewP<cL> extends TableView<cL> implements Observer {

    private TableModel tableModel;
    private Property<TableModel> tableModelProperty;
    private ObservableList data;
    private int selectIndex = 0;
    private MethodCall methodCall;
    private ArrayList<cL> tab;
    private Class tclass;
    private DataSet dataSet;

    public TableViewP() {}

    public TableViewP(TableModel tableModel, TableController tableController) {

        this.tableModel = tableModel;
        this.tab= tableModel.getTab();
        this.tclass=tableModel.gettClass();
        this.dataSet = tableModel.getDictionaryModel().getTMenuModel().getTrestModel().getDataSet();
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.setTableMenuButtonVisible(true);
        this.setEditable(true);
        XmlRW.fxmlLoad(this,tableController, "TableView.fxml","resources.ui", "stylesMenu.css");

        for (Object name : this.tableModel.getNameColumns()) {


            TableColumn<cL, String> tableColumn = getTableColumnP(name);
        }


        System.out.println("isEditable()="+isEditable());
        setPrefWidth(850);
        setPrefHeight(300);
        repaintTable();
        getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());

    }

    private TableColumn<cL, String> getTableColumnP(Object name) {
        TableColumn<cL,String> tableColumn = new TableColumn  (name.toString());


        getColumns().addAll(tableColumn);
        if(name.toString()=="name")
            tableColumn.setCellValueFactory(new PropertyValueFactory(name.toString()));

        tableColumn.setCellFactory(TextFieldTableCell.<cL>forTableColumn());

        tableColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<cL, String> t) -> {
                    ((RowWork) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                });
        tableColumn.setEditable(true);
        return tableColumn;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.getSelectionModel().getSelectedIndex();
        updateTableModel((TableModel) o);
        this.requestFocus();
        this.getSelectionModel().select(selectIndex);
        this.getFocusModel().focus(selectIndex);
    }
    // Repaints table after the data changes
    public void repaintTable() {
        getItems().clear();
        data = FXCollections.observableArrayList();
        for (Object row : this.tab) data.add(row);
        setEditable(true);
        setItems(data);






    }

    private void updateTableModel(TableModel o) {
        switch (o.getMethodCall()) {
            case addRowTable:
                selectIndex++;
                data.add(selectIndex,((TableModel)o).getSelectRow() );
                break;
            case saveRowTable:
                //tableModel.getDictionaryModel().getTMenuModel().getTrestModel().getDataSet().saveDataset(); // Save new path add read new database  from new directory path
                break;
            case editRowTable:
                this.setEditable(true);
                System.out.println("methodCall = MethodCall.editRowTable;22");
                break;
            case delRowTable:
                if (tclass == RowWork.class )  XmlRW.delRow ((cL)data.get(selectIndex), tab, dataSet.getTabWorks(), dataSet.getTabTrestsWorks());
                data.remove(selectIndex);
                if (getItems().size() == 0) {   return;   }
                break;
            default:
                break;
        }
    }







    private class RowSelectChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
            selectIndex = newVal.intValue();
            if ((selectIndex < 0) || (selectIndex >= data.size())) { return;  }
            setEditable(true);
        }
    }

    private class CellEditEvent<T, T1> {
    }
}


