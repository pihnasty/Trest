package trestview.table.tablemodel;

import entityProduction.Work;
import trestview.dictionary.DictionaryModel;
import trestview.hboxpane.HboxpaneModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by pom on 07.02.2016.
 */
public class TableModel <cL> extends AbstractTableModel implements Observer {



    public TableModel(DictionaryModel dictionaryModel, Class<cL> tClass) {
        this.dictionaryModel = dictionaryModel;
        this.tab = dictionaryModel.getTMenuModel().getTrestModel().getDataSet().getTabIND(tClass);

        this.tClass = tClass;
        this.nameColumns = biuldNameColumns();
    }


    @Override
    public void update(Observable o, Object arg) {
        if(!((HboxpaneModel) o).isSaveChange()) tab.add(new Work(this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet()));
            else {this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet().saveDataset();   }
        changed();
    }



    public void changed() {
        setChanged();
        notifyObservers();
    }



}


