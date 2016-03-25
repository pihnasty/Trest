package trestview;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import persistence.loader.DataSet;
import trestmodel.TrestModel;
import trestview.machinetest.MachineTestController;
import trestview.machinetest.MachineTestModel;
import trestview.machinetest.MachineTestView;
import trestview.menu.TMenuController;
import trestview.menu.TMenuModel;
import trestview.menu.TMenuView;


import java.io.IOException;

/**
 * Sample custom control hosting a text field and a button.
 */
public class TrestView extends BorderPane {
    TrestModel trestModel;

    public TrestView() {
    }

    public TrestView(TrestModel trestModel) {
        this.trestModel =  trestModel;

        //region FXMLLoader fxmlLoader =
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trestview.fxml"));
        //   fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //endregion


        TMenuModel menuModel = new TMenuModel(this.trestModel);
        TMenuController menuController = new TMenuController(menuModel);
        TMenuView menuView = new TMenuView(menuModel, menuController);
        menuModel.addObserver(menuView);

        this.setTop(menuView);

        MachineTestModel machineTestModel = new MachineTestModel(menuModel);
        MachineTestController machineTestController = new MachineTestController(machineTestModel);
        MachineTestView machineTestView = new MachineTestView(machineTestModel, machineTestController);
        machineTestModel.addObserver(machineTestView);

        this.setCenter(machineTestView);
    }

    public void addView(MachineTestView view) {
        this.setCenter(view);
    }


}
