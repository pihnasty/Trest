package persistence.loader;

import entityProduction.Machine;
import entityProduction.Typemachine;
import persistence.loader.tabDataSet.RowIdNameDescription;
import persistence.loader.tabDataSet.RowTypemachine;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SectionDataSet {

    private DataSet dataSet;

    private  List<Typemachine> typemachines = new  ArrayList<>();

    public SectionDataSet(DataSet dataSet) {

        this.dataSet = dataSet;
        typemachines  = getTab ((this.dataSet.getTabTypemachines()));

    }

    public <cL> List<cL> getTab (List tab){
        return (List<cL>) tab.stream().map(row->(cL)dataSet.createObject((RowIdNameDescription) row)).collect(Collectors.toList());      //   for (RowTypemachine row : dataSet.getTabTypemachines()) sectionTypemachines.add(dataSet.createObject(row));
    }

    public List<Typemachine> getTypemachines() {
        return typemachines;
    }

    public void setTypemachines(List<Typemachine> typemachines) {
        this.typemachines = typemachines;
    }

}
