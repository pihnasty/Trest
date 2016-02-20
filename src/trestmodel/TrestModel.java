package trestmodel;

import loader.DataSet;

/**
 * Created by Max on 19.02.2016.
 */
public class TrestModel {

    private DataSet dataSet;

    public TrestModel() {
        this.dataSet = new DataSet();
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

}
