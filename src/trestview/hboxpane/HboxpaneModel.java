package trestview.hboxpane;

import persistence.loader.DataSet;
import persistence.loader.tabDataSet.RowFunctiondist;
import trestmodel.TrestModel;
import trestview.dictionary.DictionaryModel;
import trestview.table.tablemodel.abstracttablemodel.Rule;

import java.util.Observable;

public class HboxpaneModel extends Observable {

    private Class tClass;
    private Rule rule;

    private DataSet dataSet;

    private MethodCall methodCall;


    public HboxpaneModel(DataSet dataSet, Rule rule) {
        this.rule = rule;
        this.tClass = rule.getClassTab();
        this.dataSet = dataSet;

    }


    public void addRowTable() {
        if (!tClass.equals(RowFunctiondist.class)) {
            methodCall = MethodCall.addRowTable;
            changed();
        }
        else {
            // TODO: Generate dialog message: This table system and can not be edited.
        }
    }

    public void saveRowTable() {
        methodCall = MethodCall.saveRowTable;
        changed();
    }

    public void delRowTable() {
        if (!tClass.equals(RowFunctiondist.class)) {
            methodCall = MethodCall.delRowTable;
            changed();
        }
    }

    public void editRowTable() {
        methodCall = MethodCall.editRowTable;
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }

    public Class gettClass() {
        return tClass;
    }

    public void settClass(Class tClass) {
        this.tClass = tClass;
    }

    public MethodCall getMethodCall() {
        return methodCall;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

}


