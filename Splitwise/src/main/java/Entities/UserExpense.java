package Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class UserExpense {

    private String expenseID;
    private BigDecimal amountPaid;
    private BigDecimal amountDue;
}
