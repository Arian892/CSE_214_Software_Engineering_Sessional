package TASK_2.Accounts;

public class Premium_Accounts extends Accounts {
    
    private double principal ;
    private double interestRate = 0.035 ;

    public Premium_Accounts(double principal){
        this.principal = principal ;
    }

    @Override
    public double calculateInterest(int timePeriod){
        return principal * interestRate * timePeriod ;

    }
    

    
}
