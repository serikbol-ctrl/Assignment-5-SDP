package edu.university.strategy;
public record Student(String id, double gpa, int completedCredits, boolean graduating) {
 public Student { if(id == null || id.isBlank() || !Double.isFinite(gpa) || gpa < 0 || gpa > 4 || completedCredits < 0) throw new IllegalArgumentException("Invalid student"); }
}
