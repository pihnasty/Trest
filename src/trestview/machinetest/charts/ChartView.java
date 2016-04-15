package trestview.machinetest.charts;

import javafx.beans.NamedArg;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Роман on 15.04.2016.
 */
public class ChartView extends LineChart implements Observer {


    public ChartView(@NamedArg("xAxis") Axis x, @NamedArg("yAxis") Axis y) {
        super(x, y);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
