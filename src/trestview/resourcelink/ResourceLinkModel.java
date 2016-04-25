package trestview.resourcelink;

import persistence.loader.DataSet;
import trestmodel.TrestModel;
import trestview.menu.TMenuModel;

import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class ResourceLinkModel extends Observable  {

    private Observable trestModel;
    private DataSet dataSet;

    public ResourceLinkModel(Observable trestModel) {
        this.trestModel = trestModel;
        this.dataSet = ((TrestModel)trestModel).getDataSet();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }

    public Observable getTrestModel() {changed();   return trestModel;   }

    public void setTrestModel(Observable trestModel) { this.trestModel = trestModel;    }

    public DataSet getDataSet() {        return dataSet;    }
}
