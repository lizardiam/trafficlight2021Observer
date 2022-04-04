package trafficlight.states;
import trafficlight.gui.Observer;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObserver(State state) {
        for (Observer o : observers){
            o.update(state);
        }
    }

}
