package trestview.hboxpane;

import trestmodel.TrestModel;
import trestview.dictionary.DictionaryModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class HboxpaneModel extends Observable  {

    private Class tClass;
    private DictionaryModel dictionaryModel;

    private MethodCall methodCall;


    public HboxpaneModel(DictionaryModel dictionaryModel, Class tClass) {
        this.dictionaryModel = dictionaryModel;
        this.tClass = tClass;
    }




    public void  addRowTable()   {
        methodCall = MethodCall.addRowTable;
        changed();
    }

    public void  saveRowTable()   {
        methodCall = MethodCall.saveRowTable;
        changed();
    }

    public void  delRowTable()   {
        methodCall = MethodCall.delRowTable;
        changed();
    }


    public void changed() {
        setChanged();
        notifyObservers();
        System.out.println("changed() HboxModel");
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

    public MethodCall getMethodCall() { return methodCall;  }

}


