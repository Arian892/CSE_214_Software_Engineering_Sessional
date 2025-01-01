package TASK_1.Systems.TicketingSystem;

public class NFC implements TicketingSystem {
    @Override
    public void show() {
        System.out.println("Passenger identified using NFC card.");
    }
}