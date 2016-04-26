package trestview.machinetest.module3;

import javafx.beans.NamedArg;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import trestview.machinetest.charts.ChartCommonModel;
import trestview.machinetest.charts.ChartController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 15.04.2016.
 */
public class View3 extends Pane implements Observer {

    Model3 model3;
    Controller3 controller3;
    XYChart.Series<Number, Number> series;
    LineChart<Number, Number> lineChart;

    public View3(Axis x, Axis y, Model3 model, Controller3 controller) {
        this.lineChart = new LineChart<Number, Number>(x , y);
        this.model3 = model;
        this.controller3 = controller;
        series = new XYChart.Series<>();
        redrawChart();
        getChildren().add(lineChart);
    }

    private void redrawChart() {
        series.getData().removeAll();
        lineChart.getData().removeAll();
        for(int i = 0; i < model3.getRandomValuesList().size(); i++) {
            series.getData().add(new XYChart.Data(i, model3.getRandomValuesList().get(i)));
        }
        lineChart.getData().add(series);
    }

    @Override
    public void update(Observable o, Object arg) {
        redrawChart();
    }
}
