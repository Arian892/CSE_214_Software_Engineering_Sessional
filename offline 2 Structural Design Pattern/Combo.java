import java.util.ArrayList;
import java.util.List;

public class Combo extends MenuItems {

    List<MenuItems> MenuItemsList;
    List<MenuItems> FreeItemsList;
    private double discount;

    public Combo(String comboName) {
        this.name = comboName;
        this.price = 0;
        MenuItemsList = new ArrayList<>();
        FreeItemsList = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public double getDiscountedTotal() {
        price =  price - (price * discount / 100);
        return price ;
    }


    public void addItems(MenuItems item) {
        MenuItemsList.add(item);
        price += item.price;

    }

    public void addFreeItems(MenuItems item) {
        FreeItemsList.add(item);
        
    }

    public void removeItems(MenuItems item) {
        MenuItemsList.remove(item);
        price -= item.price;
    }

    public void setDiscount(double percentage) {
        discount = percentage;
        // price = price - (price * percentage / 100);
    }

    public void show() {
        System.out.print(name);
        System.out.print(" (");
        int i = 0;

        for (MenuItems item : MenuItemsList) {

            item.show();
            i++;
            if (i < MenuItemsList.size())
                System.out.print(" + ");

        }

        // System.out.println(FreeItemsList.size());

        for (MenuItems freeItem : FreeItemsList) {
            System.out.print(" + ");

            System.out.print(freeItem.getName() + " (Free)");

        }
        System.out.print(" )");
    }
    public void showComboDetails() {
        System.out.println(name);
        for (MenuItems item : MenuItemsList) {
            System.out.print("- ");
            item.show();
            System.out.println();
        }
        for (MenuItems freeItem : FreeItemsList) {
            System.out.println("- " + freeItem.getName() + " (Free!!!)");
        }
        System.out.println("Total - " + price + "tk");
        System.out.println("Discount - " + discount + "%");
        System.out.println("Discounted total - " + getDiscountedTotal() + "tk");
    }
}