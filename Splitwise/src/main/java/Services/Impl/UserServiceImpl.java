package Services.Impl;

import Entities.User;
import Entities.UserExpense;
import Exceptions.GenericException;
import Services.UserService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {

    public HashMap<String, User> userHashMap =  new HashMap<>();

    @Override
    public void addUser(String userID, String name, String emailID, String phone){

        if(!userHashMap.isEmpty() && userHashMap.containsKey(userID)){
            throw new GenericException("User is already added.\n");
        }

        User user = User.builder().userID(userID).userName(name).emailID(emailID).phoneNo(phone).build();
        userHashMap.put(userID,user);

        System.out.println("User "+ user.getUserName()+" is successfully added. \n");
    }

    @Override
    public User getUser(String userID){
        return userHashMap.get(userID);
    }

    @Override
    public void findUsersWithPositiveDueBalance(){

        userHashMap.forEach((key,value)->{
            List<UserExpense> expenseList = value.getExpenseList();

            BigDecimal dueBalance = BigDecimal.valueOf(0);

            for(UserExpense userExpense:expenseList){
                dueBalance = dueBalance.add(userExpense.getAmountDue());
            }

            if(dueBalance.compareTo(BigDecimal.ZERO)>0){
                System.out.println("User: "+value.getUserName()+" has positive due Balance.\n");
            }

        });

    }

    @Override
    public void findUserExpenses(String userID){
        List<UserExpense> userExpenseList = userHashMap.get(userID).getExpenseList();

        for(UserExpense userExpense: userExpenseList){
            System.out.println("User Expense ID: "+userExpense.getExpenseID()+" Amount paid: "+ userExpense.getAmountPaid()+ " Amount Due: "+ userExpense.getAmountDue() +"\n");
        }

    }




}
