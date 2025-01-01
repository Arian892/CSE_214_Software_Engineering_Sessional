package TASK_2.Factory;

import TASK_2.Accounts.Accounts;
import TASK_2.Loan.Loan;

public interface Bank_AbstructFactory {
     Accounts createAccount(double principal);
     Loan createLoan(double principal);
    
}
