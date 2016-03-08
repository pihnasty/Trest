package trestview.table.tablemodel;

import entityProduction.Work;
import trestview.dictionary.DictionaryModel;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;


import java.lang.reflect.Method;
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

        if (o.getClass()==HboxpaneModel.class) { updateHBoxpaneModel((HboxpaneModel) o);   }
            changed();
    }

    private void updateHBoxpaneModel(HboxpaneModel o) {
        switch (o.getMethodCall()) {
            case addRowTable:
                this.tab.add(new Work(this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet()));
                break;
            case saveRowTable:
                this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet().saveDataset();
                break;
            case editRowTable:
                break;
            case delRowTable:
                this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet().saveDataset();
                break;
            default:
                break;
        }
    }

    public void changed() {
        setChanged();
        notifyObservers();

    }



}


