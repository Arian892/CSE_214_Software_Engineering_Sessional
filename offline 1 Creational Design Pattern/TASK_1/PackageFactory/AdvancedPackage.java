package TASK_1.PackageFactory;

import TASK_1.Systems.Microcontroller.*;
import TASK_1.Systems.PaymentTerminal.OnSpotPayment;
import TASK_1.Systems.PaymentTerminal.PaymentTerminal;
import TASK_1.Systems.Storage.RaspberryPiStorage;

import TASK_1.Systems.Storage.Storage;
import TASK_1.Systems.TicketingSystem.NFC;

import TASK_1.Systems.TicketingSystem.TicketingSystem;
import TASK_1.Systems.Controller.Controller;

import TASK_1.Systems.Controller.TouchScreenController;
import TASK_1.Systems.Display.*;


public class AdvancedPackage extends PackageFactory{

    @Override
    public Microcontroller getMicrocontroller(){
        
        return new RaspberryPi();
    }
    
    @Override
    public Display getDisplay()
    {
        return new OLED();

    }

     @Override
    public TicketingSystem getTicketingSystems() {
        return new NFC();
    }

    @Override
    public PaymentTerminal getPaymentTerminal() {
        return new OnSpotPayment();
    }

    

    @Override
    public Storage getStorage() {
        return new RaspberryPiStorage();
    }

    @Override
    public Controller getController() {
        return new TouchScreenController();
    }

    
}