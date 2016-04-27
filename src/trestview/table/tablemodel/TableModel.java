package trestview.table.tablemodel;

import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowFunctiondist;
import persistence.loader.tabDataSet.RowMachine;
import persistence.loader.tabDataSet.RowTypemachine;
import persistence.loader.tabDataSet.RowWork;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by pom on 07.02.2016.
 */
public class TableModel <cL> extends AbstractTableModel implements Observer {

    private MethodCall methodCall;

    public TableModel(DataSet dataSet ) {
        this.parametersOfColumns = buildParametersColumn() ;
        this.dataset = dataSet;
    }

    public TableModel(ArrayList<?> tab) {
        this.tab = tab;

    //    this.tClass = (Class) ((ParameterizedType) tab.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.tClass = RowFunctiondist.class;
        this.parametersOfColumns = buildParametersColumn() ;
        System.out.println( ((ParameterizedType) tab.getClass() .getGenericSuperclass()).getActualTypeArguments()[0]);
    }


    /**
     * @param tClass    The data type for a table row. This is [RowWork.class] for the table = [ArrayList<RowWork>].
     */
    public TableModel(DataSet dataSet, Class<cL> tClass) {
        this.tab = dataSet.getTabIND(tClass);
                  this.tClass =  tClass;

   //     Class<cL> persistentClass = (Class<cL>)    ((ParameterizedType)getClass().getGenericSuperclass()) .getActualTypeArguments()[0];




        this.parametersOfColumns = buildParametersColumn() ;
        this.dataset = dataSet;
    }
    public TableModel(DataSet dataSet, ArrayList<cL> tab) {
        // TODO Найти метод определения базового типа данных [cL] в массиве ArrayList<cL>
        this.tab = tab;
        this.tClass = tab.get(0).getClass();
        //  this.tClass = RowFunctiondist.class;
        this.parametersOfColumns = buildParametersColumn() ;
        this.dataset = dataSet;
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
                try {
                    Constructor constructor = tClass.getConstructor(DataSet.class, Class.class);
                    selectRow = constructor.newInstance(this.dataset,tClass);
                } catch (NoSuchMethodException e)  { e.printStackTrace(); }
                  catch (InstantiationException e) { e.printStackTrace(); }
                  catch (IllegalAccessException e) { e.printStackTrace(); }
                  catch (InvocationTargetException e) { e.printStackTrace(); }


                if (!tClass.equals(RowFunctiondist.class) )  tab.add(selectRow);
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


