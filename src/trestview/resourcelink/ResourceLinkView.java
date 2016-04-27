package trestview.resourcelink;

import designpatterns.MVC;
import entityProduction.Work;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowMachine;
import persistence.loader.tabDataSet.RowWork;
import resources.images.icons.IconT;
import trestview.hboxpane.HboxpaneController;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.HboxpaneView;
import trestview.table.TableController;
import trestview.table.TableViewP;
import trestview.table.tablemodel.TableModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * Created by pom on 07.02.2016.
 */
public class  ResourceLinkView extends BorderPane implements Observer {

    private Observable resourceLinkModel;
    private DataSet dataSet;
    public ResourceLinkView (ResourceLinkModel resourceLinkModel, ResourceLinkController resourceLinkController ) {
        this.dataSet = resourceLinkModel.getDataSet();
        //this.dictionaryModel =dictionaryModel;
        //this.dataSet =dictionaryModel.getTMenuModel().getTrestModel().getDataSet();
        FXMLLoader fxmlLoader = XmlRW.fxmlLoad(this,resourceLinkController, "resourceLinkView.fxml","resources.ui", "resourceLinkStyle.css");

        SplitPane splitPane = new SplitPane();
        SplitPane splitPaneInner = new SplitPane();

//----------------------------------------------------------------------------------------------------------------------
        TableModel b = new TableModel(dataSet.getTabWorks());
        MVC tableWorkMVC  = new MVC (TableModel.class, TableController.class, TableViewP.class, dataSet, RowWork.class );
        MVC hboxpaneWorkMVC = new MVC (HboxpaneModel.class,HboxpaneController.class,HboxpaneView.class,dataSet, RowWork.class);
        hboxpaneWorkMVC.addObserverP( (TableModel)tableWorkMVC.getModel());

        VBox vboxWork = new VBox();

        Label labelWork = new Label(fxmlLoader.getResources().getString("ListManufacturing"));
        labelWork.setGraphic(new ImageView(new Image(IconT.class.getResource("RowWork.png").toString())));

        vboxWork.getChildren().addAll(labelWork,(HboxpaneView)hboxpaneWorkMVC.getView(),(TableViewP)tableWorkMVC.getView());
        vboxWork.setSpacing(5);   // The amount of vertical space between each child in the vbox.
        vboxWork.setPadding(new Insets(10, 0, 0, 10));   // The top,right,bottom,left padding around the region's content. This space will be included in the calculation of the region's minimum and preferred sizes. By default padding is Insets.EMPTY and cannot be set to null.
//----------------------------------------------------------------------------------------------------------------------
        MVC tableMacineMVC  = new MVC (TableModel.class, TableController.class, TableViewP.class, dataSet, RowMachine.class );
        MVC hboxpaneMVCmacine = new MVC (HboxpaneModel.class,HboxpaneController.class,HboxpaneView.class,dataSet, RowMachine.class);
        hboxpaneMVCmacine.addObserverP( (TableModel)tableMacineMVC .getModel());

        VBox vboxMacine = new VBox();
        Label labelMacine = new Label(fxmlLoader.getResources().getString("ListEquipment"));
        labelMacine.setGraphic(new ImageView(new Image(IconT.class.getResource("RowMachine.png").toString())));

        vboxMacine.getChildren().addAll(labelMacine,(HboxpaneView)hboxpaneMVCmacine.getView(),(TableViewP)tableMacineMVC.getView());
        vboxMacine.setSpacing(5);   // The amount of vertical space between each child in the vbox.
        vboxMacine.setPadding(new Insets(30, 0, 0, 10));   // The top,right,bottom,left padding around the region's content. This space will be included in the calculation of the region's minimum and preferred sizes. By default padding is Insets.EMPTY and cannot be set to null.
//----------------------------------------------------------------------------------------------------------------------
        VBox vboxSplitPaneLeft = new VBox();
        vboxSplitPaneLeft.getChildren().addAll(vboxWork,vboxMacine);
        vboxSplitPaneLeft.setSpacing(5);   // The amount of vertical space between each child in the vbox.
        vboxSplitPaneLeft.setPadding(new Insets(10, 0, 0, 10));   // The top,right,bottom,left padding around the region's content. This space will be included in the calculation of the region's minimum and preferred sizes. By default padding is Insets.EMPTY and cannot be set to null.
//----------------------------------------------------------------------------------------------------------------------

        final StackPane sp2 = new StackPane();
        sp2.getChildren().add(new Button("Button Two"));

        final StackPane sp3 = new StackPane();
        sp3.getChildren().add(new Button("Button Tree"));

        splitPane.getItems().addAll(vboxSplitPaneLeft, splitPaneInner);

        splitPaneInner.getItems().addAll(sp2, sp3);
        splitPaneInner.setDividerPositions(0.3f, 0.6f);
        splitPaneInner.setOrientation(Orientation.VERTICAL);


        splitPane.setDividerPositions(0.2f, 0.6f);


         setCenter(splitPane);


    }

    @Override
    public void update(Observable o, Object arg) {
        resourceLinkModel = o;
    }
}
