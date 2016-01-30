package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import resources.CsControl;
import resources.UTF8Control;
import ui.rootPane.RootPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplicationFX extends Application {


    @Override
    public void start(Stage stage) throws Exception{


        setLocale("en");

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("resources.ui"));

      //  System.out.println( loader.getResources().getString("labelText"));


        RootPane rootPane = new RootPane();
        stage.setScene(new Scene(rootPane));
        stage.setTitle(loader.getResources().getString("stageTitle"));
        stage.setWidth(300);
        stage.setHeight(200);

        System.out.println((String)  rootPane.getChildren().toString());



        stage.show();

    }

    /**
     *
     * @param s
     */
    private void setLocale(String s) {
        Locale locale = Locale.getDefault();

        Locale.setDefault( new Locale(s));
        System.out.println(locale.getLanguage());
        // locale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();

        // Locale.setDefault( Locale.GERMAN);

        System.out.println(locale.getDisplayName(Locale.getDefault()));
    }


    public static void main(String args) {
        launch(args);
    }

}
