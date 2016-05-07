package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import entityProduction.Modelmachine;
import persistence.loader.tabDataSet.RowModelmachine;

public class Q {

    private Modelmachine modelmachine;
    public Q(Machine machine) {
        modelmachine = machine.getModelmachine();
        System.out.println("Machine"+machine.getName()+"RowModelmachine rowModelmachine ================================"+modelmachine.getName());

    }
}
