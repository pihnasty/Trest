package trestview.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import persistence.loader.DataSet;
import persistence.loader.XmlRW;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pom on 07.02.2016.
 */
public class TMenuController implements Initializable {

    private static TMenuModel menuModel;

    @FXML
    private Menu fileMenu;

    @FXML   private MenuItem newItem;
    @FXML   private MenuItem openItem;
    @FXML   private MenuItem saveItem;
    @FXML   private MenuItem saveAsItem;
    @FXML   private MenuItem saveAllItem;
    @FXML   private MenuItem exitItem;

    @FXML   private MenuItem cutItem;
    @FXML   private MenuItem copyItem;
    @FXML   private MenuItem pasteItem;

    @FXML   private MenuItem defaultPerspectiveItem;
    @FXML   private MenuItem ordersPerspectiveItem;
    @FXML   private MenuItem routesPerspectiveItem;
    @FXML   private MenuItem machinesLocationPerspectiveItem;


    public TMenuController() {

    }

    public TMenuController(TMenuModel menuModel) {

        this.menuModel = menuModel;
        System.out.println("_________________________________________________________________________________________1"+this.menuModel);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("_________________________________________________________________________________________2"+this.menuModel);
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
    private void handleOpenAction (ActionEvent event) {
        System.out.println("_________________________________________________________________________________________3"+this.menuModel);
        String pathData = XmlRW.getPathData();									// �������� ���� �� ��������� (���� � �������� ��������)
        System.out.println("---------------"+pathData);

        menuModel.getTrestModel().getDataSet().saveDataset();
        menuModel.getTrestModel().getDataSet();


        savePathConfig(pathData, true);											// ���������� ��������� ����������������� ����� (���������� ����� �������� ���� � ��)
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

    /**
     * ���������� ����� �������� ���� � �� � congig.xml ���� � ����������� ��������� � DataSet �������� �� ��.
     * @param pathData  	���� � �������� ��� ����������/������ ��
     * @param boolOpenDataSet		����. ��� boolOpenDataSet=true ���������� �������� ��, ������� ��������� �� ���������� ����.
     */
    public void savePathConfig(String pathData, boolean boolOpenDataSet)
    {
        if (pathData != "")						// ������� �� ������� ���� "" ��� ������� �� ����� ����������� ����
        {
            menuModel.getTrestModel().getDataSet().setPathDataDefault(pathData);	    	// ������������� ����� ��������, ������������� �� ���������, �������� ��������, ��� ��������� ����� � ��.
            // ��� �������� ������������� ���������� �������� � ����� �����, ������ ������� ���������� � ������
            // ��� �������� ���� � ��������, � ���� ��������� �������������� ����� +"\\" ����� ������ �����
            String pathSet =  menuModel.getTrestModel().getDataSet().getPathDataDefault();			// ���������� ����� �������� ���� � �� �� ��������� � ���������� ��� � ���������� (pathSet)
            menuModel.getTrestModel().getDataSet().tSettings.get(0).setSystemPath(pathSet);			// �������� ����� �������� ��� ���� � ��, ������� �������� � ���� ��������  ����� ������� tabSettings

            // �1������ �������� �������� ���� �� ��������� � �� � ������� DataSet. ��� ����������, ����� �� ������� ����� ����� writeTab(DataSet.tSettings)
            menuModel.getTrestModel().getDataSet().setPathDataDefault(DataSet.getPathConfig());		// ��� ����, ����� �������� ��������� � ���� ��������, ������������ ������� writeTab(DataSet.tabSettings)
            // ������� ����� �� �������, ������� ����� ��������� ���� PathDataDefault. �� ��� ��� ��� ���� ��������
            // � � ������� � �����, ���������� � PathConfig, �� ��������������� PathDataDefault=PathConfig.
            // ����� �������� DataSet ���� ���� ����� ������� �� ������ � ����������������� �����
            menuModel.getTrestModel().getDataSet().writeTab(DataSet.tSettings);		    			// ���������� ����, ���������� ������ � ����������� (������� ���� ������� tabSettings) � ���������������� ����
            //  �1End     ���� ������ � ���������������� ���� ��������������� �������� ��������  ���� � ��, ������� ���� �� ���������

            menuModel.getTrestModel().getDataSet().setPathDataDefault(pathSet);						// ������������� ����� ��������, ������������� �� ���������, �������� ��������, ��� ��������� ����� � ��.

            if (boolOpenDataSet)
            {
                menuModel.getTrestModel().getDataSet().openDataSet();										// ��������� ���� dataSet � ((MenuFrame)fm).dataSet ������ ����������, � ������ ��������� ������������
                menuModel.getTrestModel().setTrest(   menuModel.getTrestModel().getDataSet().getTrest(1)    );	// �������������� ������ ����� �� id
            }
        }
    }














}
