package TASK_1;

import TASK_1.Builder.TicketingSystemBuilder;
import TASK_1.Factory.InternetFactory;
import TASK_1.Factory.WebServerFactory;
import TASK_1.PackageFactory.AdvancedPackage;
import TASK_1.PackageFactory.BasicPackage;
import TASK_1.PackageFactory.PackageFactory;
import TASK_1.PackageFactory.PremiumPackage;
import TASK_1.PackageFactory.StandardPackage;
import TASK_1.Systems.Systems;
import TASK_1.Systems.InternetConnection.InternetConnection;
import TASK_1.Systems.WebServer.WebServer;
import java.util.Scanner;

public class TicketingSystemApp {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        System.out.println("Select a package (Basic, Standard, Advanced, Premium):");
        String packageChoice = scanner.nextLine().trim();

        PackageFactory selectedPackage = null;

        if (packageChoice.equalsIgnoreCase("Basic")) {
            selectedPackage = new BasicPackage();
        } else if (packageChoice.equalsIgnoreCase("Standard")) {
            selectedPackage = new StandardPackage();
        } else if (packageChoice.equalsIgnoreCase("Advanced")) {
            selectedPackage = new AdvancedPackage();
        } else if (packageChoice.equalsIgnoreCase("Premium")) {
            selectedPackage = new PremiumPackage();
        } else {
            System.out.println("Invalid package type. Exiting...");
            scanner.close();
            return;
        }

       
        String internetChoice = "";
        if (packageChoice.equalsIgnoreCase("Basic") || packageChoice.equalsIgnoreCase("Standard")) {
            // Only WiFi or GSM is allowed for Basic and Standard packages
            System.out.println("Select an Internet connection (WiFi, GSM):");
            internetChoice = scanner.nextLine().trim();

            // Check if user chooses Ethernet, which is not allowed
            while (!internetChoice.equalsIgnoreCase("WiFi") && !internetChoice.equalsIgnoreCase("GSM")) {
                System.out.println("Invalid choice! Please choose either WiFi or GSM:");
                internetChoice = scanner.nextLine().trim();
            }

        } else if (packageChoice.equalsIgnoreCase("Advanced") || packageChoice.equalsIgnoreCase("Premium")) {
            // All options available for Advanced and Premium packages
            System.out.println("Select an Internet connection (WiFi, GSM, Ethernet):");
            internetChoice = scanner.nextLine().trim();

            while (!internetChoice.equalsIgnoreCase("WiFi") && !internetChoice.equalsIgnoreCase("GSM")
                    && !internetChoice.equalsIgnoreCase("Ethernet")) {
                System.out.println("Invalid choice! Please choose either WiFi, GSM, or Ethernet:");
                internetChoice = scanner.nextLine().trim();
            }
        }

        System.out.println("Select a Web Server framework (Django, NodeJS, Ruby):");
        String webServerChoice = scanner.nextLine().trim();

        TicketingSystemBuilder builder = new TicketingSystemBuilder();

        builder.setPackage(selectedPackage);

        InternetFactory internetFactory = new InternetFactory();
        InternetConnection internetConnection = internetFactory.getInternetConnection(internetChoice);
        builder.setInternet(internetConnection);

        WebServerFactory webServerFactory = new WebServerFactory();
        WebServer webServer = webServerFactory.getWebServer(webServerChoice);
        builder.setWebServer(webServer);

        Systems ticketingSystem = builder.build();

        ticketingSystem.show();
  

        scanner.close();
        
    }
    
}
