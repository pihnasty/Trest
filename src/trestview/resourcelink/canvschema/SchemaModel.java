package trestview.resourcelink.canvschema;

import entityProduction.Trest;
import entityProduction.Work;
import persistence.loader.DataSet;
import trestview.resourcelink.ResourceLinkModel;
import trestview.table.tablemodel.TableModel;
import trestview.table.tablemodel.abstracttablemodel.Rule;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Max on 02.05.2016.
 */
public class SchemaModel extends Observable  implements Observer{
    private Observable observableModel;
    private Rule rule;
    private DataSet dataSet;

    private Work work;

    public SchemaModel(Observable observableModel, Rule rule) {
        this.observableModel = observableModel;
        this.rule = rule;
  //      this.tClass =  rule.getClassTab();
        if(rule== Rule.Work)  {
            this.dataSet = ((ResourceLinkModel)observableModel).getDataSet();
            if(!((ResourceLinkModel)observableModel).getTrest().getWorks().isEmpty()) this.work = ((ResourceLinkModel)observableModel).getTrest().getWorks().get(0);
            //this.tab = trest.getWorks();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        work = (Work) ((TableModel<Work>)o).getSelectRow();
        changed();
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }

    public Work getWork()           {   return work;        }

    public void setWork(Work work)  {   this.work = work;   }

}
