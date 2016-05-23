package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import entityProduction.Modelmachine;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Q extends BorderPane{
    private Modelmachine modelmachine;
    private Machine machine;
    private ImageView imvQ = new ImageView();
    private int idQ;


    private Double  x;
    private Double  y;
    private Double  oX;
    private Double  oY;

    private Double  angle;

    private double scaleEquipment =1.0;
    private Rectangle rInner;
    private double scale;

    public Q(Machine machine) {
        this.machine = machine;
        this.idQ = machine.getId();
        ImageView imvWork = new ImageView();
        imvWork.setImage(new javafx.scene.image.Image("file:"+this.machine.getWork().getScheme()));
        scale = imvWork.getImage().getHeight()/ this.machine.getWork().getOverallSize();
        this.modelmachine = machine.getModelmachine();
        oX = modelmachine.getWorkSizeX()*scale/2.0;
        oY = modelmachine.getWorkSizeY()*scale/2.0;
        angle = this.machine.getAngle();

        Rectangle rInner = new Rectangle(oX-modelmachine.getOverallDimensionX()*scale/2.0,oY-modelmachine.getOverallDimensionY()*scale/2.0,modelmachine.getOverallDimensionX()*scale,modelmachine.getOverallDimensionY()*scale);
        Rectangle rOuter = new Rectangle(0,0,2*oX,2*oY);

        //region: Affine coordinate transformation
        this.imvQ.setImage(new javafx.scene.image.Image("file:"+machine.getModelmachine().getImg() ));
        this.imvQ.fitWidthProperty().setValue(modelmachine.getOverallDimensionX()*scale);
        this.imvQ.fitHeightProperty().setValue(imvQ.getImage().getHeight()/imvQ.getImage().getWidth()*imvQ.getFitWidth());
        this.imvQ.setLayoutX(rInner.getX());
        this.imvQ.setLayoutY(rInner.getY());
        //endregion

        this.x=machine.getLocationX()*imvWork.getImage().getWidth()  - (oX*Math.cos(angle/(2.0*Math.PI))-oY*Math.sin(angle/(2.0*Math.PI)));
        this.y=machine.getLocationY()*imvWork.getImage().getHeight() - (oX*Math.sin(angle/(2.0*Math.PI))+oY*Math.cos(angle/(2.0*Math.PI)));
        this.scaleEquipment = machine.getWork().getScaleEquipment();




      //  this.imageview.setImage(new javafx.scene.image.Image("file:"+work.getScheme() ));
        //this.scale = machine.getWork().


       // rInner.setFill(null);

        rInner.setFill(Color.TRANSPARENT);        rInner.setStrokeWidth(5);        rInner.setStroke(Color.RED);
        rOuter.setFill(Color.TRANSPARENT);        rOuter.setStrokeWidth(5);        rOuter.setStroke(Color.BLUE);

        getChildren().addAll(imvQ,rInner,rOuter);
 //     rInner.setLayoutX(0);   rInner.setLayoutY(0);
    //    setCursor(Cursor.HAND);

    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Double getoX() {
        return oX;
    }

    public void setoX(Double oX) {
        this.oX = oX;
    }

    public Double getoY() {
        return oY;
    }

    public void setoY(Double oY) {
        this.oY = oY;
    }


}
