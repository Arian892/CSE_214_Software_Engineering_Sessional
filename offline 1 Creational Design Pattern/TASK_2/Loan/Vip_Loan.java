package TASK_2.Loan;

public class Vip_Loan extends Loan {
    private double principal;
    private double interestRate = 0.10 ;

    public Vip_Loan(double principal) {
        this.principal = principal;
    }

    @Override
    public double calculateInterest(int timePeriod) {
        return principal * interestRate * timePeriod;
    }
    
}
