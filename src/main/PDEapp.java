package main;

import javafx.application.Application;

import persistence.loader.DataSet;
import trestmodel.TrestModel;
import trestui.TrestViewFX;
import trestui.rootPane.RootPane;

/**
 * Created by pom on 1/23/2016.
 */
public class PDEapp {
    public static void main(String[] args) {


        TrestModel trestModel = new TrestModel();

     //   TrestViewFX.launch();
        Application uiApp = new TrestViewFX( args, trestModel);


       DataSet ds = ((RootPane) ((TrestViewFX)uiApp).rootPane).dataSet;

        System.out.println("Привет"+ds);

    }
}
