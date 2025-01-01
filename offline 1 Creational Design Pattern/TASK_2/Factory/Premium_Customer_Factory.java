package TASK_2.Factory;

import TASK_2.Accounts.Accounts;
import TASK_2.Loan.Loan;
import TASK_2.Loan.Premium_Loan;
import TASK_2.Accounts.Premium_Accounts;

public class Premium_Customer_Factory implements Bank_AbstructFactory {
    
    public Accounts createAccount(double principal)
    {
        return new Premium_Accounts(principal);

    }

    
    public Loan createLoan (double principal)
    {
        return new Premium_Loan(principal);
        
    }
}
