package TASK_1.Systems.PaymentTerminal;

public class OnSpotPayment implements PaymentTerminal {
    @Override
    public void show() {
        System.out.println("Processing on-spot payment and providing change.");
    }
}