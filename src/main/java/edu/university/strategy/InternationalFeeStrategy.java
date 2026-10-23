package edu.university.strategy;
import java.util.Objects;
public final class InternationalFeeStrategy implements FeeStrategy {
 private final RateSource rates;
 public InternationalFeeStrategy(RateSource rates){this.rates=Objects.requireNonNull(rates);}
 public double calculate(FeeRequest r){return r.credits()*rates.tuitionPerCredit()*rates.internationalMultiplier()+15000;}
 public String name(){return "International with service fee";}
}
