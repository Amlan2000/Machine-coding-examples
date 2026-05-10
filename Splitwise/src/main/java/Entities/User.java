package Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

    private String userID;
    private String userName;
    private String emailID;
    private String phoneNo;
//    private long dueBalance;

    @Builder.Default
    private List<UserExpense> expenseList=new ArrayList<>();
}
