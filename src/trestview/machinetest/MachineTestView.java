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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import persistence.loader.XmlRW;
import trestview.machinetest.charts.ChartCommonModel;
import trestview.machinetest.charts.ChartController;
import trestview.machinetest.charts.ChartView;
import trestview.machinetest.module3.Controller3;
import trestview.machinetest.module3.Model3;
import trestview.machinetest.module3.View3;


import java.io.IOException;
import java.util.*;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestView extends BorderPane implements Observer {
    private MachineTestModel machineTestModel;

//    LineChart<Number,Number> lineChart;
    ChartView chartView;
    ChartView chartView1;
    ChartView chartView2;

    XYChart.Series series;

    public MachineTestView(MachineTestModel machineTestModel, MachineTestController machineTestController) {
        this.machineTestModel = machineTestModel;
        XmlRW.fxmlLoad(this, machineTestController,"machinetestView.fxml", "resources.ui", null);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("machinetestView.fxml"));
//        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(machineTestController);
//
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }

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

        final NumberAxis xAxis1 = new NumberAxis();
        final NumberAxis yAxis1 = new NumberAxis();

        final NumberAxis xAxis2 = new NumberAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        //----------------------------------------------------//
        Model3 model3 = new Model3();
        Controller3 controller3 = new Controller3(model3);
        View3 view3 = new View3(xAxis, yAxis, model3, controller3);
        model3.addObserver(view3);

        ChartCommonModel chartCommonModel1 = new ChartCommonModel();
        ChartController chartController1 = new ChartController(chartCommonModel1);
        chartView1 = new ChartView(xAxis1, yAxis1, chartCommonModel1, chartController1);
        machineTestModel.addObserver(chartView1);

        ChartCommonModel chartCommonModel2 = new ChartCommonModel();
        ChartController chartController2 = new ChartController(chartCommonModel2);
        chartView2 = new ChartView(xAxis2, yAxis2, chartCommonModel2, chartController2);
        machineTestModel.addObserver(chartView2);

        //---------------------------------------------------//

//        xAxis.setLabel("X");
        series = new XYChart.Series();
//        series.setName("Random values");    //Put in resourses


//        lineChart = new LineChart<Number, Number>(xAxis,yAxis);

        //populating the series with data
        populateSeries(machineTestModel.getRandomValuesList());

        VBox vBox1 = new VBox(5);
        vBox1.setSpacing(5);
//        chartView.setMaxSize(600, 400);
        chartView1.setMaxSize(600, 400);
        chartView2.setMaxSize(600, 400);
        vBox1.getChildren().addAll(view3, chartView1, chartView2);
        splitPane.getItems().addAll(vBox, vBox1);

        setCenter(splitPane);
    }

    private void populateSeries(ArrayList<Double> list) {
        for(int i = 0; i < list.size(); i++) {
            series.getData().add(new XYChart.Data(i, list.get(i)));
        }

        chartView1.getData().add(series);
//        chartView1.getData().add(series);
//        chartView2.getData().add(series);
    }


    @Override
    public void update(Observable o, Object arg) {
        machineTestModel = (MachineTestModel)o;
    }
}
