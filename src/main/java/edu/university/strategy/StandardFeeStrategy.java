package edu.university.strategy;
import java.util.Objects;
public final class StandardFeeStrategy implements FeeStrategy {
 private final RateSource rates;
 public StandardFeeStrategy(RateSource rates){this.rates=Objects.requireNonNull(rates);}
 public double calculate(FeeRequest r){return r.credits()*rates.tuitionPerCredit();}
 public String name(){return "Standard";}
}
