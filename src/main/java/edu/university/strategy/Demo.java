package edu.university.strategy;
import java.util.List;
import java.util.Scanner;
public final class Demo {
 private Demo(){}
 public static void main(String[] args){
  Scanner input=new Scanner(System.in);RateSource rates=new SimulatedRateSource();
  Student student=new Student("S100",3.8,110,true);Course course=new Course("CS401",2,1,3);
  FeeRequest request=new FeeRequest(5,true,false,false);
  SeatContext seats=new SeatContext(new FirstComeSeatStrategy());
  FeeContext fees=new FeeContext(new StandardFeeStrategy(rates));
  while(true){
   System.out.println("\nUNIVERSITY REGISTRATION STRATEGY");
   System.out.println("1. Compare seat strategies  2. Compare fee strategies");
   System.out.println("3. Select automatically   4. Switch manually  0. Exit");
   System.out.print("Choice: ");String choice=input.nextLine().trim();
   switch(choice){
    case "1" -> {for(SeatStrategy s:List.of(new FirstComeSeatStrategy(),new MeritSeatStrategy(),new GraduationSeatStrategy())) System.out.println(s.name()+": "+s.decide(student,course));}
    case "2" -> {for(FeeStrategy s:List.of(new StandardFeeStrategy(rates),new ScholarshipFeeStrategy(rates),new InternationalFeeStrategy(rates))) System.out.println(s.name()+": "+s.calculate(request)+" KZT");}
    case "3" -> {seats.setStrategy(new SeatSelector().select(student,course));fees.setStrategy(new FeeSelector(rates).select(request));System.out.println("Seat: "+seats.currentStrategy()+" -> "+seats.evaluate(student,course));System.out.println("Fee: "+fees.currentStrategy()+" -> "+fees.quote(request));}
    case "4" -> {seats.setStrategy(new MeritSeatStrategy());fees.setStrategy(new InternationalFeeStrategy(rates));System.out.println("Switched: "+seats.currentStrategy()+" / "+fees.currentStrategy());System.out.println(seats.evaluate(student,course));System.out.println(fees.quote(request)+" KZT");}
    case "0" -> {return;}
    default -> System.out.println("Unknown option");
   }
  }
 }
}
