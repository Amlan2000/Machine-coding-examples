package Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    private String userID;
    private String userName;
    private long coinsWallet;
    private long totalCoinsEarned;   // for leaderboard — never decremented

    @Builder.Default
    private List<Card> cardList = new ArrayList<>();

    @Builder.Default
    private List<Transaction> transactionHistory = new ArrayList<>();
}