package trestview.table.tablemodel.abstracttablemodel;

import entityProduction.Functiondist;
import persistence.loader.tabDataSet.RowFunctiondist;
import persistence.loader.tabDataSet.RowMachine;
import persistence.loader.tabDataSet.RowTypemachine;
import persistence.loader.tabDataSet.RowWork;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pom on 19.03.2016.
 */
public class ColumnsOrderMap {
    private static Map<Class, ColumnsOrder> map;

    private ColumnsOrderMap() {
        map = new HashMap<>();
        map.put(RowWork.class, new ColumnsOrder("id", "name", "scheme", "image", "overallSize", "scaleEquipment", "description"));
        map.put(RowMachine.class, new ColumnsOrder("id", "name", "image","locationX","locationY",  "state",  "description" ));
        map.put(RowTypemachine.class, new ColumnsOrder("id", "name", "description" ));
        map.put(RowFunctiondist.class, new ColumnsOrder("id", "name", "description" ));
        map.put(Functiondist.class, new ColumnsOrder("id", "name", "averageValue", "meanSquareDeviation", "pathData", "description" ));
    }

    public static ColumnsOrder getColumns(Class key) {
        new ColumnsOrderMap();
        return map.get(key);
    }
}



