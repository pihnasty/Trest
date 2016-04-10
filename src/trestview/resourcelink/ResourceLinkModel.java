package trestview.resourcelink;

import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class ResourceLinkModel extends Observable  {

    private Observable trestModel;

    public ResourceLinkModel(Observable trestModel) {
        this.trestModel = trestModel;
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }

    public Observable getTrestModel() {changed();   return trestModel;   }

    public void setTrestModel(Observable trestModel) { this.trestModel = trestModel;    }
}
