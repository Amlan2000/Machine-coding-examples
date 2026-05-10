package Entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userID;
    private String userName;
    private Double funds;
}


// TODO: Keep track of user->funds