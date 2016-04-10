package trestview.resourcelink;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import resources.images.icons.IconT;
import trestview.hboxpane.HboxpaneController;
import trestview.hboxpane.HboxpaneModel;
import trestview.hboxpane.HboxpaneView;
import trestview.table.TableController;
import trestview.table.TableViewP;
import trestview.table.tablemodel.TableModel;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * Created by pom on 07.02.2016.
 */
public class  ResourceLinkView extends BorderPane implements Observer {

    public Observable resourceLinkModel;
    public ResourceLinkView (ResourceLinkModel resourceLinkModel, ResourceLinkController resourceLinkController ) {
        //this.dictionaryModel =dictionaryModel;
        //this.dataSet =dictionaryModel.getTMenuModel().getTrestModel().getDataSet();
        FXMLLoader fxmlLoader = XmlRW.fxmlLoad(this,resourceLinkController, "resourceLinkView.fxml","resources.ui", "");


/*        TableModel tableModel = new  TableModel(dataSet, dictionaryModel.gettClass());           //( TableModel)TableMolelBuilder.build(dictionaryModel, dictionaryModel.gettClass()); //  new TableModel(this.dictionaryModel.getTMenuModel().getTrestModel().getTrest().getWorks(), Work.class);
        TableController tableController = new TableController(tableModel);
        TableViewP tableView = new TableViewP(tableModel, tableController);
        tableModel.addObserver(tableView);

        //  public   MVC (Class mClass, Class cClass, Class vClass, Observable o, Class cL )

   //    MVC hboxpaneMVC = new MVC (HboxpaneModel.class,HboxpaneController.class,HboxpaneView.class, dictionaryModel, dictionaryModel.gettClass());

        HboxpaneModel hboxpaneModel = new HboxpaneModel(dataSet, dictionaryModel.gettClass());
        HboxpaneController hboxpaneController = new HboxpaneController(hboxpaneModel);
        HboxpaneView hboxpaneView = new HboxpaneView(hboxpaneModel, hboxpaneController);
        hboxpaneModel.addObserver(hboxpaneView);
        hboxpaneModel.addObserver(tableModel);


        VBox vbox = new VBox();
        vbox.getChildren().addAll(hboxpaneView,tableView);
        //vbox.getChildren().addAll((HboxpaneView) hboxpaneMVC.getView(),(TableViewP) tableMVC.getView());
        vbox.setSpacing(5);   // The amount of vertical space between each child in the vbox.
        vbox.setPadding(new Insets(10, 0, 0, 10));   // The top,right,bottom,left padding around the region's content. This space will be included in the calculation of the region's minimum and preferred sizes. By default padding is Insets.EMPTY and cannot be set to null.

        getDialogPane().setContent( vbox);
      //  getDialogPane().setContent(tableView);

        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(searchButtonType,ButtonType.CANCEL);        // Create the layout for the controls.

        Optional<Pair<String, Boolean>> result = showAndWait();        // Display dialog box and wait for user response.

        // If the user closed the dialog box via the search button, output the
        // chosen search text and case-sensitive search status.

*/
    }

    @Override
    public void update(Observable o, Object arg) {
        resourceLinkModel = o;
    }
}
