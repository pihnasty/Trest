package trestview.table.tablemodel;

import persistence.loader.XmlRW;
import trestview.menu.TMenuModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by pom on 05.03.2016.
 */
public abstract class AbstractTableModel<cL> extends Observable {



    protected ArrayList<cL> tab;

    protected ArrayList<String>  nameColumns ;

    protected Class tClass;

    public void changed() {
        setChanged();
        notifyObservers();
    }


    public ArrayList<String> biuldNameColumns() {
        nameColumns = new ArrayList<String>();
        if (tab != null) {
            if (!tab.isEmpty()) { Field[] fields = XmlRW.fieldsCl(tClass);
                for (Field fd : fields) { nameColumns.add(fd.getName());
                }
            }
        }
        return nameColumns;
    }

    public ArrayList<String> getNameColumns() {
        return nameColumns;
    }

    public ArrayList<cL> getTab() {
        return tab;
    }

    public void setTab(ArrayList<cL> tab) {
        this.tab = tab;
    }

}
