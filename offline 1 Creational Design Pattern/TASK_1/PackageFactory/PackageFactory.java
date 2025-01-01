package TASK_1.PackageFactory;
import TASK_1.Systems.Microcontroller.*;
import TASK_1.Systems.PaymentTerminal.PaymentTerminal;
import TASK_1.Systems.Storage.Storage;
import TASK_1.Systems.TicketingSystem.*;
import TASK_1.Systems.Controller.Controller;
import TASK_1.Systems.Display.*;


public abstract class PackageFactory {



    // public PackageFactory getPackage(String packageType) {
    //     if (packageType.equalsIgnoreCase("Basic")) {
    //         return new BasicPackage();
    //     } else if (packageType.equalsIgnoreCase("Standard")) {
    //         return new StandardPackage();
    //     } else if (packageType.equalsIgnoreCase("Advanced")) {
    //         return new AdvancedPackage();
    //     } else if (packageType.equalsIgnoreCase("Premium")) {
    //         return new PremiumPackage();
    //     } else {
    //         return null; // Invalid package type
    //     }
    // }





    

    public abstract Microcontroller getMicrocontroller();

    public abstract Display
    getDisplay ();

    public abstract PaymentTerminal
    getPaymentTerminal();

    public abstract TicketingSystem 
    getTicketingSystems();

    public abstract Storage
    getStorage();

    public abstract Controller
    getController();

}
