package trestview.dictionary;

import entityProduction.Work;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import resources.images.icons.IconT;
import trestview.hboxpane.HboxpaneController;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.HboxpaneView;
import trestview.table.TableController;
import trestview.table.TableViewP;
import trestview.table.tablemodel.TableModel;
import trestview.table.tablemodel.TableMolelBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by pom on 07.02.2016.
 */
public class DictionaryView extends Dialog implements Observer {

    private DictionaryModel dictionaryModel;



    public DictionaryView() {
    }

    public DictionaryView (DictionaryModel dictionaryModel, DictionaryController dictionaryController ) {
        this.dictionaryModel =dictionaryModel;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dictionaryView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
     //  this.getOwner().getStylesheets().add((getClass().getResource("stylesMenu2.css")).toExternalForm());
     //   getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( dictionaryController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


       //  setTitle("%Open");
        setTitle(fxmlLoader.getResources().getString("Dictionary")+":  "+fxmlLoader.getResources().getString("Work"));
        setHeaderText(fxmlLoader.getResources().getString("HeaderText"));
        setResizable(true);
       this.getDialogPane().setMinWidth(1500);

        setWidth(1500);

       // this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));


       // getDialogPane().getGraphic().getScene().getWindow().set
     //   ArrayList icons = new ArrayList();
      //  icons.add(new Image(IconT.class.getResource("work1.png").toString()));
     //   this.getOwner().getScene().getWindow().impl_getPeer().setIcons(icons);

        setGraphic( new ImageView(new Image(IconT.class.getResource("work1.png").toString())));
      //  this.getDialogPane().setPrefWidth(455);
       // this.getDialogPane().isScaleShape();
        TableModel tableModel = ( TableModel)TableMolelBuilder.build(dictionaryModel, Work.class); //  new TableModel(this.dictionaryModel.getTMenuModel().getTrestModel().getTrest().getWorks(), Work.class);
        TableController tableController = new TableController(tableModel);
        TableViewP tableView = new TableViewP(tableModel, tableController);
        tableModel.addObserver(tableView);


        HboxpaneModel hboxpaneModel = new HboxpaneModel(dictionaryModel, Work.class);
        HboxpaneController hboxpaneController = new HboxpaneController(hboxpaneModel);
        HboxpaneView hboxpaneView = new HboxpaneView(hboxpaneModel, hboxpaneController);
        hboxpaneModel.addObserver(hboxpaneView);
        hboxpaneModel.addObserver(tableModel);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(hboxpaneView,tableView);
        vbox.setSpacing(5);   // The amount of vertical space between each child in the vbox.
        vbox.setPadding(new Insets(10, 0, 0, 10));   // The top,right,bottom,left padding around the region's content. This space will be included in the calculation of the region's minimum and preferred sizes. By default padding is Insets.EMPTY and cannot be set to null.

        getDialogPane().setContent( vbox);
      //  getDialogPane().setContent(tableView);

        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(searchButtonType,ButtonType.CANCEL);        // Create the layout for the controls.

        Optional<Pair<String, Boolean>> result = showAndWait();        // Display dialog box and wait for user response.

        // If the user closed the dialog box via the search button, output the
        // chosen search text and case-sensitive search status.



    }

    @Override
    public void update(Observable o, Object arg) {
        dictionaryModel = (DictionaryModel)o;
    }
}
