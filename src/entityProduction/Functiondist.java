package entityProduction;

import persistence.loader.DataSet;
import persistence.loader.tabDataSet.RowFunctionOEM;
import persistence.loader.tabDataSet.RowFunctiondist;

public class Functiondist extends RowFunctiondist {
public Functiondist(int id, String name, String description)	{	super(id, name, description);			}
public Functiondist()											{	super();								}
public Functiondist(DataSet dataSet)							{	super(dataSet, Operation.class);		}
}