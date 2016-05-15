package trestview.resourcelink.schemawork;

import entityProduction.Machine;
import entityProduction.Modelmachine;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Q extends BorderPane{
    private Modelmachine modelmachine;
    private Machine machine;
    private ImageView imv = new ImageView();
    private int idQ;



    private DoubleBinding x;
    private DoubleBinding y;
    private double scaleEquipment =1.0;
    private Rectangle rInner;
    private double scale;

    public Q(Machine machine) {
        this.machine = machine;

        ImageView imvWork = new ImageView();
        imvWork.setImage(new javafx.scene.image.Image("file:"+this.machine.getWork().getScheme()));
        scale = imvWork.getImage().getHeight()/ this.machine.getWork().getOverallSize();


        this.modelmachine = machine.getModelmachine();
        this.idQ = machine.getId();
        this.imv.setImage(new javafx.scene.image.Image("file:"+machine.getModelmachine().getImg() ));

        this.imv.fitWidthProperty().setValue(modelmachine.getOverallDimensionX()*scale);
//       this.imv.fitHeightProperty().setValue(400);


    //    this.x=machine.getLocationX()*imvWork.getImage().getWidth();


        x = new DoubleBinding() {
            { super.bind(imvWork.getImage().widthProperty()); }
            @Override
            protected double computeValue() {
                return imvWork.getImage().widthProperty().getValue()*machine.getLocationX();
            }};

        y = new DoubleBinding() {
            { super.bind(imvWork.getImage().heightProperty()); }
            @Override
            protected double computeValue() {
                return imvWork.getImage().heightProperty().getValue()*machine.getLocationY();
            }};


   //     this.y=machine.getLocationY()*imvWork.getImage().getHeight();






        this.scaleEquipment = machine.getWork().getScaleEquipment();




      //  this.imageview.setImage(new javafx.scene.image.Image("file:"+work.getScheme() ));
        //this.scale = machine.getWork().


        Rectangle rInner = new Rectangle(0,0,modelmachine.getOverallDimensionX()*scale,modelmachine.getOverallDimensionY()*scale);
        Rectangle rOuter = new Rectangle(0,0,modelmachine.getWorkSizeX()*scale,modelmachine.getWorkSizeY()*scale);


       // rInner.setFill(null);

        rInner.setFill(Color.TRANSPARENT);        rInner.setStrokeWidth(5);        rInner.setStroke(Color.RED);
        rOuter.setFill(Color.TRANSPARENT);        rOuter.setStrokeWidth(5);        rOuter.setStroke(Color.BLUE);

        getChildren().addAll(imv,rInner,rOuter);
        rInner.setLayoutX(0);   rInner.setLayoutY(0);

    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public Number getY() {
        return y.get();
    }

    public DoubleBinding yProperty() {
        return y;
    }

    public Number getX() {
        return x.get();
    }

    public DoubleBinding xProperty() {
        return x;
    }



}
