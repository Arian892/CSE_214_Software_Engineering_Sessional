package TASK_1.Factory;

import TASK_1.Systems.WebServer.Django;
import TASK_1.Systems.WebServer.NodeJS;
import TASK_1.Systems.WebServer.Ruby;
import TASK_1.Systems.WebServer.WebServer;

public class WebServerFactory {
    public WebServer getWebServer (String choice){
        if (choice.equalsIgnoreCase("django"))
        return new Django();
        else if (choice.equalsIgnoreCase("nodejs"))
        return new NodeJS();
        else 
        return new Ruby();
    }
    
}
