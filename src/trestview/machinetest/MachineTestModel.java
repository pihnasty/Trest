package trestview.machinetest;

import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestModel extends Observable {

    private TMenuModel menuModel;
//    private Class tClass;

    public MachineTestModel() {
    }
    public MachineTestModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
//        this.tClass = tClass;
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
