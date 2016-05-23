package trestview.machinetest.animation;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import trestview.machinetest.MachineTestController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 18.04.2016.
 */
public class ProcessingAnimationView extends Pane implements Observer {

    private ProcessingAnimationModel processingAnimationModel;
    private MachineTestController machineTestController;

    private ImageView imageViewTop;
    private ImageView imageViewBot;
    private Image imgTop;
    private Image imgBot;

    public ProcessingAnimationView(ProcessingAnimationModel animationModel) {//ProcessingAnimationModel animationModel, MachineTestController testController) {
        this.processingAnimationModel = animationModel;
//        this.machineTestController = testController;

        imageViewTop = new ImageView();
        imageViewTop.setFitWidth(270);
        imageViewTop.setFitHeight(100);
        imgTop = new Image("file:Image\\animation\\presTop.png");
        imageViewTop.setImage(imgTop);

        imageViewBot = new ImageView();
        imageViewBot.setTranslateY(40);
        imageViewBot.setFitWidth(270);
        imageViewBot.setFitHeight(300);
        imgBot = new Image("file:Image\\animation\\presBot.png");
        imageViewBot.setPreserveRatio(true);
        imageViewBot.setImage(imgBot);

        this.getChildren().addAll(imageViewBot, imageViewTop);
//        this.setHeight(400);
        this.setMinSize(300, 300);
//        this.minHeight(600);
//        this.set

//        runProcessingAnimation(500);
    }

    private void runProcessingAnimation(double duration) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(duration), imageViewTop);
        tt.setByY(90);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
        tt.play();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass() == ProcessingAnimationModel.class) {
            runProcessingAnimation(((ProcessingAnimationModel)o).getDuration());
        }
    }
}
