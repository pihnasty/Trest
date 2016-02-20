package trestui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import persistence.loader.DataSet;
import trestmodel.TrestModel;
import trestui.rootPane.RootPane;

import java.awt.*;

import java.util.Locale;
import java.util.ResourceBundle;


public class TrestViewFX extends Application {
    /**
     * To start  launch(args) you need:
     * 1.Explicitly create a default constructor (no arguments);
     * 2.Create a constructor with arguments and call it method.
     */

    public final int DEFAULT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final int DEFAULT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public Locale locale;
    int w =1;
    DataSet dataSet;
    public BorderPane rootPane;


    private TrestModel trestModel;

    public TrestViewFX() {
    }


    public TrestViewFX(String[] args,  TrestModel trestModel)  {
        this.trestModel = trestModel;
        this.dataSet = this.trestModel.getDataSet();
        w=w+1;
        launch(args);
        System.out.println("1------------------------------"+w);
        try {
        //    start(new Stage() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    @Override
    public void start(Stage stage) throws Exception {

                    setLocale("en"); //en

                    System.out.println("2------------------------------" + w);


                    rootPane = new RootPane(dataSet);






                    Image im = new Image(this.getClass().getResource("work3.png").toString());
                    ImageView imv = new ImageView(im);
                    stage.getIcons().add(new Image(this.getClass().getResource("work3.png").toString()));
                    FXMLLoader loader = new FXMLLoader();
                    loader.setResources(ResourceBundle.getBundle("resources.ui"));
                    stage.setTitle(loader.getResources().getString("stageTitle"));
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setMaximized(true);

                    stage.setScene(new Scene(rootPane));
                    stage.setWidth(DEFAULT_WIDTH / 2);
                    stage.setHeight(DEFAULT_HEIGHT / 2);
                    stage.show();

    }

    /**
     * Set the language for the application
     *
     * @param s Language of the application ( new Locale(s))
     */
    private void setLocale(String s) {
        locale = Locale.getDefault();
        if (s != "") {
            locale = new Locale(s);
            Locale.setDefault(locale);
            System.out.println("s=" + s);
        }
    }
}
