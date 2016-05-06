package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import persistence.loader.tabDataSet.RowModelmachine;

public class Q {
    public Q(Machine machine, RowModelmachine rowModelmachine) {
        System.out.println("Machine"+machine.getName()+"     rowModelmachine"+rowModelmachine.getName());

    }
}
