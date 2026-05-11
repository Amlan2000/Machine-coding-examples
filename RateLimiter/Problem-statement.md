Problem Statement: Pluggable API Rate Limiter

Context
Website is launching a set of premium travel APIs. To protect the infrastructure from bursts and ensure fair usage, you need to build a standalone library for rate limiting. The library should be capable of blocking requests from specific users based on predefined rules.
Functional Requirements

Rule Configuration: Support different rate limits for different users or API endpoints (e.g., User A can make 10 requests/min, while User B can make 100 requests/min).
Multiple Algorithms: The system must support at least two algorithms.
Fixed Window: Resets the counter at the start of every window.
Token Bucket: Allows for bursts by maintaining a "bucket" of tokens that refills at a fixed rate.
Default Fallback: If no specific rule is defined for a user, the system should fall back to a global default limit.

Client Response: The system should return a result containing:
isAllowed (Boolean)
remainingQuota (Integer)
retryAfterMs (Long - time until they can try again if blocked).


Non-Functional Requirements
Low Latency: The isAllowed() check must be highly efficient.
In-Memory: Use in-memory data structures only (no external Redis/DB).
Thread Safety: Since this library will be used in a multi-threaded web server, it must handle concurrent requests for the same user without race conditions.

