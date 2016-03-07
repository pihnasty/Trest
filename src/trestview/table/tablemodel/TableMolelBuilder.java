package trestview.table.tablemodel;

import trestview.dictionary.DictionaryModel;


/**
 * Created by pom on 05.03.2016.
 */
public class  TableMolelBuilder <cL> {

    public static <cL> AbstractTableModel build (DictionaryModel dictionaryModel, Class tClass) {

        AbstractFactoryTableModel factoryTableModel = new FactoryTableModel(dictionaryModel,tClass);

        return ((FactoryTableModel) factoryTableModel).getTableModel();
    }
}


// new TableModel(this.dictionaryModel.getTMenuModel().getTrestModel().getTrest().getWorks(), Work.class);