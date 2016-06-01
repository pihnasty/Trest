package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import entityProduction.Work;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
          //  q.setRotate(q.getAngle());
            q.setLayoutX(q.getX());
            q.setLayoutY(q.getY());
        }
        if (imageview != null)           bp.getChildren().addAll(imageview);
        if (schemaModel.getQs() != null) bp.getChildren().addAll(schemaModel.getQs());
    }


    @Override
    public void update(Observable o, Object arg) {

        if(  ((SchemaModel) o).getMouseEvent().getEventType() ==   MouseEvent.MOUSE_MOVED) changeCursor(o);
        if(  ((SchemaModel) o).getMouseEvent().getEventType() ==   MouseEvent.MOUSE_PRESSED) {
            changeCursor(o);
            changeLocation(o);
        }

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
    }


    public Q find(Observable o) {

        MouseEvent mouseEvent =  ((SchemaModel) o).getMouseEvent();
        Point p =  new Point((int) mouseEvent .getX(), (int) mouseEvent.getY());
        for (int i = 0; i <   bp.getChildren().size(); i++) {
          if (bp.getChildren().get(i).getClass()==Q.class) {
              Q q = (Q) bp.getChildren().get(i);
              double x =  (p.getX()/kScale.getValue() -q.getLayoutX());
              double y =  (p.getY()/kScale.getValue() -q.getLayoutY() );
              double t = 2.0*Math.PI/360;
              double xAngle =   x*Math.cos(q.getAngle()*t) + y*Math.sin(q.getAngle()*t);
              double yAngle =   -x*Math.sin(q.getAngle()*t) + y*Math.cos(q.getAngle()*t);
              if (q.getrOuter(). contains(xAngle ,yAngle) ){
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

    public void changeLocation(Observable o) {
        Q q = find(o);
        if (q == null) {
            setCursor(Cursor.DEFAULT);
        } else {
          for (Machine m:  ((SchemaModel) o).getWork().getMachines()) {
              if (m.getId()==q.getIdQ()) {
                  m.setLocationX(0.5); m.setLocationY(0.5);
                  System.out.println(" m.setLocationX(0.5); m.setLocationY(0.5);");
                  repaint(schemaModel);
              }
          }

        }
    }


}
