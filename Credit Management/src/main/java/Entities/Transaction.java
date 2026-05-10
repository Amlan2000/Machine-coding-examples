@Getter @Builder
public class Transaction {
    private final String transactionID;
    private final String userID;
    private final double amount;
    private final Category category;
    private final TxnType type;
    private final long coinsImpact;   // +earned or -redeemed
    private final LocalDateTime timestamp;
}