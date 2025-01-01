package TASK_1.Systems.InternetConnection;


public class Ethernet implements InternetConnection {
    @Override
    public void show() {
        System.out.println("Connecting to the internet using Ethernet.");
    }
}