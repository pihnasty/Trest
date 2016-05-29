package trestview.machinetest.animation;

import javafx.animation.*;
import javafx.event.*;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
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
    Rectangle rectangle;
    Circle circle;
    Timeline timeline;
    TranslateTransition transitionCircle;
    TranslateTransition transitionRect;

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

        rectangle = new Rectangle(140, 185, 15, 5);
//        rectangle.setVisible(false);
        circle = new Circle(0, 180, 10);

        this.getChildren().addAll(rectangle, circle, imageViewBot, imageViewTop);

        this.setMinSize(300, 300);

        timeline = new Timeline();
        transitionCircle = new TranslateTransition();
        transitionRect = new TranslateTransition();
    }

    private void runProcessingAnimation(double duration) {
        timeline.setAutoReverse(true);
        timeline.setCycleCount(2);

//        transitionRect = new TranslateTransition(Duration.millis(duration), rectangle);
//        transitionCircle = new TranslateTransition(Duration.millis(duration), circle);
        transitionCircle.setDuration(Duration.millis(duration));
        transitionCircle.setNode(circle);
        transitionCircle.setAutoReverse(false);
        transitionCircle.setByX(150);
        transitionCircle.setFromX(0);

        transitionRect.setAutoReverse(false);
        transitionRect.setDuration(Duration.millis(duration));
        transitionRect.setNode(rectangle);
        transitionRect.setByX(150);
        transitionRect.setFromX(0);

        KeyValue keyValue = new KeyValue(imageViewTop.translateYProperty(), 90);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), (ActionEvent)->{
            toggleHide(rectangle);
            transitionRect.play();
            transitionCircle.play();


        },keyValue);

        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void toggleHide(Shape... shapes) {
        for(Shape shape : shapes) {
            if(shape.isVisible()) {
                shape.setVisible(false);
            } else {
                shape.setVisible(true);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass() == ProcessingAnimationModel.class) {
            toggleHide(rectangle);
            runProcessingAnimation(((ProcessingAnimationModel)o).getDuration());

        }
    }
}
