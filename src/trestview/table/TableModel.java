package trestview.table;

import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class TableModel extends Observable  {

    private TMenuModel menuModel;

    public TableModel() {
    }
    public TableModel(TMenuModel menuModel) {
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
