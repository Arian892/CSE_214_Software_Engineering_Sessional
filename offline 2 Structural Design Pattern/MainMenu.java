import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    List<MenuItems> MenuItemsList;

    public MenuItems search(String itemName) {
        for (MenuItems item : MenuItemsList) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void showmenu() {
        System.out.println("Menu:");
        System.out.println();
        for (MenuItems item : MenuItemsList) {
            // System.out.print(+i + ". ");
            // System.out.println(item.name + " - " + item.price + "tk ");
            item.show();
            System.out.println("  -  " + item.price + "tk ");
            
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.MenuItemsList = new ArrayList<>();

        MenuItems item1 = new Items("Burger", 300);
        MenuItems item4 = new Items("Fries", 100);
        MenuItems item2 = new Items("Wedges", 150);
        MenuItems item3 = new Items("Shawrma", 200);
        MenuItems item5 = new Items("Drink", 25);

        mainMenu.MenuItemsList.add(item1);
        mainMenu.MenuItemsList.add(item2);
        mainMenu.MenuItemsList.add(item3);
        mainMenu.MenuItemsList.add(item4);
        mainMenu.MenuItemsList.add(item5);

        Scanner sc = new Scanner(System.in);
        int choice;
        boolean exit = false;

        while (!exit) {

            System.out.println("1.Create Combo");
            System.out.println("2.Show Menu");
            System.out.println("0.Exit");
            System.out.println();

            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            if (choice == 2) {
                mainMenu.showmenu();
            }
            if (choice == 0) {
                exit = true;
            }
            if (choice == 1) {
                String comboName;
                System.out.println("Enter the name of the combo: ");

                comboName = sc.nextLine();
                Combo combo = new Combo(comboName);

                while (true) {
                    String command;
                    System.out.println();
                    System.out.println("Available Commands:");
                    System.out.println("Add[item]");
                    System.out.println("Remove[item]");
                    System.out.println("Free[item]");
                    System.out.println("Discount[percentage]");
                    System.out.println("Done");
                    System.out.println();

                    command = sc.nextLine();
                    String name;

                    if (command.equalsIgnoreCase("done")) {
                        mainMenu.MenuItemsList.add(combo);

                        
                        combo.showComboDetails();
                        System.out.println();
                        break;
                    } else if (command.toLowerCase().startsWith("add")) {

                        name = command.substring(4);
                        MenuItems item = mainMenu.search(name);
                        if (item != null) {
                            combo.addItems(item);
                        } else {
                            System.out.println("Item not found");
                        }

                    } else if (command.toLowerCase().startsWith("remove")) {

                        name = command.substring(7);

                        MenuItems item = mainMenu.search(name);
                        if (item != null) {
                            combo.removeItems(item);
                        } else {
                            System.out.println("Item not found");
                        }

                    } else if (command.toLowerCase().startsWith("free")) {

                        name = command.substring(5);
                        MenuItems item = mainMenu.search(name);

                        if (item != null) {
                            combo.addFreeItems(item);
                        } else {
                            System.out.println("Item not found");
                        }

                    } else if (command.toLowerCase().startsWith("discount")) {
                        double discount;
                        discount = Double.parseDouble(command.substring(9));
                        // System.out.println(discount);
                        // sc.nextLine();
                        combo.setDiscount(discount);

                    }
                }

            }

        }

    }
}
