# Assignment 5 — Strategy Pattern (University Registration)

**Java 17 / Maven / JUnit 5.** This is a separate Assignment 5 project; no Assignment 4 code is required.

## Run

```bash
mvn clean test
mvn exec:java
```

Menu 1 applies all three seat algorithms to the same student/course. Menu 2 compares all three fee algorithms on the same request. Menu 3 automatically selects strategies. Menu 4 replaces both strategies at runtime.

## Two strategy families

**Seat allocation:** first-come accepts any available seat; merit requires GPA >= 3.5; graduation-priority requires a graduating student with at least 90 completed credits. These algorithms use distinct eligibility rules.

**Tuition:** standard computes per-credit tuition; scholarship subtracts a fixed grant capped at the base amount; international applies a multiplier plus a fixed service fee. Rates come from `RateSource` (simulated implementation, fake in tests). The `lateRegistration` request flag is retained for potential extension, but does not affect current policies.

Selectors choose based on actual input. For seats: full courses fall back to first-come denial, graduating seniors get priority, nearly full courses use merit, other cases use first-come. For fees: international > scholarship > standard. Context classes only delegate and allow `setStrategy`; no strategy-type conditionals.

## Class responsibilities (each description is one sentence, <=25 words)

| Class | Responsibility |
|---|---|
| Student | Represents academic eligibility information. |
| Course | Represents enrollment capacity information. |
| SeatDecision | Records a seat decision explanation. |
| SeatStrategy | Defines the seat admission algorithm contract. |
| FirstComeSeatStrategy | Admits students based on remaining capacity. |
| MeritSeatStrategy | Admits students based on grade eligibility. |
| GraduationSeatStrategy | Prioritizes eligible graduating seniors. |
| SeatContext | Delegates admission decisions to a replaceable strategy. |
| SeatSelector | Chooses admission policy from current conditions. |
| FeeRequest | Represents tuition quotation inputs. |
| FeeStrategy | Defines the tuition calculation contract. |
| RateSource | Abstracts external tuition rate information. |
| SimulatedRateSource | Supplies deterministic demonstration rates. |
| StandardFeeStrategy | Calculates standard credit-based tuition. |
| ScholarshipFeeStrategy | Applies a capped scholarship grant. |
| InternationalFeeStrategy | Calculates international tuition with a service fee. |
| FeeContext | Delegates tuition quotations to a replaceable strategy. |
| FeeSelector | Chooses tuition policy from student billing conditions. |
| Demo | Demonstrates comparison, selection, runtime replacement. |

## Why Strategy rather than State or Template Method?

Strategy expresses **interchangeable policies** chosen by a client or selector. `SeatContext` can use merit eligibility for a course, then replace it with first-come admission without any internal lifecycle transition. `FeeContext` similarly delegates to one of several pricing algorithms. State is better when behavior changes because an object enters a new lifecycle state and transitions are intrinsic to the object. Here, policy selection is external: the same student, course, or fee request can be evaluated using different strategies. Template Method would fix the algorithm's outline in a superclass with subclasses overriding selected steps. Our algorithms do not share a meaningful invariant sequence of steps; they share a small interface. Composition also lets a selector install a new policy at runtime without subclassing a context. A new policy class can be introduced without editing existing context code, supporting the Open/Closed Principle.

## Refactoring evidence — REQUIRED before submission

The assignment requires **two real Git commits** for one family: (1) a working legacy implementation using `if`/`switch`, (2) a Strategy refactor, with the same tests passing on both commits. This archive contains the **final Strategy implementation only**. Do not invent commit hashes or claim this requirement is complete. Make genuine commits while performing the refactor, then replace these placeholders:

- Before commit: **[insert real Git commit URL]**
- After commit: **[insert real Git commit URL]**

For a truthful history, build the legacy version yourself, commit it with working tests, then refactor and commit the Strategy version. Never rewrite timestamps to simulate prior work.

## Tests

`mvn test` covers each strategy, selector rules, context switching, a fake external data source, invalid input, plus a JUnit 5 parameterized non-negative-fee invariant. UML: `docs/uml.md`.
