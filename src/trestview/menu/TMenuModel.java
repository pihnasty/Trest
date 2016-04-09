package trestview.menu;

import trestmodel.TrestModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuModel extends Observable  {

    private Observable trestModel;

    public TMenuModel() {
    }



    public TMenuModel(Observable trestModel) {
        this.trestModel = trestModel;
    }




    public TrestModel getTrestModel() {
        return (TrestModel) trestModel;
    }

    public void setTrestModel(Observable trestModel) {
        this.trestModel = trestModel;
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }
}
