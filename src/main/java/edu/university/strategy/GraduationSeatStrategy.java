package edu.university.strategy;
public final class GraduationSeatStrategy implements SeatStrategy {
 public SeatDecision decide(Student s, Course c){boolean ok=c.freeSeats()>0 && s.graduating() && s.completedCredits()>=90;return new SeatDecision(ok,ok?"Graduating senior priority":"No seat or not an eligible senior");}
 public String name(){return "Graduation-priority";}
}
