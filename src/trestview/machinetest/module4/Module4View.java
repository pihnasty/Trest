package trestview.machinetest.module4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 15.04.2016.
 */
public class Module4View extends HBox implements Observer {


    Module4Model module4Model;
    Module4Controller module4Controller;
    XYChart.Series<Number, Number> series;
    XYChart.Series<Number, Number> seriesBar;
    LineChart<Number, Number> lineChart;
    final NumberAxis xAxis;
    final NumberAxis yAxis;


    public Module4View(Module4Model model, Module4Controller controller) {
        this.module4Model = model;
        this.module4Controller = controller;
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        this.lineChart = new LineChart<Number, Number>(xAxis , yAxis);
        series = new XYChart.Series<>();
        seriesBar = new XYChart.Series<>();
        lineChart.getData().add(series);
        lineChart.setAnimated(false);
        getChildren().addAll(lineChart);

    }

    private void redrawChart(List<Double> listData, List<Double> listTime) {
        series.getData().clear();
        for(int i = 0; i < listData.size(); i++) {
            series.getData().add(new XYChart.Data(i, listData.get(i)));
        }
        System.out.println("In redrawChart()M4");
    }



    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass() == Module4Model.class) {
            Module4Model m4 = (Module4Model) o;
            redrawChart(m4.getExpectedValues(), null);
        }
    }



}
