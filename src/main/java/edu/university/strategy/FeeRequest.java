package edu.university.strategy;
public record FeeRequest(int credits, boolean scholarship, boolean international, boolean lateRegistration) {
 public FeeRequest {if(credits<=0 || credits>30) throw new IllegalArgumentException("Credits must be 1..30");}
}
