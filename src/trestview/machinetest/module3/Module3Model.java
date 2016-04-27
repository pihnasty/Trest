package trestview.machinetest.module3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Роман on 15.04.2016.
 */
public class Module3Model extends Observable {

    private ArrayList<Double> randomValuesList;
    private Timer timer;

    public Module3Model() {
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

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                randomValuesList.clear();
                System.out.println("Timer is working");
                    randomValuesList.add((Math.random()*100));
                changed();
            }
        },3000, 4000);

//        for (double i = 0; i < limit; i++) {
//            randomValuesList.add(Math.atan(i));
//        }
    }



    public void changed() {
        setChanged();
        notifyObservers();
    }

}
