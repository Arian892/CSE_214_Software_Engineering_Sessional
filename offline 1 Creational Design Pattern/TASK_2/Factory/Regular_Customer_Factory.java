package TASK_2.Factory;

import TASK_2.Accounts.Accounts;
import TASK_2.Accounts.Regular_Accounts;
import TASK_2.Loan.Loan;
import TASK_2.Loan.Regular_Loan;

public class Regular_Customer_Factory implements Bank_AbstructFactory {


   
    public Accounts createAccount(double principal)
    {
        return new Regular_Accounts(principal);

    }

    
    public Loan createLoan (double principal)
    {
        return new Regular_Loan(principal);
    }

    

}