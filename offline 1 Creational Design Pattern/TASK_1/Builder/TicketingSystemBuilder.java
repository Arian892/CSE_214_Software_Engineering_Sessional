package TASK_1.Builder;
import TASK_1.PackageFactory.PackageFactory;
import TASK_1.Systems.Systems;
import TASK_1.Systems.Controller.Controller;

import TASK_1.Systems.Display.Display;

import TASK_1.Systems.InternetConnection.InternetConnection;
import TASK_1.Systems.Microcontroller.Microcontroller;
import TASK_1.Systems.PaymentTerminal.PaymentTerminal;
import TASK_1.Systems.Storage.Storage;
import TASK_1.Systems.TicketingSystem.TicketingSystem;
import TASK_1.Systems.WebServer.WebServer;

public class TicketingSystemBuilder implements iTicketingSystemBuilder {
    private Microcontroller microcontroller;
    private Display display;
    private TicketingSystem ticketingSystem;
    private PaymentTerminal paymentTerminal;
    private InternetConnection internetConnection;
    private Storage storage;
    private Controller controller;
    private WebServer webServer;

    @Override
    public void setPackage(PackageFactory packageFactory) {
        this.microcontroller = packageFactory.getMicrocontroller();
        this.display = packageFactory.getDisplay();
        this.paymentTerminal = packageFactory.getPaymentTerminal();
        this.controller = packageFactory.getController();
        this.storage = packageFactory.getStorage();
        this.ticketingSystem = packageFactory.getTicketingSystems();

    }

    @Override
    public void setInternet(InternetConnection internetConnection) {
        this.internetConnection = internetConnection;

    }

    @Override
    public void setWebServer(WebServer webServer) {
        this.webServer = webServer;

    }

    @Override
    public Systems build() {
        return new Systems(microcontroller, display, ticketingSystem, paymentTerminal, internetConnection, storage,
                controller, webServer);

    }

}
