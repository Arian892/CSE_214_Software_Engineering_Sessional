package TASK_1.Systems;

import TASK_1.Systems.Controller.Controller;
import TASK_1.Systems.Display.Display;
import TASK_1.Systems.InternetConnection.InternetConnection;
import TASK_1.Systems.Microcontroller.Microcontroller;
import TASK_1.Systems.PaymentTerminal.PaymentTerminal;
import TASK_1.Systems.Storage.Storage;
import TASK_1.Systems.TicketingSystem.TicketingSystem;
import TASK_1.Systems.WebServer.WebServer;

public class Systems {

    private Microcontroller microcontroller;
    private Display display;
    private TicketingSystem ticketingSystems;
    private PaymentTerminal paymentTerminal;
    private InternetConnection internetConnection;
    private Storage storage;
    private Controller controller;
    private WebServer webServer;

    // Constructor
    public Systems(Microcontroller microcontroller, Display display,  
                           TicketingSystem ticketingSystems, PaymentTerminal paymentTerminal, 
                           InternetConnection internetConnection, Storage storage,
                           Controller controller, WebServer webServer) {
        this.microcontroller = microcontroller;
        this.display = display;
        this.ticketingSystems = ticketingSystems;
        this.paymentTerminal = paymentTerminal;
        this.internetConnection = internetConnection;
        this.storage = storage;
        this.controller = controller;
        this.webServer = webServer;
    }

    // Show function to display all Systems components
    public void show() {
        System.out.println("Ticketing Systems Components:");
       
        microcontroller.show();
        display.show();
        ticketingSystems.show();
        paymentTerminal.show();
        internetConnection.show();
        storage.show();
        controller.show();
        webServer.show();
    }

    // // Setters in case the components need to be set or changed
    // public void setMicrocontroller(Microcontroller microcontroller) {
    //     this.microcontroller = microcontroller;
    // }

    // public void setDisplay(Display display) {
    //     this.display = display;
    // }

    // public void setTicketingSystems(TicketingSystem ticketingSystems) {
    //     this.ticketingSystems = ticketingSystems;
    // }

    // public void setPaymentTerminal(PaymentTerminal paymentTerminal) {
    //     this.paymentTerminal = paymentTerminal;
    // }

    // public void setInternetConnection(InternetConnection internetConnection) {
    //     this.internetConnection = internetConnection;
    // }

    // public void setStorage(Storage storage) {
    //     this.storage = storage;
    // }

    // public void setController(Controller controller) {
    //     this.controller = controller;
    // }

    // public void setWebServer(WebServer webServer) {
    //     this.webServer = webServer;
    // }
}
