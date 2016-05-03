package trestview.machinetest.module0;

import java.util.*;

/**
 * Created by Роман on 03.05.2016.
 */
public class Module0Model extends Observable {

    private Deque<Double> randomVariablesList;

    private Timer timer;

    public Module0Model() {
        this.randomVariablesList = new ArrayDeque<>();
//        this.groupedStatisticalSeries = new Hashtable<>();
        populateList(100);
    }

    public Deque<Double> getRandomVariablesList() {
        return randomVariablesList;
    }



    public void setRandomValuesList(Deque<Double> randomVariablesList) {
        this.randomVariablesList = randomVariablesList;

    }

    //populate the List with a test data
    private void populateList(int limit) {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                randomValuesList.clear();
                System.out.println("Timer is working");
                double value = Math.random()*100;
                System.out.println(value+"\\(^-^)/");
                randomVariablesList.addLast(value);
//                createGroupedStatisticalSeries();
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
