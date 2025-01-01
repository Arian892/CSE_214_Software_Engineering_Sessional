package TASK_2.Factory;

import TASK_2.Accounts.Accounts;
import TASK_2.Accounts.Vip_Accounts;
import TASK_2.Loan.Loan;
import TASK_2.Loan.Vip_Loan;

public class Vip_Customer_Factory implements
Bank_AbstructFactory {
    
    public Accounts createAccount(double principal)
    {
        return new Vip_Accounts(principal);

    }

    
    public Loan createLoan (double principal)
    {
        return new Vip_Loan(principal);
    }

    
}
