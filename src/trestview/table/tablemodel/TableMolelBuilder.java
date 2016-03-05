package trestview.table.tablemodel;

import java.util.ArrayList;

/**
 * Created by pom on 05.03.2016.
 */
public class TableMolelBuilder {
    public static <cL> AbstractTableModel build (ArrayList<cL> tab, Class<cL> tClass) {

        if (tab != null) {
                if (!tab.isEmpty()) {     System.out.println(((ArrayList<cL>) tab).get(0).getClass());
                    }
             //   else { tab.add()}


        }

       AbstractFactoryTableModel factoryTableModel = new FactoryTableModel(  tab,   tClass);

        return ((FactoryTableModel) factoryTableModel).getTableModel();
    }
}


// new TableModel(this.dictionaryModel.getTMenuModel().getTrestModel().getTrest().getWorks(), Work.class);