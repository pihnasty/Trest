package trestview.table.tablemodel;

import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowMachine;
import persistence.loader.tabDataSet.RowTypemachine;
import persistence.loader.tabDataSet.RowWork;
import trestview.dictionary.DictionaryModel;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by pom on 07.02.2016.
 */
public class TableModel <cL> extends AbstractTableModel implements Observer {

    private MethodCall methodCall;

    /**
     *
     * @param dictionaryModel
     * @param tClass    The data type for a table row. This is [RowWork.class] for the table = [ArrayList<RowWork>].
     */
    public TableModel(DictionaryModel dictionaryModel, Class<cL> tClass) {
        this.dictionaryModel = dictionaryModel;
        this.tab = dictionaryModel.getTMenuModel().getTrestModel().getDataSet().getTabIND(tClass);
        this.tClass = tClass;
        this.parametersOfColumns = buildParametersColumn() ;
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
            //    selectRow = new RowWork(this.dataset,tClass);
                try {
                    Constructor constructor = tClass.getConstructor(DataSet.class, Class.class);
                    selectRow = constructor.newInstance(this.dataset,tClass);
                } catch (NoSuchMethodException e)  { e.printStackTrace(); }
                  catch (InstantiationException e) { e.printStackTrace(); }
                  catch (IllegalAccessException e) { e.printStackTrace(); }
                  catch (InvocationTargetException e) { e.printStackTrace(); }


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

/*
                try {
                    System.out.println("getTab"+tClass.getSimpleName().substring(3)+"s");
                    Class noparams[] = {};
                    Method mGetTabChild  =  dataset.getClass().getDeclaredMethod("getTab"+tClass.getSimpleName().substring(3)+"s", null);
                    Method mGetTabParent =  dataset.getClass().getDeclaredMethod("getTab"+tClass.getSimpleName().substring(3)+"s", null);
                    try {
                        XmlRW.delRow (selectRow, tab,
                               // dataset.getTabWorks(),
                                (ArrayList<cL>) mGetTabChild.invoke(dataset,null),
                                dataset.getTabTrestsWorks());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
*/

                //System.out.println(tClass.getSimpleName().substring(3));
                if (tClass == RowTypemachine.class )  XmlRW.delRow (selectRow, tab, dataset.getTabTypemachines(), dataset.getTabModelmachineTypemachines());
                if (tClass == RowMachine.class )  XmlRW.delRow (selectRow, tab, dataset.getTabMachines(), dataset.getTabWorksMachines());
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


