package Entities;

import Enums.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Expense {

    private String expenseID;
    private ExpenseType expenseType;
    private BigDecimal totalAmount;
    private String expenseName;
    private String paidBy; // of type String userID
    private String createdBy; // of type String userID
    private int totalUsers;

    @Builder.Default
    private HashMap<String,BigDecimal> userAmountMap = new HashMap<>(); // userID -> amount

}
