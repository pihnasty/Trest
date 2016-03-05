package trestview.table.tablemodel;

import entityProduction.Work;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import trestview.menu.TMenuModel;
import trestview.table.tablemodel.AbstractTableModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by pom on 07.02.2016.
 */
public class TableModel <cL> extends AbstractTableModel {



    public TableModel(ArrayList<cL> tab, Class<cL> tClass) {
        this.tab = tab;
        this.tClass = tClass;
        this.nameColumns = biuldNameColumns();
    }




}
