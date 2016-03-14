package trestview.table.tablemodel;

import entityProduction.Work;
import persistence.loader.tabDataSet.RowWork;
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

    private MethodCall methodCall;

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
                methodCall = MethodCall.addRowTable;
                selectRow = new RowWork(this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet(),tClass);
                tab.add(selectRow);
                break;
            case saveRowTable:
                methodCall = MethodCall.saveRowTable;
                this.dictionaryModel.getTMenuModel().getTrestModel().getDataSet().saveDataset();
                break;
            case editRowTable:
                methodCall = MethodCall.editRowTable;
                break;
            case delRowTable:
                methodCall = MethodCall.delRowTable;
                break;
            default:
                break;
        }
    }

    public void changed() {
        setChanged();
        notifyObservers();

    }

    public MethodCall getMethodCall() { return methodCall;  }



}


