package trestview.dictionary;

import trestmodel.TrestModel;
import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class DictionaryModel extends Observable  {

    private TMenuModel menuModel;

    public DictionaryModel() {
    }
    public DictionaryModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public TMenuModel getTrestModel() {
        return menuModel;
    }

    public void setTrestModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }
}
