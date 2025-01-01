package TASK_2.Accounts;

public class Vip_Accounts extends Accounts {
    private double principal ;
    private double interestRate = 0.05 ;

    public Vip_Accounts (double principal){
        this.principal = principal ;
    }


    @Override
    public double calculateInterest(int timePeriod){
        return principal * interestRate * timePeriod ;

    }
    
}
