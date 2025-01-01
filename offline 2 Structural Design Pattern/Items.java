public class Items extends MenuItems{
    

    public Items (String itemName, double itemPrice){
        this.name = itemName;
        this.price = itemPrice;
        

    }

    public double getPrice (){
        return price;
    }

    public String getName (){
        return name;
    }

    public void show() {
        System.out.print(name);
    }
}
