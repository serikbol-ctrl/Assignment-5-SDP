package edu.university.strategy;
import java.util.Objects;
public final class FeeSelector {
 private final RateSource rates;
 public FeeSelector(RateSource rates){this.rates=Objects.requireNonNull(rates);}
 public FeeStrategy select(FeeRequest request){
  if(request.international()) return new InternationalFeeStrategy(rates);
  if(request.scholarship()) return new ScholarshipFeeStrategy(rates);
  return new StandardFeeStrategy(rates);
 }
}
