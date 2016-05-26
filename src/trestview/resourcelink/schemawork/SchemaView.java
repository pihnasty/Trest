package trestview.resourcelink.schemawork;

import entityProduction.Work;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SchemaView extends BorderPane implements Observer {

    private ImageView imageview = new ImageView();
    private SchemaModel schemaModel;
    private Work work;
    private Double scaleScheme;

    private BorderPane bp;

    private DoubleBinding kScale;
    private DoubleBinding hImv;
    private DoubleBinding wImv;
    private List<Q> qs;

    public SchemaView(SchemaModel schemaModel, SchemaController schemaController) {

        bp = new BorderPane();
        getChildren().addAll(bp);
        qs = schemaModel.getQs();

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
            q.setRotate(q.getAngle());
            q.setLayoutX(q.getX());
            q.setLayoutY(q.getY());

        }
        if (imageview != null)           bp.getChildren().addAll(imageview);
        if (schemaModel.getQs() != null) bp.getChildren().addAll(schemaModel.getQs());


    }


    @Override
    public void update(Observable o, Object arg) {

        changeCursor(o);

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
                return 1;
                        //0.85*getHeight()/imageview.getImage().getHeight() ;
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

    public Q find(Observable o,String s) {

        Point p =  ((SchemaModel) o).getePoint();
        for (int i = 0; i < qs.size(); i++) {
            Q q = qs.get(i);
            if (q.contains(p.getX()*kScale.getValue(), p.getY()*kScale.getValue())) {
                System.out.println(p.getX()/kScale.getValue()+"     "+ p.getY()/kScale.getValue()+"   "+q.getIdQ()+"  "+q.getX()+"     "+ q.getY()+"  "+q.getWidth()+"     "+ q.getHeight()+ "  "+q.getLayoutX()+"     "+ q.getLayoutY() );
                return q;
            }
        }
        return null;
    }

    public Q find(Observable o) {

        Point p =  ((SchemaModel) o).getePoint();
        for (int i = 0; i <   bp.getChildren().size(); i++) {
          if (bp.getChildren().get(i).getClass()==Q.class) {
              Q q = (Q) bp.getChildren().get(i);
              if (q.contains(p.getX()-q.getLayoutX() , p.getY()-q.getLayoutY() )) {
                  System.out.println(p.getX() / kScale.getValue() + "     " + p.getY() / kScale.getValue() + "   " + q.getIdQ() + "  " + q.getX() + "     " + q.getY() + "  " + q.getWidth() + "     " + q.getHeight() + "  " + q.getLayoutX() + "     " + q.getLayoutY());
                  return q;
              }
          }
        }
        return null;
    }

    public void changeCursor(Observable o) {
        if (find(o) == null) {
            setCursor(Cursor.DEFAULT);
        } else {
            setCursor(Cursor.HAND);
        }

    }
}
