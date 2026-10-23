package edu.university.strategy;
public record Course(String code, int capacity, int enrolled, int credits) {
 public Course { if(code == null || code.isBlank() || capacity < 0 || enrolled < 0 || enrolled > capacity || credits <= 0) throw new IllegalArgumentException("Invalid course"); }
 public int freeSeats(){return capacity-enrolled;}
}
