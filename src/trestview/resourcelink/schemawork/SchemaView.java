package trestview.resourcelink.schemawork;

import entityProduction.Work;
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



     //  setHeight(imageview.getImage().getHeight());       // setMaxHeight(100);
      //  setWidth   (1200);


        getChildren().addAll( imageview, r,l);


        imageview.setFitHeight(getHeight()*scaleHeight());
        imageview.setFitWidth( getWidth()*scaleHeight());

        System.out.println("getHeight()"+getMinHeight()+"   "+imageview.getImage().getHeight()+"   ------------"+imageview.getImage().getWidth());

        imageview.fitHeightProperty().bind(heightProperty());

    }

    @Override
    public void update(Observable o, Object arg) {
        work = (Work) ((SchemaModel) o).getWork();
        imageview.setImage(new javafx.scene.image.Image("file:"+work.getScheme() ));
        System.out.println("getHeight()"+getHeight());
    }

    double scaleHeight() {
        return (getHeight()/getWidth() < imageview.getFitHeight() / imageview.getFitWidth()) ? getHeight()/imageview.getFitHeight() : getWidth()/imageview.getFitWidth();
    }


}
