package trestview.table.tablemodel;

import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowWork;
import trestview.dictionary.DictionaryModel;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;

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
        this.nameColumns = buildNameColumns();
        this.dataset = dictionaryModel.getTMenuModel().getTrestModel().getDataSet();

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
                selectRow = new RowWork(this.dataset,tClass);
                tab.add(selectRow);
                break;
            case saveRowTable:
                methodCall = MethodCall.saveRowTable;
                this.dataset.saveDataset();
                break;
            case editRowTable:
                methodCall = MethodCall.editRowTable;
                break;
            case delRowTable:
                methodCall = MethodCall.delRowTable;
                if (tClass == RowWork.class )  XmlRW.delRow (selectRow, tab, dataset.getTabWorks(), dataset.getTabTrestsWorks());
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


