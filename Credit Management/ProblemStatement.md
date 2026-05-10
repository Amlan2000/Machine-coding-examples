Machine Coding Question: Scapia Rewards & Redemption Engine

Problem Statement
Scapia is a travel fintech that lets users earn Scapia Coins on every credit card spend and redeem them for travel bookings, airport privileges, and in-app purchases.
You have to design and implement a Rewards & Redemption Engine as a console/REST application.

Core Requirements
1. User & Card Management

Create a user with a card (cardId, userId, creditLimit)
Each user has a Scapia Coins wallet (starts at 0)

2. Transaction & Coin Accrual

Record a spend transaction: (userId, amount, category)
Categories: TRAVEL, DINING, SHOPPING, OTHER
Coin earn rates:

TRAVEL → 2x coins per ₹ spent
DINING → 1.5x coins per ₹ spent
SHOPPING, OTHER → 1x coins per ₹ spent


Coins are always whole numbers (floor)
Coins are credited only after transaction is confirmed (not pending)

3. Redemption

Users can redeem coins for:

FLIGHT_BOOKING — minimum 5,000 coins, 1 coin = ₹0.25
HOTEL_BOOKING — minimum 2,000 coins, 1 coin = ₹0.20
AIRPORT_PRIVILEGE — minimum 1,000 coins, 1 coin = ₹0.20


Validate: user must have sufficient coins
Deduct coins and return equivalent ₹ value redeemed

4. Transaction History

List all transactions for a user (spends + redemptions), sorted by time
Show: type, amount/coins, category, timestamp

5. Leaderboard

Show top-N users by total coins earned (not current balance — total ever earned)


Bonus (if time permits)

Expiry: Coins earned expire 365 days from earning date. Redemption should use oldest coins first (FIFO).
Referral Bonus: When user A refers user B, A gets 500 bonus coins after B's first transaction.


Constraints & Expectations
AspectExpectationLanguageJava / Python / Go (your choice)ArchitectureClean separation: models, service, repositoryNo DB neededIn-memory storage is fineNo frameworks neededPlain classes; no Spring/DjangoError handlingGraceful errors for insufficient coins, invalid user, etc.Test coverageAt least 2–3 unit tests for core logic

Sample Interaction (CLI or unit test style)
createUser("u1", "Alice")
createUser("u2", "Bob")

recordTransaction("u1", 10000, "TRAVEL")   → +20,000 coins
recordTransaction("u1", 5000, "DINING")    → +7,500 coins
recordTransaction("u2", 8000, "SHOPPING")  → +8,000 coins

getWalletBalance("u1")  → 27,500 coins
getWalletBalance("u2")  → 8,000 coins

redeem("u1", "FLIGHT_BOOKING", 10000)  → ₹2,500 redeemed. Balance: 17,500 coins
redeem("u1", "AIRPORT_PRIVILEGE", 500) → ERROR: Minimum 1,000 coins required

getTransactionHistory("u1") → [spend ₹10000 TRAVEL, spend ₹5000 DINING, redeem 10000 coins FLIGHT_BOOKING]

getLeaderboard(2) → [Alice: 27500, Bob: 8000]

Evaluation Criteria

Correctness — Does the logic work for all cases?
OOP Design — Clean models, single responsibility, no god classes
Extensibility — Can a new category or redemption type be added easily?
Edge cases — Insufficient balance, unknown userId, zero-amount transactions
Code readability — Naming, structure, no spaghetti