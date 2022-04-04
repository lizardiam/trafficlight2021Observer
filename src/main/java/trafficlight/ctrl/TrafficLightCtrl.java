package trafficlight.ctrl;
import trafficlight.gui.TrafficLight;
import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

//repository: https://github.com/lizardiam/trafficlight2021Observer

public class TrafficLightCtrl {

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    private static TrafficLightCtrl instance = new TrafficLightCtrl();

    public static TrafficLightCtrl getInstance(){
        if (instance == null){
            instance = new TrafficLightCtrl();
        }
        return instance;
    }

    public TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        currentState.notifyObserver(currentState);
    }

    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;

                yellowState.notifyObserver(yellowState);
                notifyObserver(this);

                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;

                yellowState.notifyObserver(yellowState);
                notifyObserver(this);

                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;

                    redState.notifyObserver(redState);
                    notifyObserver(this);

                    return redState;
                }else {
                    previousState = currentState;

                    greenState.notifyObserver(greenState);
                    notifyObserver(this);

                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public void stop() {
        doRun = false;
    }
}