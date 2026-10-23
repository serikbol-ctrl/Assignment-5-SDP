package edu.university.strategy;
public final class MeritSeatStrategy implements SeatStrategy {
 public SeatDecision decide(Student s, Course c){boolean ok=c.freeSeats()>0 && s.gpa()>=3.5;return new SeatDecision(ok,ok?"Merit threshold passed":"No seat or GPA below 3.5");}
 public String name(){return "Merit-based";}
}
