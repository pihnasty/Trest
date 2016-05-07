package trestmodel;

import entityProduction.*;
import persistence.loader.DataSet;
import persistence.loader.SectionDataSet;
import persistence.loader.tabDataSet.RowTrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

/**
 * Created by Max on 19.02.2016.
 */
public class TrestModel extends Observable {

    private DataSet dataSet;
    private SectionDataSet sectionDataSet;

    private Trest trest;
    private List<Trest> trests = new ArrayList();

    public Locale locale;

    public TrestModel() {
        setLocale("ru"); //en
        this.dataSet = new DataSet();

        this.sectionDataSet = new SectionDataSet(dataSet);

        DataSet.showTab(sectionDataSet.getTypemachines());

        for (RowTrest r: this.dataSet.getTabTrests()) trests.add( dataSet.createObject(r));

        for (Trest t: trests)  if (t.getId()==1)    this.trest= t;

        intersectionMachine();  // The intersection of the collection from the collection on line

        DataSet.showTab(trests);

        DataSet.showTab(trest.getWorks());


    }

    /**
     * The intersection of the collection from the collection on line
     */
    private void intersectionMachine() {
        List<Typemachine> tpS = sectionDataSet.getTypemachines();
        List<Machine> forDelete = new ArrayList();
        for (int iType = 0; iType < tpS.size(); iType++) {
            List<Modelmachine> mmS = tpS.get(iType).getModelmachines();
            for (int iModel = 0; iModel < mmS.size(); iModel++) {
                List<Machine> mS = mmS.get(iModel).getMachines();
                for (int iMachine = 0; iMachine < mS.size(); iMachine++) {
                    Machine machineType = mS.get(iMachine);
                    boolean isTrestIsTipe = false;
                    for (Trest trest : trests)
                        for (Work work : trest.getWorks())
                            for (Machine machineTrest : work.getMachines()) {
                                if (machineType.getId() == machineTrest.getId()) {
                                    isTrestIsTipe = true;
                                    Machine m = machineTrest;
                                    m.setModelmachine(machineType.getModelmachine());
                                    mS.set(iMachine, m);
                                }
                            }
                    if (!isTrestIsTipe) forDelete.add(machineType);
                }
            }
        }
        for(int i=0; i<dataSet.getTabMachines().size(); i++ ) for (Machine machine: forDelete) if (machine.getId()== dataSet.getTabMachines().get(i).getId()) {
            dataSet.getTabMachines().remove(dataSet.getTabMachines().get(i));
        }
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {

        this.dataSet = dataSet;
    }

    public Trest getTrest() {
        return trest;
    }

    public void setTrest(Trest trest) {
        this.trest = trest;
    }


    /**Set the language for the application
     * @param s Language of the application ( new Locale(s))
     */
    private void setLocale(String s) {
        locale = Locale.getDefault();
        if (s != "") {
            locale = new Locale(s);
            Locale.setDefault(locale);
        }
    }

}
