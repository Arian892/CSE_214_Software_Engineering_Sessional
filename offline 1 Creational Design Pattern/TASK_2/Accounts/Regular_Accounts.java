package TASK_2.Accounts;

public class Regular_Accounts extends Accounts{
    
    private double principal ;
    private double interestRate = 0.025 ;

    public Regular_Accounts(double principal){
        this.principal= principal;
    }

    @Override
    public double calculateInterest(int timePeriod){
        return principal * interestRate * timePeriod ;

    }
}
