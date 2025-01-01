package TASK_1.Systems.TicketingSystem;

public class RFID implements TicketingSystem {
    @Override
    public void show() {
        System.out.println("Passenger identified using RFID card.");
    }
}