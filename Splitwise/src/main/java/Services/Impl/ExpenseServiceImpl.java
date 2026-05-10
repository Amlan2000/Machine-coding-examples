package Services.Impl;

import Entities.Expense;
import Entities.User;
import Entities.UserExpense;
import Enums.ExpenseType;
import Exceptions.GenericException;
import Services.ExpenseService;
import Services.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExpenseServiceImpl implements ExpenseService {

    public HashMap<String, Expense> expenseHashMap = new HashMap<>();

    private final UserService userService;

    public ExpenseServiceImpl(UserService userService){
        this.userService=userService;
    }

    @Override
    public void addExpense(String expenseID, String expenseName, BigDecimal amount, String paidBy, String createdBy, int totalUsers, ExpenseType expenseType, List<String> users, List<BigDecimal> amounts){

        if(!expenseHashMap.isEmpty() && expenseHashMap.containsKey(expenseID)){
            throw new GenericException("Expense is already added. \n");
        }


        HashMap<String,BigDecimal> userAmountMap = new HashMap<>();
        for(int i=0;i<users.size();i++){
            BigDecimal indiAmount= BigDecimal.valueOf(0);
            if(!amounts.isEmpty()){
                indiAmount= amounts.get(i);
            }
            userAmountMap.put(users.get(i), indiAmount);
        }
        HashMap<String,BigDecimal> individualExpenseMap = calculateIndividualExpenses(expenseID,amount,expenseType,userAmountMap,paidBy);


        Expense expense = Expense.builder().expenseID(expenseID).expenseName(expenseName).totalAmount(amount).paidBy(paidBy).createdBy(createdBy).totalUsers(userAmountMap.size()).expenseType(expenseType).userAmountMap(individualExpenseMap).build();

        expenseHashMap.put(expenseID,expense);

        System.out.println("Successfully added expense with id: "+ expenseID +"\n");

    }

    private HashMap<String,BigDecimal> calculateIndividualExpenses(String expenseID,BigDecimal amount,ExpenseType expenseType,HashMap<String,BigDecimal> amounts,String paidBy){

        HashMap<String,BigDecimal> tempExpenseMap = new HashMap<>();

        switch(expenseType) {
            case ExpenseType.EQUAL:
                amounts.forEach((key,value)->{
                    if(value.equals(BigDecimal.valueOf(0))){
                        value = amount.divide(BigDecimal.valueOf(amounts.size()),2,RoundingMode.HALF_UP);
                    }
                    tempExpenseMap.put(key,value);

                    calculateUserExpenseList(expenseID,amount,key,value,paidBy);

                });
                break;
            case ExpenseType.EXACT:
                tempExpenseMap.putAll(amounts);
                break;

            case ExpenseType.PERCENT:
                amounts.forEach((key,value)-> {
                    value = value.multiply(amount)
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                    tempExpenseMap.put(key,value);
                });
                break;
            default:
                throw new GenericException("Add expense type.\n");

        }

        return tempExpenseMap;
    }

    private void calculateUserExpenseList(String expenseID,BigDecimal amount,String key, BigDecimal value,String paidBy){
        User user = userService.getUser(key);

        BigDecimal amountPaid = BigDecimal.ZERO,amountDue;

        if(paidBy.equals(key)){
            amountPaid=amount;
            amountDue=amountPaid.subtract(value);
        }
        else{
            amountDue= value.negate();
        }

        UserExpense expense = UserExpense.builder().expenseID(expenseID).amountPaid(amountPaid).amountDue(amountDue).build();

        List<UserExpense> currentExpenseList = user.getExpenseList();
        currentExpenseList.add(expense);
        user.setExpenseList(currentExpenseList);
    }



}
