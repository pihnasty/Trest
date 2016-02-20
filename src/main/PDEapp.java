package main;

import javafx.application.Application;
import trestmodel.TrestModel;
import trestui.TrestViewFX;

/**
 * Created by pom on 1/23/2016.
 */
public class PDEapp {
    public static void main(String[] args) {


        TrestModel trestModel = new TrestModel();

        Application uiApp = new TrestViewFX( args, trestModel);
    }
}
