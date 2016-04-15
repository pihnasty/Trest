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
import trestview.machinetest.charts.ChartCommonModel;
import trestview.machinetest.charts.ChartController;
import trestview.machinetest.charts.ChartView;


import java.io.IOException;
import java.util.*;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestView extends BorderPane implements Observer {
    private MachineTestModel machineTestModel;

//    LineChart<Number,Number> lineChart;
    ChartView chartView;

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
        Image image = new Image("file:Image\\Machine\\freza_02.png");

        ImageView imageView = new ImageView();
        imageView.setImage(image);
//        imageView.fitWidthProperty().bind(widthProperty());

        imageView.setPreserveRatio(true);

        vBox.getChildren().addAll(imageView, machineTestModel.getTableView());

        SplitPane splitPane = new SplitPane();

        //creating the chart
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        //----------------------------------------------------//
        ChartCommonModel chartCommonModel = new ChartCommonModel();
        ChartController chartController = new ChartController(chartCommonModel);
        chartView = new ChartView(xAxis, yAxis);
        machineTestModel.addObserver(chartView);


        //---------------------------------------------------//

        xAxis.setLabel("X");
        series = new XYChart.Series();
        series.setName("Random values");    //Put in resourses


//        lineChart = new LineChart<Number, Number>(xAxis,yAxis);

        //populating the series with data
        populateSeries(machineTestModel.getRandomValuesList());


        splitPane.getItems().addAll(vBox,chartView);

        setCenter(splitPane);
    }

    private void populateSeries(ArrayList<Double> list) {
        for(int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data(i, list.get(i)));
        }

        chartView.getData().add(series);
    }


    @Override
    public void update(Observable o, Object arg) {
        machineTestModel = (MachineTestModel)o;
    }
}
