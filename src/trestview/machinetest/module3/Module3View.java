package trestview.machinetest.module3;

import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 15.04.2016.
 */
public class Module3View extends HBox implements Observer {

    Module3Model module3Model;
    Module3Controller module3Controller;
    XYChart.Series<Number, Number> series;
    XYChart.Series<String, Number> seriesBar;
    LineChart<Number, Number> lineChart;
    BarChart<String, Number> barChart;//<String, Number> --> <Number, Number>
    final NumberAxis xAxis;
    final NumberAxis yAxis;

    final CategoryAxis xAxis1;
    final NumberAxis yAxis1;


    public Module3View(Module3Model model, Module3Controller controller) {
         xAxis = new NumberAxis();
         yAxis = new NumberAxis();
         xAxis1 = new CategoryAxis();
         yAxis1 = new NumberAxis();

        this.lineChart = new LineChart<Number, Number>(xAxis , yAxis);
        this.barChart = new BarChart<String, Number>(xAxis1, yAxis1);
        this.module3Model = model;
        this.module3Controller = controller;
        series = new XYChart.Series<>();
        seriesBar = new XYChart.Series<>();
        redrawChart();
        lineChart.getData().add(series);
        barChart.getData().add(seriesBar);
        getChildren().addAll(lineChart, barChart);
    }

    private void redrawChart() {
        series.getData().removeAll();
////        lineChart.getData().removeAll();
        for(int i = 0; i < module3Model.getRandomValuesList().size(); i++) {
            series.getData().add(new XYChart.Data(i, module3Model.getRandomValuesList().get(i)));

        }

//        lineChart.getData().add(series);
        System.out.println("In redrawChart()");
    }

    @Override
    public void update(Observable o, Object arg) {
        redrawChart();
    }



}
