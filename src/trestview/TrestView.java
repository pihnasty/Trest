package trestview;

import designpatterns.MVC;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import trestmodel.TrestModel;
import trestview.machinetest.MachineTestController;
import trestview.machinetest.MachineTestModel;
import trestview.machinetest.MachineTestView;
import trestview.menu.TMenuController;
import trestview.menu.TMenuModel;
import trestview.menu.TMenuView;


import java.io.IOException;
import java.util.Observable;
import java.util.ResourceBundle;
public class TrestView extends BorderPane {
    TrestModel trestModel;
    public TrestView(TrestModel trestModel) {
        this.trestModel =  trestModel;
        XmlRW.fxmlLoad(this,this,"trestview.fxml","","");
        MVC menu = new MVC(TMenuModel.class, TMenuController.class, TMenuView.class, this.trestModel );
        this.setTop((TMenuView)menu.getView());

        MachineTestModel machineTestModel = new MachineTestModel((TMenuModel)menu.getModel());
        MachineTestController machineTestController = new MachineTestController(machineTestModel);
        MachineTestView machineTestView = new MachineTestView(machineTestModel, machineTestController);
        machineTestModel.addObserver(machineTestView);



        this.setCenter(machineTestView);
    }

    public void addView(MachineTestView view) {
        this.setCenter(view);
    }


}
