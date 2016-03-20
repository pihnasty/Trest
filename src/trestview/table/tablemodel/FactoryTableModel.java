package trestview.table.tablemodel;

import trestview.dictionary.DictionaryModel;
import trestview.table.tablemodel.AbstractFactoryTableModel;
import trestview.table.tablemodel.AbstractTableModel;
import trestview.table.tablemodel.TableModel;

import java.util.ArrayList;

/**
 * Created by pom on 05.03.2016.
 */

public class FactoryTableModel   extends AbstractFactoryTableModel {

    private DictionaryModel dictionaryModel;
    private Class tClass;


    public FactoryTableModel (DictionaryModel dictionaryModel, Class  tClass) {
        this.dictionaryModel =  dictionaryModel;
        this.tClass = tClass.getSuperclass();

    }

    public AbstractTableModel getTableModel() {
        return new TableModel(dictionaryModel,tClass);
    }


}
