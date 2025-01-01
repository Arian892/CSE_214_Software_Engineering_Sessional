package TASK_1.Systems.InternetConnection;

public class Wifi implements InternetConnection {
    @Override
    public void show() {
        System.out.println("Connecting to the internet using WiFi.");
    }
}