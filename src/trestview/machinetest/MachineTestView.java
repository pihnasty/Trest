package trestview.machinetest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import persistence.loader.XmlRW;


import java.io.IOException;
import java.util.*;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestView extends BorderPane implements Observer {
    private MachineTestModel machineTestModel;

    LineChart<Number,Number> lineChart;

    XYChart.Series series;

    public MachineTestView(MachineTestModel machineTestModel, MachineTestController machineTestController) {
        this.machineTestModel = machineTestModel;
//        XmlRW.fxmlLoad(machineTestModel, machineTestController,"machinetestView.fxml", "resources.ui", null);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("machinetestView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(machineTestController);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

//        setTitle(fxmlLoader.getResources().getString("TestOfMachine"));


        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 5, 5, 10));

        //img
        Image image = new Image("resources/images/animation/2.gif");

        ImageView imageView = new ImageView();
        imageView.setImage(image);
//        imageView.fitWidthProperty().bind(widthProperty());

        imageView.setPreserveRatio(true);

        vBox.getChildren().addAll(new Label("Hello"), imageView);
//        getDialogPane().setContent(vBox);
//        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
////
//
//
//        Optional<Pair<String, Boolean>> result = showAndWait();
        SplitPane splitPane = new SplitPane();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        //creating the chart

        lineChart = new LineChart<Number, Number>(xAxis,yAxis);

//        lineChart.setTitle(fxmlLoader.getResources().getString("TestOfMachine"));
        //defining a series
        series = new XYChart.Series();
        series.setName("Random values");    //Put in resourses
        //populating the series with data
        populateSeries(machineTestModel.getRandomValuesList());


        splitPane.getItems().addAll(vBox,lineChart);


//        getChildren().add(lineChart);
            setCenter(splitPane);
    }

    private void populateSeries(ArrayList<Double> list) {
        for(int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data(i, list.get(i)));
        }

        lineChart.getData().add(series);
    }


    @Override
    public void update(Observable o, Object arg) {
        machineTestModel = (MachineTestModel)o;
    }
}
