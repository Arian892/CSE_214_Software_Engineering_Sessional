package TASK_2.Loan;

public class Premium_Loan extends Loan {
    private double principal;
    private double interestRate = 0.12 ;

    public Premium_Loan(double principal) {
        this.principal = principal;
    }

    @Override
    public double calculateInterest(int timePeriod) {
        return principal * interestRate * timePeriod;
    }
    
}
