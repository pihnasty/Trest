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



    private DoubleBinding kScale;
    private DoubleBinding hImv;
    private DoubleBinding wImv;

    public SchemaView(SchemaModel schemaModel, SchemaController schemaController) {

    this.work = schemaModel.getWork();

    this.imageview.setImage(new javafx.scene.image.Image("file:"+work.getScheme() ));

        setStyle("-fx-background-color: #336699;");



/*
        this.setHeight(200);
        this.setWidth(200);

        //GraphicsContext gc = getGraphicsContext2D();

        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
*/
        Rectangle r = new Rectangle(100,100,100,100);
        Line l = new Line(12,12,120,120);





        getChildren().addAll( imageview, r,l);





        System.out.println("getHeight()"+getMinHeight()+"   "+imageview.getImage().getHeight()+"   ------------"+imageview.getImage().getWidth());


        kScale = new DoubleBinding() {
            { super.bind(heightProperty()); }
            @Override
            protected double computeValue() {
                System.out.println(getHeight()/imageview.getImage().getHeight());
                return getHeight()/imageview.getImage().getHeight() ;
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



        imageview.fitHeightProperty().bind(hImv);
        imageview.fitWidthProperty().bind(wImv);


     //   imageview.setFitHeight( imageview.getImage().getHeight()*db.getValue());

    }

    @Override
    public void update(Observable o, Object arg) {
        work = (Work) ((SchemaModel) o).getWork();
        imageview.setImage(new javafx.scene.image.Image("file:"+work.getScheme() ));
        System.out.println("getHeight()"+getHeight());
        setHeight(getHeight()+1);        setHeight(getHeight()-1);




    }

    double scaleHeight() {
        return (getHeight()/getWidth() < imageview.getFitHeight() / imageview.getFitWidth()) ? getHeight()/imageview.getFitHeight() : getWidth()/imageview.getFitWidth();
    }




}
