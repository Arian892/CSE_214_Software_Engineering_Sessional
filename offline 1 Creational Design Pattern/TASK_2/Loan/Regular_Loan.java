package TASK_2.Loan;

public class Regular_Loan extends Loan {
    private double principal;
    private double interestRate = 0.14 ;

    public Regular_Loan(double principal) {
        this.principal = principal;
    }

    @Override
    public double calculateInterest(int timePeriod) {
        return principal * interestRate * timePeriod;
    }
    
}
