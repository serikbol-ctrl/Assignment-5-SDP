package edu.university.strategy;
public final class FirstComeSeatStrategy implements SeatStrategy {
 public SeatDecision decide(Student s, Course c){return new SeatDecision(c.freeSeats()>0,c.freeSeats()>0?"Available seat":"Course full");}
 public String name(){return "First-come";}
}
