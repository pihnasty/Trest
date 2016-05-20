package trestview.resourcelink.schemawork;

import entityProduction.Work;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


import java.util.Observable;
import java.util.Observer;

public class SchemaView extends BorderPane implements Observer {

    private ImageView imageview = new ImageView();
    private SchemaModel schemaModel;
    private Work work;
    private Double scaleScheme;

    private BorderPane bp;

    private DoubleBinding kScale;
    private DoubleBinding hImv;
    private DoubleBinding wImv;

    public SchemaView(SchemaModel schemaModel, SchemaController schemaController) {

        bp = new BorderPane();
        getChildren().addAll(bp);


        setStyle("-fx-background-color: #336699;");
//region

//endregion


        setkScaleDefault();


        repaint(schemaModel);


        bp.scaleXProperty().bind(kScale);
        bp.scaleYProperty().bind(kScale);
    }

    private void repaint(SchemaModel schemaModel) {
        this.bp.getChildren().clear();
        this.work = schemaModel.getWork();
        this.imageview.setImage(new Image("file:"+work.getScheme() ));
        for(Q q: schemaModel.getQs()) {
            q.setLayoutX(q.getX().doubleValue());
            q.setLayoutY(q.getY().doubleValue());
            q.setRotate(q.getAngle());
        }
        if (imageview != null)           bp.getChildren().addAll(imageview);
        if (schemaModel.getQs() != null) bp.getChildren().addAll(schemaModel.getQs());


    }


    @Override
    public void update(Observable o, Object arg) {
        repaint((SchemaModel) o);
        setHeight(getHeight()+1);        setHeight(getHeight()-1);
    }

    double scaleHeight() {
        return (getHeight()/getWidth() < imageview.getFitHeight() / imageview.getFitWidth()) ? getHeight()/imageview.getFitHeight() : getWidth()/imageview.getFitWidth();
    }

    private void setkScaleDefault() {
        kScale = new DoubleBinding() {
            { super.bind(heightProperty()); }
            @Override
            protected double computeValue() {
                return 0.85*getHeight()/imageview.getImage().getHeight() ;
            }};

        hImv = new DoubleBinding() {
            { super.bind(heightProperty()); }
            @Override
            protected double computeValue() {
                return kScale.getValue()*imageview.getImage().getHeight();
            }};

        wImv = new DoubleBinding() {
            { super.bind(heightProperty()); }
            @Override
            protected double computeValue() {
                return kScale.getValue()*imageview.getImage().getWidth();
            }};
    }

}
