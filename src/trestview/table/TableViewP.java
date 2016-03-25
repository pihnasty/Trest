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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowIdId2;
import persistence.loader.tabDataSet.RowIdNameDescription;
import persistence.loader.tabDataSet.RowWork;
import resources.images.icons.IconT;
import resources.images.works.WorkT;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;
import trestview.table.tablemodel.TableModel;
import trestview.table.tablemodel.abstracttablemodel.ParametersColumn;

import java.io.File;
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
    private cL selectRow;

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

        this.tableModel.getParametersOfColumns().stream().map(p-> getTableColumnP((ParametersColumn)p)).count();

        isEditable();

        setPrefHeight(300);
        repaintTable();
        getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());

    }

    private void setStringColumn(ParametersColumn parametersColumn, TableColumn<cL, String> tableColumn,String fielgName, Class tclass ) {
        if(parametersColumn.getFielgName().equals(fielgName)) {
            tableColumn.setCellValueFactory(new PropertyValueFactory(fielgName));
            tableColumn.setCellFactory(TextFieldTableCell.<cL>forTableColumn());
            tableColumn.setOnEditCommit(  (TableColumn.CellEditEvent<cL, String> t) -> {
              //if(tclass==RowWork.class)
              if(fielgName=="name") ((RowIdNameDescription) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setName(t.getNewValue());
              if(fielgName=="scheme") {

                  File f = new File(t.getNewValue());
                  String schemePath = "Image\\Manufacturing";

                  if (!f.exists()) {
                      FileChooser chooser = new FileChooser();
                      chooser.setInitialDirectory(new File (schemePath));
                      chooser.getExtensionFilters().addAll(
                              new FileChooser.ExtensionFilter("All Images", "*.*"),
                              new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                              new FileChooser.ExtensionFilter("PNG", "*.png"),
                              new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                              new FileChooser.ExtensionFilter("GIF", "*.gif")
                      );
                     chooser.setTitle( ResourceBundle.getBundle("resources.ui").getString("nameFileScheme"));
                      Stage st = new Stage();
                      st.setMaxWidth(400);
                      st.setMaxHeight(400);
                      f = chooser.showOpenDialog(st);
                      Image image = new Image(f.toURI().toString()); System.out.println(image.getHeight());

                      try                      {

                          System.out.println(f.getAbsolutePath());
                          t.getOldValue();
                          System.out.println( "t.getOldValue()="+ t.getOldValue());
                      }
                     catch (Exception e) {
                        System.out.println(e.getClass());
                     }

                  }



                  ((RowWork) t.getTableView().getItems().get(t.getTablePosition().getRow())).setScheme(t.getNewValue());
              }
              if(fielgName=="description") ((RowIdNameDescription) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setDescription(t.getNewValue());
            });
        }
    }

    private void setIntegerColumn(ParametersColumn parametersColumn, TableColumn<cL, Integer> tableColumn,String fielgName, Class tclass ) {
        if(parametersColumn.getFielgName().equals(fielgName)) {
            tableColumn.setCellValueFactory(new PropertyValueFactory(fielgName));
            tableColumn.setCellFactory(TextFieldTableCell.<cL, Integer>forTableColumn(new IntegerStringConverter()));
            tableColumn.setOnEditCommit(  (TableColumn.CellEditEvent<cL, Integer> t) -> {
            if(fielgName=="id")   ((RowIdNameDescription) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setId(t.getNewValue());
            });
        }
    }

    private void setDoubleColumn(ParametersColumn parametersColumn, TableColumn<cL, Double> tableColumn,String fielgName, Class tclass ) {
        if(parametersColumn.getFielgName().equals(fielgName)) {
            tableColumn.setCellValueFactory(new PropertyValueFactory(fielgName));
            tableColumn.setCellFactory(TextFieldTableCell.<cL, Double>forTableColumn(new DoubleStringConverter()));
            tableColumn.setOnEditCommit(  (TableColumn.CellEditEvent<cL, Double> t) -> {
            if(fielgName=="overallSize")   ((RowWork) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setOverallSize(t.getNewValue());
            if(fielgName=="scaleEquipment")   ((RowWork) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setScaleEquipment(t.getNewValue());
            });
        }
    }
    private void setImageColumn(ParametersColumn parametersColumn, TableColumn<cL, String> tableColumn, String fielgName, Class tclass ) {
        if(parametersColumn.getFielgName().equals(fielgName)) {
            tableColumn.setCellValueFactory(new PropertyValueFactory("scheme"));
            tableColumn.setCellFactory(
              new Callback<TableColumn<cL, String>,TableCell<cL, String>>(){
                @Override
                public TableCell<cL, String> call(TableColumn<cL, String> param) {
                    TableCell<cL, String> cell = new TableCell<cL, String>(){
                        @Override
                        public void updateItem(String item, boolean empty) {
                            if(item!=null){
                                HBox box= new HBox();
                                box.setSpacing(10) ;
                                ScrollPane sp = new ScrollPane();
                                sp.setPrefSize(160,160);
                                ImageView imageview = new ImageView();
                                imageview.setFitHeight(300);
                                sp.setPannable(false);
                              //  imageview.setFitWidth(300);
                                imageview.setImage(new Image("file:"+item ));
                                box.getChildren().addAll(imageview);
                                sp.setContent(imageview);
                                setGraphic(sp);
                            }
                        }
                    };
                    System.out.println(cell.getIndex());
                    return cell;
                }

            });


            //   tableColumn.setCellFactory(TextFieldTableCell.<cL, Integer>forTableColumn(new IntegerStringConverter()));
       //     tableColumn.setOnEditCommit(  (TableColumn.CellEditEvent<cL, Image> t) -> {
         //       if(fielgName=="image")   ((RowIdNameDescription) t.getTableView().getItems().get( t.getTablePosition().getRow()) ).setId(t.getNewValue());
           // });
        }
    }


    private TableColumn<cL, ?> getTableColumnP(ParametersColumn parametersColumn) {

        TableColumn<cL,?> tableCol = new TableColumn<>();

        if (parametersColumn.getcLs()==String.class) {
            TableColumn<cL,String> tableColumn = new TableColumn  (parametersColumn.getName());
            setStringColumn(parametersColumn, tableColumn,"name",tclass);
            setStringColumn(parametersColumn, tableColumn,"scheme",tclass);
            setStringColumn(parametersColumn, tableColumn,"description",tclass);
            tableCol=tableColumn;
        }

        if (parametersColumn.getcLs()==int.class) {
            TableColumn<cL,Integer> tableColumn = new TableColumn  (parametersColumn.getName());
            setIntegerColumn(parametersColumn, tableColumn,"id",tclass);
            tableCol=tableColumn;
        }
        if (parametersColumn.getcLs()==double.class) {
            TableColumn<cL,Double> tableColumn = new TableColumn  (parametersColumn.getName());
            setDoubleColumn(parametersColumn, tableColumn,"overallSize",tclass);
            setDoubleColumn(parametersColumn, tableColumn,"scaleEquipment",tclass);
            tableCol=tableColumn;
        }
        if (parametersColumn.getcLs()==Image.class) {
            TableColumn<cL,String> tableColumn = new TableColumn  (parametersColumn.getName());
            setImageColumn(parametersColumn, tableColumn,"image",tclass);
            tableCol=tableColumn;
        }


        getColumns().addAll(tableCol);
         tableCol.setMinWidth(parametersColumn.getWidth());
         tableCol.setPrefWidth(parametersColumn.getWidth());
         tableCol.setEditable(parametersColumn.isEditable());


         setPrefWidth(getPrefWidth() + parametersColumn.getWidth());
         setMinWidth(getMinWidth() + parametersColumn.getWidth());
            //  setMinWidth(getMinWidth()+parametersColumn.getWidth());

        return tableCol;
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
            selectRow = (cL)data.get(selectIndex);
            tableModel.setSelectRow(selectRow );
        }
    }

    private class CellEditEvent<T, T1> {
    }
}


