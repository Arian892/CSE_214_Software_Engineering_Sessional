package TASK_1.Builder;

import TASK_1.Systems.Systems;
import TASK_1.Systems.InternetConnection.InternetConnection;
import TASK_1.Systems.WebServer.WebServer;

import TASK_1.PackageFactory.*;


public interface iTicketingSystemBuilder {
    void setPackage (PackageFactory packageFactory);
    void setInternet(InternetConnection internetConnection);
    void setWebServer(WebServer webServer);
    
    Systems build ();

}
