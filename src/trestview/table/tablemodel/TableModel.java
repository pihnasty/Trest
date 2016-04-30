package trestview.table.tablemodel;

import entityProduction.Work;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.*;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.MethodCall;
import trestview.resourcelink.ResourceLinkModel;
import trestview.table.tablemodel.abstracttablemodel.Rule;

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


    /**
     * @param  rule    The data type for a table row. This is [RowWork.class] for the table = [ArrayList<RowWork>].
     */
    public TableModel(Observable o, Rule rule) {
        this.rule = rule;
        this.tClass =  rule.getClassTab();
        if(rule.getClassTab()== Work.class)  {
            this.dataset = ((ResourceLinkModel)o).getDataSet();
            this.trest = ((ResourceLinkModel)o).getTrest();
            this.tab = trest.getWorks();
        }


        this.parametersOfColumns = buildParametersColumn() ;

    }

    public TableModel(DataSet dataSet, Rule rule) {
        this.rule = rule;
        this.tClass =  rule.getClassTab();
        this.tab = dataSet.getTabIND(tClass);
        this.parametersOfColumns = buildParametersColumn() ;
        this.dataset = dataSet;
    }





    public TableModel(ArrayList<cL> tab, Rule rule) {
        // TODO Найти метод определения базового типа данных [cL] в массиве ArrayList<cL>
        this.rule=rule;
        this.tab = tab;
        this.tClass = rule.getClassTab();
        //  this.tClass = RowFunctiondist.class;
        this.parametersOfColumns = buildParametersColumn() ;
        this.dataset = null;
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
                    Constructor constructor;
                    RowIdNameDescription r;
                    ArrayList<RowIdNameDescription> tabRow;
                    switch (rule) {
                        case RowFunctiondist:

                            break;
                        case Work:
                            System.out.println("case Work:");
                            constructor = tClass.getSuperclass().getConstructor(DataSet.class, Class.class);
                            r = (RowIdNameDescription) constructor.newInstance(this.dataset,tClass.getSuperclass());

                            tabRow = dataset.getTabIND(tClass.getSuperclass());
                            tabRow.add(r);

                            selectRow = dataset.createObject(r);
                            tab.add(selectRow);
                            break;
                        case Machine:
                            System.out.println("case Work:");
                            constructor = tClass.getSuperclass().getConstructor(DataSet.class, Class.class);
                            r = (RowIdNameDescription) constructor.newInstance(this.dataset,tClass.getSuperclass());

                            tabRow = dataset.getTabIND(tClass.getSuperclass());
                            tabRow.add(r);

                            selectRow = dataset.createObject(r);
                            tab.add(selectRow);
                            break;
                        default:
                            constructor = tClass.getConstructor(DataSet.class, Class.class);
                            selectRow = constructor.newInstance(this.dataset,tClass);
                            tab.add(selectRow);
                            System.out.println("                  default:");
                            break;
                    }

                } catch (NoSuchMethodException e)       { e.printStackTrace(); }
                  catch (InstantiationException e)      { e.printStackTrace(); }
                  catch (IllegalAccessException e)      { e.printStackTrace(); }
                  catch (InvocationTargetException e)   { e.printStackTrace(); }



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


