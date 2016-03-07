package trestview.menu;

import trestmodel.TrestModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuModel extends Observable  {

    private TrestModel trestModel;

    public TMenuModel() {
    }



    public TMenuModel(TrestModel trestModel) {
        this.trestModel = trestModel;
    }




    public TrestModel getTrestModel() {
        return trestModel;
    }

    public void setTrestModel(TrestModel trestModel) {
        this.trestModel = trestModel;
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }
}
