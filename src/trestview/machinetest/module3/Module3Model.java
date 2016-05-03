package trestview.machinetest.module3;

import trestview.machinetest.module0.Module0Model;

import java.util.*;

/**
 * Created by Роман on 15.04.2016.
 */
public class Module3Model extends Observable implements Observer{

    Module0Model module0Model;

    private Deque<Double> randomValuesList;
//    private Hashtable<Double, Double> groupedStatisticalSeries;//HashSet
//    private Timer timer;

    public Module3Model(Module0Model module0Model) {
        this.module0Model = module0Model;
        this.randomValuesList = new ArrayDeque<>();
//        this.groupedStatisticalSeries = new Hashtable<>();

    }

    public Deque<Double> getRandomValuesList() {
        return randomValuesList;
    }

//    public Hashtable<Double, Double> getGroupedStatisticalSeries() { return groupedStatisticalSeries; }

    public void setRandomValuesList(Deque<Double> randomValuesList) {
        this.randomValuesList = randomValuesList;
    }

    private void updateRandomValuesList() {
        double tau = module0Model.getRandomVariablesList().peekLast();//get tail or null if deque is empty
        randomValuesList.addLast(tau);
        changed();
    }

    //populate the List with a test data


//    private void createGroupedStatisticalSeries() {
//
//        float step = 10.0f;
//
//        for(double i = 0; i < 100; i+=step) {
//            int count = 0;
//            double sum = 0;
//            for(int j = 0; j < randomValuesList.size(); j++){
//                if(randomValuesList.get(j) >= i && randomValuesList.get(j) < i + step) {
//                    count++;
//                }
//
//
//            }
//            double val = count;
////            if(i == 0)
//                groupedStatisticalSeries.put(i, val);
//            System.out.println("keys::::i="+i+" --- "+ groupedStatisticalSeries.keys().toString());
////            else
////                groupedStatisticalSeries.replace(i, val);
//        }
//    }



    public void changed() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateRandomValuesList();
    }
}
