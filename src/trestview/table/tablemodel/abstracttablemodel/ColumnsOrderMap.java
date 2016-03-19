package trestview.table.tablemodel.abstracttablemodel;

import persistence.loader.tabDataSet.RowWork;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pom on 19.03.2016.
 */
public class ColumnsOrderMap {
    private static Map<Class, ColumnsOrder> map;

    private ColumnsOrderMap() {
        map = new HashMap<Class, ColumnsOrder>();
        map.put(RowWork.class, new ColumnsOrder("id", "name", "scheme", "overallSize", "scaleEquipment", "description"));
    }

    public static ColumnsOrder getColumns(Class key) {
        new ColumnsOrderMap();
        return map.get(key);
    }
}
