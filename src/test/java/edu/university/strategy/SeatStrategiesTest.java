package edu.university.strategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SeatStrategiesTest {
 private final Course open=new Course("CS401",3,1,3);
 @Test void firstComeUsesAvailability(){assertTrue(new FirstComeSeatStrategy().decide(new Student("a",2,10,false),open).admitted());assertFalse(new FirstComeSeatStrategy().decide(new Student("a",2,10,false),new Course("X",1,1,3)).admitted());}
 @Test void meritUsesGpa(){assertTrue(new MeritSeatStrategy().decide(new Student("a",3.8,10,false),open).admitted());assertFalse(new MeritSeatStrategy().decide(new Student("a",3.1,10,false),open).admitted());}
 @Test void graduationUsesSeniorEligibility(){assertTrue(new GraduationSeatStrategy().decide(new Student("a",2.1,110,true),open).admitted());assertFalse(new GraduationSeatStrategy().decide(new Student("a",3.9,70,true),open).admitted());}
 @Test void selectorRules(){SeatSelector s=new SeatSelector();assertInstanceOf(FirstComeSeatStrategy.class,s.select(new Student("a",4,100,true),new Course("X",1,1,3)));assertInstanceOf(GraduationSeatStrategy.class,s.select(new Student("a",2,110,true),open));assertInstanceOf(MeritSeatStrategy.class,s.select(new Student("a",4,20,false),open));assertInstanceOf(FirstComeSeatStrategy.class,s.select(new Student("a",4,20,false),new Course("X",10,0,3)));}
 @Test void contextCanSwitch(){SeatContext c=new SeatContext(new FirstComeSeatStrategy());assertTrue(c.evaluate(new Student("a",2,10,false),open).admitted());c.setStrategy(new MeritSeatStrategy());assertFalse(c.evaluate(new Student("a",2,10,false),open).admitted());}
}
