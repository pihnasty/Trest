package trestview.dictionary;

import trestmodel.TrestModel;
import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class DictionaryModel extends Observable  {

    private TMenuModel menuModel;

    public Class gettClass() {
        return tClass;
    }

    public void settClass(Class tClass) {
        this.tClass = tClass;
    }

    private Class tClass;

    public DictionaryModel() {
    }
    public DictionaryModel(Observable menuModel,Class tClass) {
        this.menuModel = (TMenuModel)menuModel;
        this.tClass = tClass;
    }

    public TMenuModel getTMenuModel() {
        return menuModel;
    }

    public void setTMenuModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }
}
