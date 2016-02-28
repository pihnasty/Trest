package trestview;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import persistence.loader.DataSet;
import trestmodel.TrestModel;
import trestview.menu.TMenuController;
import trestview.menu.TMenuModel;
import trestview.menu.TMenuView;


import java.io.IOException;

/**
 * Sample custom control hosting a text field and a button.
 */
public class TrestView extends BorderPane {
    TrestModel trestModel;

    public TrestView() {
    }

    public TrestView(TrestModel trestModel) {
        this.trestModel =  trestModel;

        //region FXMLLoader fxmlLoader =
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trestview.fxml"));
        //   fxmlLoader.setResources(ResourceBundle.getBundle("resources.ui"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //endregion


        TMenuModel menuModel = new TMenuModel(this.trestModel);
        TMenuController menuController = new TMenuController(menuModel);
        TMenuView menuView = new TMenuView(menuModel, menuController);
        menuModel.addObserver(menuView);

        this.setTop(menuView);
    }
}
