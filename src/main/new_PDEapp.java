package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.images.icons.IconT;
import trestcontroller.TrestController;
import trestmodel.TrestModel;
import trestview.TrestView;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * Created by pom on 1/23/2016.
 */
public class new_PDEapp extends Application {
    /**
     * To start  launch(args) you need:
     * 1.Explicitly create a default constructor (no arguments);
     * 2.Create a constructor with arguments and call it method.
     */
    public final int DEFAULT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public final int DEFAULT_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
            launch();
    }


    @Override
    public void start(Stage stage) throws Exception {

        //region Trest MVC
        TrestModel trestModel = new TrestModel();
        TrestView trestView = new TrestView(trestModel);
        TrestController trestController = new TrestController();
        //endregion

        //region Methods of specifying the icon.
        //  1.------------------------------------------
        //      Image im = new Image(this.getClass().getResource("../resources/images/icons/work3.png").toString());
        //      Image(IconT.class.getResource("work3.png").toString())
        //      ----------------------------------------
        //  2.  ImageView imv = new ImageView(im);
        //  3.  stage.getIcons().add(imv);
        stage.getIcons().add(new Image(IconT.class.getResource("work3.png").toString()));
        //endregion

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("resources.ui"));
        stage.setTitle(loader.getResources().getString("stageTitle"));
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.setScene(new Scene(trestView));
        stage.setWidth(DEFAULT_WIDTH / 2);
        stage.setHeight(DEFAULT_HEIGHT / 2);
        stage.show();
    }
}
