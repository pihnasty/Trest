package trestview.table.tablemodel;

import trestview.table.tablemodel.AbstractFactoryTableModel;
import trestview.table.tablemodel.AbstractTableModel;
import trestview.table.tablemodel.TableModel;

import java.util.ArrayList;

/**
 * Created by pom on 05.03.2016.
 */

public class FactoryTableModel <cL> extends AbstractFactoryTableModel {

    private ArrayList<cL> tab;
    private Class<cL> tClass;


    public FactoryTableModel (ArrayList<cL> tab, Class<cL> tClass) {
        this.tab = tab;
        this.tClass = tClass;
    }



    public AbstractTableModel getTableModel() {
        return new TableModel(tab,tClass);
    }


}
