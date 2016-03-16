package trestview.table.tablemodel;

import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowIdNameDescription;
import trestview.dictionary.DictionaryModel;
import trestview.menu.TMenuModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by pom on 05.03.2016.
 */
public abstract class AbstractTableModel<cL> extends Observable {




    protected DictionaryModel dictionaryModel;
    protected ArrayList<cL> tab;
    protected cL selectRow;
    protected DataSet dataset;

    protected ArrayList<String>  nameColumns ;



    protected Class tClass;

    public void changed() {
        setChanged();
        notifyObservers();
    }


    public ArrayList<String> biuldNameColumns() {
        nameColumns = new ArrayList<String>();
        Integer i = new Integer(0);
        if (tab != null) {
            if (!tab.isEmpty()) { Field[] fields = XmlRW.fieldsCl(tClass);
                for (Field fd : fields) {
                    // nameColumns.add(i.toString());
                     nameColumns.add(fd.getName());
                    i++;
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

    public DictionaryModel getDictionaryModel() {
        return dictionaryModel;
    }

    public void setDictionaryModel(DictionaryModel dictionaryModel) {
        this.dictionaryModel = dictionaryModel;
    }

    public cL getSelectRow() {return selectRow;   }

    public void setSelectRow(cL selectRow) {  this.selectRow = selectRow; }

    public Class gettClass() { return tClass;  }

    public void settClass(Class tClass) { this.tClass = tClass;  }

}
