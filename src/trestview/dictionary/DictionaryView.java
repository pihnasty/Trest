package trestview.dictionary;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

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
     //   getStylesheets().add((getClass().getResource("stylesMenu.css")).toExternalForm());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController( dictionaryController);        // or  fx:controller="ui.rootPane.menu.TMenuController"
                                                // or <fx:root type="trestview.menu.TMenuView" xmlns:fx="http://javafx.com/fxml"  fx:controller="trestview.menu.TMenuController" >
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        setTitle("Search");
        setHeaderText("Enter search parameters");

        // Set the search icon.

        //  dialog.setGraphic(new ImageView(this.getClass().getResource("search.png").toString()));

        // Set the button types.

        ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(searchButtonType,
                ButtonType.CANCEL);

        // Create the layout for the controls.

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Create and initialize the search-text and case-sensitive-search controls.

        TextField srchText = new TextField();
        srchText.setPromptText("Search text");
        CheckBox css = new CheckBox("Case-sensitive search");

        // Populate the layout with a label along with the search text and
        // case-sensitive search controls.

        grid.add(new Label("Search Text:"), 0, 0);
        grid.add(srchText, 1, 0);
        grid.add(css, 0, 1);

        // Disable/enable search button depending on whether search-text field is
        // empty. Button defaults to being disabled.

        Node searchButton = getDialogPane().lookupButton(searchButtonType);
        searchButton.setDisable(true);
        srchText.textProperty().addListener((observable, oldValue, newValue) ->
        {
            searchButton.setDisable(newValue.trim().isEmpty());
        });

        // Install controls layout in the dialog panel.

        getDialogPane().setContent(grid);

        // Request focus on the search-text field. See
        // https://community.oracle.com/thread/2321126 for information on why
        // Platform.runLater() is used.

        Platform.runLater(() -> srchText.requestFocus());

        // Convert the result to a srchtext-css-status pair when the search button
        // is clicked.

        setResultConverter(dialogButton ->
        {
            if (dialogButton == searchButtonType)
                return new Pair<>(srchText.getText(), css.isSelected());
            return null;
        });

        // Display dialog box and wait for user response.

        Optional<Pair<String, Boolean>> result = showAndWait();

        // If the user closed the dialog box via the search button, output the
        // chosen search text and case-sensitive search status.

        result.ifPresent(stcss ->
        {
            System.out.println("Search text = " + stcss.getKey() +
                    ", Case-sensitive search = " + stcss.getValue());
        });
    }










    @Override
    public void update(Observable o, Object arg) {
        dictionaryModel = (DictionaryModel)o;
        System.out.println("New model");
    }
}
