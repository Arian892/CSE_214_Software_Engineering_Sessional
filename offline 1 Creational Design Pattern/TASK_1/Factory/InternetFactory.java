package TASK_1.Factory;

import TASK_1.Systems.InternetConnection.Ethernet;
import TASK_1.Systems.InternetConnection.GSM;
import TASK_1.Systems.InternetConnection.InternetConnection;
import TASK_1.Systems.InternetConnection.Wifi;

public class InternetFactory {

    public InternetConnection getInternetConnection(String choice)
    {
        if (choice.equalsIgnoreCase("WIFI"))
        return new Wifi();
        else if (choice.equalsIgnoreCase("GSM"))
        return new GSM();
        else 
        return new Ethernet();

    }
    
}
