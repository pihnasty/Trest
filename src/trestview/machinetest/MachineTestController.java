package trestview.machinetest;

import entityProduction.Machine;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestController implements Initializable {
    private MachineTestModel machineTestModel;

    public MachineTestController() {

    }

    public MachineTestController(MachineTestModel machineTestModel) {
        this.machineTestModel = machineTestModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
