package ui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import loader.DataSet;
import ui.rootPane.RootPane;

import java.awt.*;

import java.util.Locale;
import java.util.ResourceBundle;


public class MainApplicationFX extends Application {
    /**
     *  To start  launch(args) you need:
     *  1.Explicitly create a default constructor (no arguments);
     *  2.Create a constructor with arguments and call it method.
     */

    public final int DEFAULT_WIDTH =  Toolkit.getDefaultToolkit().getScreenSize().width;
    public final int DEFAULT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public MainApplicationFX( ) {
        String [] st = {""};
      //    launch(st);
    }
    public MainApplicationFX(String[] args) {
         launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        DataSet dataSet = new DataSet();
        RootPane rootPane = new RootPane(dataSet);



        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("resources.ui"));
        setLocale("");

        stage.setTitle(loader.getResources().getString("stageTitle"));

        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);

       Image im=new Image(this.getClass().getResource("work3.png").toString());
  //      System.out.println("Height: "+im.getHeight()+"Width: "+im.getWidth());
        ImageView imv=new ImageView(im);


        rootPane.setBottom(imv);



        //  System.out.println( loader.getResources().getString("labelText"));










        stage.setScene(new Scene(rootPane));



        stage.setWidth(DEFAULT_WIDTH/2 );
        stage.setHeight(DEFAULT_HEIGHT/2);
      //  stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Set the language for the application
     * @param s Language of the application ( new Locale(s))
     */
    private void setLocale(String s) {
        Locale locale = Locale.getDefault();
        if (s!="") Locale.setDefault(new Locale(s));
        System.out.println(locale.getLanguage());
        // locale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        // Locale.setDefault( Locale.GERMAN);
        System.out.println(locale.getDisplayName(Locale.getDefault()));
    }
}
