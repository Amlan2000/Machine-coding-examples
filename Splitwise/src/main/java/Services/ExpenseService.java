package Services;

import Entities.User;
import Enums.ExpenseType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


public interface ExpenseService {

    void addExpense(String expenseID, String expenseName, BigDecimal amount, String paidBy, String createdBy, int totalUsers, ExpenseType expenseType, List<String> users, List<BigDecimal> amounts);
}
