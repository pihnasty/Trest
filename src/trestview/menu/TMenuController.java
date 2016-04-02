package trestview.menu;

import designpatterns.MVC;
import entityProduction.Machine;
import entityProduction.Work;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;
import persistence.loader.tabDataSet.RowMachine;
import persistence.loader.tabDataSet.RowTypemachine;
import persistence.loader.tabDataSet.RowWork;
import trestmodel.TrestModel;
import trestview.dictionary.DictionaryController;
import trestview.dictionary.DictionaryModel;
import trestview.dictionary.DictionaryView;
import trestview.machinetest.MachineTestController;
import trestview.machinetest.MachineTestModel;
import trestview.machinetest.MachineTestView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuController implements Initializable {


    private TMenuModel menuModel;



    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem newItem;
    @FXML
    private MenuItem openItem;
    @FXML
    private MenuItem saveItem;
    @FXML
    private MenuItem saveAsItem;
    @FXML
    private MenuItem saveAllItem;
    @FXML
    private MenuItem exitItem;

    @FXML
    private MenuItem cutItem;
    @FXML
    private MenuItem copyItem;
    @FXML
    private MenuItem pasteItem;

    @FXML
    private MenuItem defaultPerspectiveItem;
    @FXML
    private MenuItem ordersPerspectiveItem;
    @FXML
    private MenuItem routesPerspectiveItem;
    @FXML
    private MenuItem machinesLocationPerspectiveItem;



    public TMenuController() {

    }

    public TMenuController(TMenuModel menuModel) {
        this.menuModel = menuModel;
        //System.out.println("_________________________________________________________________________________________1"+this.menuModel);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println("_________________________________________________________________________________________2"+this.menuModel);
        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAsItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        saveAllItem.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

        cutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));

        defaultPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        ordersPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        routesPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        machinesLocationPerspectiveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
    }


    @FXML
    private void handleOpenAction(ActionEvent event) {
        String pathData = XmlRW.getPathData();                                    // The directory path to the database that you selected in the dialog [DirectoryChooser()].
        menuModel.getTrestModel().getDataSet().saveDataset();                     // Save old DB
        savePathConfig(pathData, true);                                           // Save new path add read new database  from new directory path
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        menuModel.getTrestModel().getDataSet().saveDataset();                     // Save new path add read new database  from new directory path
    }


    @FXML
    private void handleSaveAsAction(ActionEvent event) {
        String XmlRW_pathDataWork= XmlRW.getSavePath("");			              // The directory path to the database that you selected in the dialog [DirectoryChooser()].
        savePathConfig(XmlRW_pathDataWork, false);			                      // Save new path add read new database  from new directory path
        menuModel.getTrestModel().getDataSet().saveDataset();
    }


    @FXML
    private void handleExitAction(ActionEvent event) {
        menuModel.getTrestModel().getDataSet().saveDataset();                     // Save new path add read new database  from new directory path
        System.exit(0);
        Platform.exit();
    }
//------------------- menu Dictionary ------------------------------------
    @FXML
    private void handleRowWorkAction (ActionEvent event) {
        new MVC(DictionaryModel.class, DictionaryController.class, DictionaryView.class, this.menuModel, RowWork.class );
}
    @FXML
    private void handleRowTypemachineAction (ActionEvent event) {
        new MVC(DictionaryModel.class, DictionaryController.class, DictionaryView.class, this.menuModel, RowTypemachine.class );
    //    new MVC(DictionaryModel.class, DictionaryController.class, DictionaryView.class, this.menuModel, RowMachine.class );
    }
    @FXML
    private void handleRowMachineAction (ActionEvent event) {
        new MVC(DictionaryModel.class, DictionaryController.class, DictionaryView.class, this.menuModel, RowMachine.class );
    }


    //------------------- menu MachineTest ------------------------------------
    @FXML
    private void handleMachineTestAction (ActionEvent event) {

        new MVC(MachineTestModel.class, MachineTestController.class, MachineTestView.class, this.menuModel  );
  /*      MachineTestModel machineTestModel = new MachineTestModel(this.menuModel);
        MachineTestController machineTestController = new MachineTestController(machineTestModel);
        MachineTestView machineTestView = new MachineTestView(machineTestModel, machineTestController);
        machineTestModel.addObserver(machineTestView);
*/
        System.out.println("Machine test in progress...");

    }





    /**
     * Add congig.xml
     * @param pathData          The directory path to the database, which (path) will be written to the file (tSettings).
     * @param boolOpenDataSet   If it is true - a database in the specified path is read into the model.
     */
    public void savePathConfig(String pathData, boolean boolOpenDataSet) {
        if (pathData != "") {
            menuModel.getTrestModel().getDataSet().setPathDataDefault(pathData);                    // Set the default path to the database.
            menuModel.getTrestModel().getDataSet().tSettings.get(0).setSystemPath(pathData);        // We set up a new path.
            menuModel.getTrestModel().getDataSet().setPathDataDefault(DataSet.getPathConfig());
            menuModel.getTrestModel().getDataSet().writeTab(DataSet.tSettings);                     // Write the changes to the file [tSettings]
            menuModel.getTrestModel().getDataSet().setPathDataDefault(pathData);
            if (boolOpenDataSet) {
                TrestModel trestModel = new TrestModel();           //  We get a new model from a the changed directory.
                menuModel.setTrestModel(trestModel);                //  We define a new model
            }
        }
    }
}
