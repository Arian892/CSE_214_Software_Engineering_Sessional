package TASK_1.PackageFactory;

import TASK_1.Systems.Microcontroller.*;
import TASK_1.Systems.PaymentTerminal.OnSpotPayment;
import TASK_1.Systems.PaymentTerminal.PaymentTerminal;
import TASK_1.Systems.Storage.SDCardStorage;
import TASK_1.Systems.Storage.Storage;
import TASK_1.Systems.TicketingSystem.RFID;
import TASK_1.Systems.TicketingSystem.TicketingSystem;
import TASK_1.Systems.Controller.Controller;
import TASK_1.Systems.Controller.ExternalController;
import TASK_1.Systems.Display.*;

public class StandardPackage extends PackageFactory{

    @Override
    public Microcontroller getMicrocontroller(){
        
        return new ArduinoMega();
    }
    
    @Override
    public Display getDisplay()
    {
        return new LED();

    }

     @Override
    public TicketingSystem getTicketingSystems() {
        return new RFID();
    }

    @Override
    public PaymentTerminal getPaymentTerminal() {
        return new OnSpotPayment();
    }

    

    @Override
    public Storage getStorage() {
        return new SDCardStorage();
    }

    @Override
    public Controller getController() {
        return new ExternalController();
    }

    
}