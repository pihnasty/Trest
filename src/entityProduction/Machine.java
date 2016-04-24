package entityProduction;

import persistence.loader.DataSet;
import persistence.loader.tabDataSet.RowMachine;

/**
 * @author POM Единица производственного оборудования
 */
public class Machine extends RowMachine {
    

            
    public Machine() {
        super();
    }

    public Machine(int id, String name, double locationX, double locationY, double angle, double state, String description) {
        super(id, name, locationX, locationY, angle, state, description);
    }

    public Machine(DataSet dataSet) {
        super(dataSet, Machine.class);
    }

    /* Фото машины									*/
    private String img = "Image/Machine/press_04.png";

}
