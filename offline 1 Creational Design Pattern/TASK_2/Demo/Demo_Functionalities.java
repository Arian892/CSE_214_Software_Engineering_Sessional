package TASK_2.Demo;
import TASK_2.Loan.*;
import TASK_2.Accounts.*;

public class Demo_Functionalities {
    public static void main(String[] args) {
        //  Create accounts for different customer types
        Accounts regularAccount = new Regular_Accounts(10000);  // Principal = 10000
        Accounts premiumAccount = new Premium_Accounts(10000);
        Accounts vipAccount = new Vip_Accounts(10000);

        // Calculate interest for 5 years
        System.out.println("Regular Account Interest for 5 years: " + regularAccount.calculateInterest(5));
        System.out.println("Premium Account Interest for 5 years: " + premiumAccount.calculateInterest(5));
        System.out.println("VIP Account Interest for 5 years: " + vipAccount.calculateInterest(5));

        // Create loans for different customer types
        Loan regularLoan = new Regular_Loan(5000);  // Principal = 5000
        Loan premiumLoan = new Premium_Loan(5000);
        Loan vipLoan = new Vip_Loan(5000);

        // Calculate loan interest for 3 years
        System.out.println("Regular Loan Interest for 3 years: " + regularLoan.calculateInterest(3));
        System.out.println("Premium Loan Interest for 3 years: " + premiumLoan.calculateInterest(3));
        System.out.println("VIP Loan Interest for 3 years: " + vipLoan.calculateInterest(3));
        
    }
    
}
