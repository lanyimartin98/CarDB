package model;

import exceptions.InvalidData;
import exceptions.MilageCanNotBeLowered;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CarTest {
    Car car;

    @Before
    public void newCar(){
        car=new Car("BMW","325i","MNN-971", LocalDate.of(2005,01,01),920, inUse.InUse,false, usageType.personal,170000);

    }



    @Test (expected = InvalidData.class)
    public void setTitle() throws InvalidData {

        car.setTitle("MNn-990");


    }


    @Test (expected = InvalidData.class)
    public void setStartOfUse() throws InvalidData {

        car.setStartOfUse(LocalDate.of(2020,10,22));
    }

    @Test
    public void setHorsePower() {
        System.out.println(car.getUsageType());
    }


    @Test (expected = MilageCanNotBeLowered.class)
    public void setMilage() throws MilageCanNotBeLowered {
         car.setMilage(10000);
    }
    @Test
    public void setUsageType(){
        car.setHorsePower(1920);
    }

    @Test
    public void testToString() {
        System.out.println(car.toString());
    }

}
