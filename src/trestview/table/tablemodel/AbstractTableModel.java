package trestview.table.tablemodel;

import entityProduction.Work;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowIdNameDescription;
import persistence.loader.tabDataSet.RowWork;
import trestview.dictionary.DictionaryModel;
import trestview.menu.TMenuModel;
import trestview.table.tablemodel.abstracttablemodel.ColumnsOrderMap;
import trestview.table.tablemodel.abstracttablemodel.ParametersColumn;
import trestview.table.tablemodel.abstracttablemodel.ParametersColumnMap;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by pom on 05.03.2016.
 */
public abstract class AbstractTableModel<cL> extends Observable {

    protected ArrayList<cL> tab;
    protected cL selectRow;
    protected DataSet dataset;
    protected ArrayList<ParametersColumn>  parametersOfColumns;
    protected Class tClass;

    public ArrayList<ParametersColumn> getParametersOfColumns() {
        return parametersOfColumns;
    }



    public void changed() {
        setChanged();
        notifyObservers();
    }

    public ArrayList<ParametersColumn> buildParametersColumn() {
        parametersOfColumns = new ArrayList<>();
        ColumnsOrderMap.getColumns(tClass).stream().map(s-> parametersOfColumns.add(ParametersColumnMap.getParametersColumn(s))).count();
        return parametersOfColumns;
    }

    public ArrayList<cL> getTab() {
        return tab;
    }

    public void setTab(ArrayList<cL> tab) {
        this.tab = tab;
    }

    public cL getSelectRow() {return selectRow;   }

    public void setSelectRow(cL selectRow) {  this.selectRow = selectRow; }

    public Class gettClass() { return tClass;  }

    public DataSet getDataset() {  return dataset;  }

    public void setDataset(DataSet dataset) {  this.dataset = dataset;  }

}


