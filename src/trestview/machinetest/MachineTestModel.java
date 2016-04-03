package trestview.machinetest;

import entityProduction.Functiondist;
import entityProduction.Parametrfunctiondist;
import persistence.loader.DataSet;
import persistence.loader.tabDataSet.*;
import trestview.menu.TMenuModel;
import trestview.table.TableController;
import trestview.table.TableViewP;
import trestview.table.tablemodel.TableModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestModel extends Observable {

    private TMenuModel menuModel;
    private ArrayList<Double> randomValuesList;
    private DataSet dataSet;
    private TableViewP tableView;
//    private Class tClass;



    public MachineTestModel() {
        this.randomValuesList = new ArrayList<Double>();
    }
    public MachineTestModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        this.randomValuesList = new ArrayList<Double>();
        populateList(100);
//        this.tClass = tClass;
        this.dataSet =  menuModel.getTrestModel().getDataSet();

//-----------------------------------------------------------------------------------------------
        ArrayList<Functiondist> functiondists = new ArrayList<>();
        for (RowFunctiondist rowFun: dataSet.getTabFunctiondists()) functiondists.add(dataSet.createObject( rowFun));
        DataSet.showTab(functiondists);
        //   MVC tableMVC = new MVC(TableModel.class, TableController.class, TableViewP.class, dictionaryModel, dictionaryModel.gettClass());
        TableModel tableModel = new  TableModel(dataSet, functiondists );           //( TableModel)TableMolelBuilder.build(dictionaryModel, dictionaryModel.gettClass()); //  new TableModel(this.dictionaryModel.getTMenuModel().getTrestModel().getTrest().getWorks(), Work.class);
        TableController tableController = new TableController(tableModel);
        tableView = new TableViewP(tableModel, tableController);
        tableModel.addObserver(tableView);
//--------------------------------------------------------------------------------------------------


    }

    public TMenuModel getTMenuModel() {
        return menuModel;
    }

    public void setTMenuModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        changed();
    }

    public ArrayList<Double> getRandomValuesList() {
        return randomValuesList;
    }

    public void setRandomValuesList(ArrayList<Double> randomValuesList) {
        this.randomValuesList = randomValuesList;
    }


    //adds a value to the List
    public void addValue(double value) {
        this.randomValuesList.add(value);
    }

    //removes value from List
    public void removeValue(int index) {
        this.randomValuesList.remove(index);
    }

    //populate the List with a test data
    private void populateList(int limit) {
        for (double i = 0; i < limit; i++) {
            randomValuesList.add(Math.atan(i));
        }
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }

    public TableViewP getTableView() {
        return tableView;
    }

    public void setTableView(TableViewP tableView) {
        this.tableView = tableView;
    }

}
