package trestview.machinetest.animation;

import javafx.scene.layout.Pane;
import trestview.machinetest.MachineTestController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 18.04.2016.
 */
public class ProcessingAnimationView extends Pane implements Observer {

    private ProcessingAnimationModel processingAnimationModel;
    private MachineTestController machineTestController;

    public ProcessingAnimationView(ProcessingAnimationModel animationModel, MachineTestController testController) {
        this.processingAnimationModel = animationModel;
        this.machineTestController = testController;

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
