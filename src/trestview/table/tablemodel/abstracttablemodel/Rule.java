package trestview.table.tablemodel.abstracttablemodel;

import entityProduction.Functiondist;
import persistence.loader.tabDataSet.*;

public enum Rule {  RowWork (RowWork.class),
                    RowMachine(RowMachine.class),
                    RowTypemachine(RowTypemachine.class),
                    RowFunctiondist(RowFunctiondist.class),
                    Functiondist(Functiondist.class),
                    Functiondist2(Functiondist.class);
    private Rule(Class clazz) {  this.clazz = clazz;  }
    private Class clazz;
    public Class getClassTab () { return this.clazz; }
}

