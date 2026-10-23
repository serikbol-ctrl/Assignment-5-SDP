package edu.university.strategy;
import java.util.Objects;
public final class SeatContext {
 private SeatStrategy strategy;
 public SeatContext(SeatStrategy strategy){setStrategy(strategy);}
 public void setStrategy(SeatStrategy strategy){this.strategy=Objects.requireNonNull(strategy);}
 public SeatDecision evaluate(Student student, Course course){return strategy.decide(student,course);}
 public String currentStrategy(){return strategy.name();}
}
