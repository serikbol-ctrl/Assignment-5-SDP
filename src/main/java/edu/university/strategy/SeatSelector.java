package edu.university.strategy;
public final class SeatSelector {
 public SeatStrategy select(Student student, Course course){
  if(course.freeSeats()==0) return new FirstComeSeatStrategy();
  if(student.graduating() && student.completedCredits()>=90) return new GraduationSeatStrategy();
  if(course.freeSeats()<=2) return new MeritSeatStrategy();
  return new FirstComeSeatStrategy();
 }
}
