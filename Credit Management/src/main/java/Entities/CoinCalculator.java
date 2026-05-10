public class CoinCalculator {
    public static long calculate(double amount, Category category) {
        double multiplier = switch (category) {
            case TRAVEL   -> 2.0;
            case DINING   -> 1.5;
            default       -> 1.0;
        };
        return (long)(amount * multiplier);   // floor via long cast
    }
}