package trestview.table.tablemodel.abstracttablemodel;

import entityProduction.Functiondist;
import persistence.loader.tabDataSet.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pom on 19.03.2016.
 */
public class ColumnsOrderMap {
    private static Map<Rule, ColumnsOrder> map;

    private ColumnsOrderMap() {
        map = new HashMap<>();
        map.put(Rule.RowWork, new ColumnsOrder("id", "name", "scheme", "image", "overallSize", "scaleEquipment", "description"));
        map.put(Rule.RowMachine, new ColumnsOrder("id", "name", "image","locationX","locationY",  "state",  "description" ));
        map.put(Rule.RowTypemachine, new ColumnsOrder("id", "name", "description" ));
        map.put(Rule.RowFunctiondist, new ColumnsOrder("id", "name", "description" ));
        map.put(Rule.Functiondist, new ColumnsOrder("id", "name", "averageValue", "meanSquareDeviation", "pathData", "description" ));
        map.put(Rule.Functiondist2, new ColumnsOrder("id", "name" ));
    }

    public static ColumnsOrder getColumns(Rule key) {
        new ColumnsOrderMap();
        return map.get(key);
    }
}



