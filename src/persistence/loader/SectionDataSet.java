package persistence.loader;

import entityProduction.Machine;
import entityProduction.Typemachine;
import persistence.loader.tabDataSet.RowTypemachine;


import java.util.ArrayList;

public class SectionDataSet {
    ArrayList<Machine> sectionMachines;
    public SectionDataSet(DataSet dataSet) {

        ArrayList<Typemachine> sectionTypemachines = new ArrayList<>();

        for (RowTypemachine row : dataSet.getTabTypemachines()) sectionTypemachines.add(dataSet.createObject(row));

        DataSet .showTab(sectionTypemachines);

        for (Typemachine row : sectionTypemachines)   DataSet .showTab(row.getModelmachines());










    }


}
