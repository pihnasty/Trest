package trestview.table;

import javafx.scene.control.TableViewBuilder;
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
        TableViewBuilder a;
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
