package edu.university.strategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
class FeeStrategiesTest {
 static final RateSource FAKE=new RateSource(){public double tuitionPerCredit(){return 100;}public double internationalMultiplier(){return 1.5;}};
 static Stream<FeeStrategy> strategies(){return Stream.of(new StandardFeeStrategy(FAKE),new ScholarshipFeeStrategy(FAKE),new InternationalFeeStrategy(FAKE));}
 @ParameterizedTest @MethodSource("strategies") void quoteNeverNegative(FeeStrategy s){assertTrue(s.calculate(new FeeRequest(1,false,false,false))>=0);}
 @Test void standardCalculation(){assertEquals(300,new StandardFeeStrategy(FAKE).calculate(new FeeRequest(3,false,false,false)));}
 @Test void scholarshipGrantHasFloor(){assertEquals(0,new ScholarshipFeeStrategy(FAKE).calculate(new FeeRequest(3,true,false,false)));}
 @Test void internationalHasMultiplierAndServiceFee(){assertEquals(15450,new InternationalFeeStrategy(FAKE).calculate(new FeeRequest(3,false,true,false)));}
 @Test void selectorRules(){FeeSelector s=new FeeSelector(FAKE);assertInstanceOf(InternationalFeeStrategy.class,s.select(new FeeRequest(3,true,true,false)));assertInstanceOf(ScholarshipFeeStrategy.class,s.select(new FeeRequest(3,true,false,false)));assertInstanceOf(StandardFeeStrategy.class,s.select(new FeeRequest(3,false,false,false)));}
 @Test void contextSwitchesAtRuntime(){FeeContext c=new FeeContext(new StandardFeeStrategy(FAKE));assertEquals(300,c.quote(new FeeRequest(3,false,false,false)));c.setStrategy(new InternationalFeeStrategy(FAKE));assertEquals(15450,c.quote(new FeeRequest(3,false,true,false)));}
 @Test void invalidInputsRejected(){assertThrows(IllegalArgumentException.class,()->new FeeRequest(0,false,false,false));assertThrows(IllegalArgumentException.class,()->new Student("",2,10,false));}
}
