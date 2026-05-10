package Services.Impl;

import Entities.Card;
import Entities.Transaction;
import Enums.Category;
import Exceptions.GenericException;
import Services.TransactionService;
import Services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    public HashMap<String, Transaction> transactionHashMap = new HashMap<>();
    private UserService userService;

    public TransactionServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public void addTransaction(String userID, double amount, Category category) {
        User user = userService.getUser(userID);
        if (user == null) throw new GenericException("User not found");

        List<Card> cards = user.getCardList();
        if (cards == null || cards.isEmpty()) throw new GenericException("No cards found");

        boolean charged = false;
        for (Card card : cards) {
            if (card.getCreditLimit() >= amount) {
                card.setCreditLimit(card.getCreditLimit() - amount);
                charged = true;
                break;
            }
        }
        if (!charged) throw new GenericException("Insufficient credit limit");

        long coinsEarned = CoinCalculator.calculate(amount, category);
        user.setCoinsWallet(user.getCoinsWallet() + coinsEarned);
        user.setTotalCoinsEarned(user.getTotalCoinsEarned() + coinsEarned);  // leaderboard

        Transaction txn = Transaction.builder()
                .transactionID(UUID.randomUUID().toString())
                .userID(userID)
                .amount(amount)
                .category(category)
                .type(TxnType.SPEND)
                .coinsImpact(coinsEarned)
                .timestamp(LocalDateTime.now())
                .build();

        transactionMap.put(txn.getTransactionID(), txn);
        user.getTransactionHistory().add(txn);

        System.out.printf("Transaction successful. +%d coins earned.%n", coinsEarned);
    }

    private static final Map<String, long[]> REDEMPTION_RULES = Map.of(
            // key → [minCoins, coinValueNumerator(paise), denominator]
            "FLIGHT_BOOKING",    new long[]{5000, 25},  // 1 coin = ₹0.25
            "HOTEL_BOOKING",     new long[]{2000, 20},
            "AIRPORT_PRIVILEGE", new long[]{1000, 20}
    );

    @Override
    public void redeem(String userID, String redeemType, long coins) {
        User user = userService.getUser(userID);
        if (user == null) throw new GenericException("User not found");

        long[] rules = REDEMPTION_RULES.get(redeemType);
        if (rules == null) throw new GenericException("Invalid redemption type");

        if (coins < rules[0])
            throw new GenericException("Minimum " + rules[0] + " coins required");
        if (user.getCoinsWallet() < coins)
            throw new GenericException("Insufficient coins");

        user.setCoinsWallet(user.getCoinsWallet() - coins);
        double value = coins * (rules[1] / 100.0);

        Transaction txn = Transaction.builder()
                .transactionID(UUID.randomUUID().toString())
                .userID(userID)
                .type(TxnType.REDEMPTION)
                .coinsImpact(-coins)
                .timestamp(LocalDateTime.now())
                .build();

        transactionMap.put(txn.getTransactionID(), txn);
        user.getTransactionHistory().add(txn);

        System.out.printf("Redeemed %d coins for ₹%.2f via %s%n", coins, value, redeemType);
    }
}
