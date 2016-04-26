package trestview.machinetest.module3;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Роман on 15.04.2016.
 */
public class Model3 extends Observable {

    private ArrayList<Double> randomValuesList;

    public Model3() {
        this.randomValuesList = new ArrayList<Double>();
        populateList(100);
    }

    public ArrayList<Double> getRandomValuesList() {
        return randomValuesList;
    }

    public void setRandomValuesList(ArrayList<Double> randomValuesList) {
        this.randomValuesList = randomValuesList;
    }

    //populate the List with a test data
    private void populateList(int limit) {
        for (double i = 0; i < limit; i++) {
            randomValuesList.add(Math.atan(i));
        }
    }

    public void changed() {
        setChanged();
        notifyObservers();
    }
}
