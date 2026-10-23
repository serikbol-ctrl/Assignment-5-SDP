package edu.university.strategy;
import java.util.Objects;
public final class FeeContext {
 private FeeStrategy strategy;
 public FeeContext(FeeStrategy strategy){setStrategy(strategy);}
 public void setStrategy(FeeStrategy strategy){this.strategy=Objects.requireNonNull(strategy);}
 public double quote(FeeRequest request){return strategy.calculate(request);}
 public String currentStrategy(){return strategy.name();}
}
