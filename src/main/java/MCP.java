import trafficlight.ctrl.TrafficLightCtrl;

//repository: https://github.com/lizardiam/trafficlight2021Observer

public class MCP {
    public static void main(String[] args) {
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        ctrl.run();
    }
}