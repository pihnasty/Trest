package trestview.table.tablemodel.abstracttablemodel;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by pom on 19.03.2016.
 */
public class ParametersColumnMap  {
    private static Map<String,ParametersColumn> map;
    public ParametersColumnMap () {
        map = new HashMap<String,ParametersColumn>();
        // Parameters  of column  for RowWork
        map.put("id", new ParametersColumn (getHeader("id"),      int.class, false, 40) );
        map.put("name", new ParametersColumn (getHeader("name"), String.class, true, 200) );
        map.put("scheme", new ParametersColumn (getHeader("scheme"), double.class, true, 100) );
        map.put("overallSize", new ParametersColumn (getHeader("overallSize"), double.class, true, 100) );
        map.put("scaleEquipment", new ParametersColumn (getHeader("scaleEquipment"), double.class, true, 100) );
        map.put("description", new ParametersColumn (getHeader("description"), double.class, true, 100) );

    }
    private String getHeader(String headerColumn){
        return ResourceBundle.getBundle("resources.ui").getString(headerColumn);
    }
}