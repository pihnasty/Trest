package trestview.hboxpane;

import persistence.loader.tabDataSet.RowFunctiondist;
import trestmodel.TrestModel;
import trestview.dictionary.DictionaryModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class HboxpaneModel extends Observable {

    private Class tClass;
    private DictionaryModel dictionaryModel;

    private MethodCall methodCall;


    public HboxpaneModel(DictionaryModel dictionaryModel, Class tClass) {
        this.dictionaryModel = dictionaryModel;
        this.tClass = tClass;
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

    public DictionaryModel getDictionaryModel() {
        return dictionaryModel;
    }

    public void setDictionaryModel(DictionaryModel dictionaryModel) {
        this.dictionaryModel = dictionaryModel;
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

}


