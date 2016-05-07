package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import entityProduction.Modelmachine;
import entityProduction.Work;
import persistence.loader.DataSet;
import persistence.loader.tabDataSet.RowIdNameDescription;
import persistence.loader.tabDataSet.RowModelmachine;
import trestview.resourcelink.ResourceLinkModel;
import trestview.table.tablemodel.TableModel;
import trestview.table.tablemodel.abstracttablemodel.Rule;

import java.util.ArrayList;
import java.util.List;
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

    private List<Q> qs;

    public SchemaModel(Observable observableModel, Rule rule) {
        this.observableModel = observableModel;
        this.rule = rule;
  //      this.tClass =  rule.getClassTab();
        qs = new ArrayList();
        if(rule== Rule.Work)  {
            this.dataSet = ((ResourceLinkModel)observableModel).getDataSet();
            if(!((ResourceLinkModel)observableModel).getTrest().getWorks().isEmpty())  {
                this.work = ((ResourceLinkModel)observableModel).getTrest().getWorks().get(0);
                for(Machine machine : work.getMachines()) {
                   qs.add(new Q(machine ));
                }  //  ArrayList<Machine> machines));
            }
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

    public List<Q> getQs()          {   return qs;          }

    public void setQs(List<Q> qs)   {   this.qs = qs;       }

}
