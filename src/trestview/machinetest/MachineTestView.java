package trestview.machinetest;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import persistence.loader.XmlRW;


import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestView extends HBox implements Observer {
    private MachineTestModel machineTestModel;

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

//        VBox vBox = new VBox();
//        vBox.setSpacing(5);
//        vBox.setPadding(new Insets(10, 5, 5, 10));

//        getDialogPane().setContent(vBox);
//        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
////
//
//
//        Optional<Pair<String, Boolean>> result = showAndWait();
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle(fxmlLoader.getResources().getString("TestOfMachine"));
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Random values");    //Put in resourses
        //populating the series with data
        populateChart(series);


        lineChart.getData().add(series);

        getChildren().add(lineChart);

    }

    private void populateChart(XYChart.Series series) {
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
    }


    @Override
    public void update(Observable o, Object arg) {
        machineTestModel = (MachineTestModel)o;
    }
}
