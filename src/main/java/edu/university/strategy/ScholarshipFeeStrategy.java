package edu.university.strategy;
import java.util.Objects;
public final class ScholarshipFeeStrategy implements FeeStrategy {
 private final RateSource rates;
 public ScholarshipFeeStrategy(RateSource rates){this.rates=Objects.requireNonNull(rates);}
 public double calculate(FeeRequest r){double base=r.credits()*rates.tuitionPerCredit();return Math.max(0,base-Math.min(base,60000));}
 public String name(){return "Scholarship grant";}
}
