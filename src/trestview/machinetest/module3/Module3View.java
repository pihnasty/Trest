package trestview.machinetest.module3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    XYChart.Series<Number, Number> seriesBar;
    ObservableList<String> ranks;

    LineChart<Number, Number> lineChart;
    BarChart barChart;//<String, Number> --> <Number, Number>
    final NumberAxis xAxis;
    final NumberAxis yAxis;

    final CategoryAxis xAxis1;
    final NumberAxis yAxis1;
    int i = 0;

    public Module3View(Module3Model model, Module3Controller controller) {
         ranks = FXCollections.observableArrayList();
         xAxis = new NumberAxis();
         yAxis = new NumberAxis();

         xAxis1 = new CategoryAxis();
         yAxis1 = new NumberAxis();

        this.lineChart = new LineChart<Number, Number>(xAxis , yAxis);
        this.barChart = new BarChart(xAxis1, yAxis1);
        this.module3Model = model;
        this.module3Controller = controller;
        series = new XYChart.Series<>();
        seriesBar = new XYChart.Series<>();
//        redrawChart();
        lineChart.getData().add(series);
        barChart.getData().add(seriesBar);
        getChildren().addAll(lineChart, barChart);
    }

    private void redrawChart() {
        series.getData().removeAll();
////        lineChart.getData().removeAll();
//        int i = module3Model.getRandomValuesList().size();
//            if(module3Model.getRandomValuesList().isEmpty())
//                return;

//            double val = module3Model.getRandomValuesList().get(module3Model.getRandomValuesList().size()-1);
//            series.getData().add(new XYChart.Data(i, val));
//            i++;


//        lineChart.getData().add(series);
        System.out.println("In redrawChart()");
    }

    private void redrawBarChart() {
        seriesBar.getData().removeAll();
//        double rank = module3Model.getMinValue();

        for (int i = 0; i < module3Model.getGroupedStatisticalSeries().size(); i++) {

            seriesBar.getData().add(new XYChart.Data(
                    module3Model.getRanks().get(i).toString(),
                    module3Model.getGroupedStatisticalSeries().get(i)));

//            rank += module3Model.getStep();
        }

//        System.out.println("In redrawBarChart() "+module3Model.getGroupedStatisticalSeries()
//                + " size " +module3Model.getRandomValuesList().size());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass() == Module3Model.class) {
            redrawChart();
            redrawBarChart();
        }
    }



}
