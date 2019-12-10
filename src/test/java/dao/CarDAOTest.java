package dao;

import exceptions.AnotherCarFound;
import exceptions.CarNotFound;
import model.Car;
import model.inUse;
import model.usageType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;

public class CarDAOTest {
    ICarDAO dao;
    static String file ="cars.json";

    @BeforeClass
    public static void setUp(){
        File jsonfile = new File(file);
        if (jsonfile.exists()){
            jsonfile.delete();
        }


    }


    @Before
    public void newDAO() throws AnotherCarFound, CarNotFound {
     dao=new CarDAO(file);


    }
    @Test
    public void addCar() throws AnotherCarFound, CarNotFound {


        dao.addCar(new Car("BMW","325i","MNN-971", LocalDate.of(2005,01,01),1920, inUse.InUse,false, usageType.personal,170000));
    }
    @Test (expected = AnotherCarFound.class)
    public void addAnotherTest() throws AnotherCarFound, CarNotFound {


        dao.addCar(new Car("BMW","325i","MNN-971", LocalDate.of(1996,10,10),1920, inUse.InUse,false, usageType.personal,170000));
    }

    @Test
    public void getAllCars() {

        Collection<Car> arr = dao.getAllCars();
        for (Car c : arr
        ) {
            System.out.println(c);

        }
    }



    @Test(expected = CarNotFound.class)
    public void getCarByTitle() throws CarNotFound, AnotherCarFound {

        System.out.println(dao.getCarByTitle("MMN-971"));

    }

}