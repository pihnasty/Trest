package trestview.machinetest;

import trestview.menu.TMenuModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Roman Korsun on 22.03.2016.
 */
public class MachineTestModel extends Observable {

    private TMenuModel menuModel;
    private ArrayList<Double> randomValuesList;
//    private Class tClass;



    public MachineTestModel() {
        this.randomValuesList = new ArrayList<Double>();
    }
    public MachineTestModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        this.randomValuesList = new ArrayList<Double>();
        populateList(100);
//        this.tClass = tClass;
    }

    public TMenuModel getTMenuModel() {
        return menuModel;
    }

    public void setTMenuModel(TMenuModel menuModel) {
        this.menuModel = menuModel;
        changed();
    }

    public ArrayList<Double> getRandomValuesList() {
        return randomValuesList;
    }

    public void setRandomValuesList(ArrayList<Double> randomValuesList) {
        this.randomValuesList = randomValuesList;
    }


    //adds a value to the List
    public void addValue(double value) {
        this.randomValuesList.add(value);
    }

    //removes value from List
    public void removeValue(int index) {
        this.randomValuesList.remove(index);
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
