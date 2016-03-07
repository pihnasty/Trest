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
    private boolean saveChange = false;


    public HboxpaneModel(DictionaryModel dictionaryModel, Class tClass) {
        this.dictionaryModel = dictionaryModel;
        this.tClass = tClass;
    }




    public void  addRowTable()   {
        saveChange = false;
        changed();
    }

    public void  saveRowTable()   {
        saveChange = true;
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


    public boolean isSaveChange() {
        return saveChange;
    }

    public void setSaveChange(boolean saveChange) {
        this.saveChange = saveChange;
    }

}
