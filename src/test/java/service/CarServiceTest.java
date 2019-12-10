package service;

import dao.ICarDAO;
import exceptions.AnotherCarFound;
import exceptions.CarNotFound;
import model.Car;
import model.inUse;
import model.usageType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class CarServiceTest {
    static CarService service;
    Car car;
    @Before
    public void setUp() throws AnotherCarFound, CarNotFound {
        ICarDAO mock = Mockito.mock(ICarDAO.class);
        service = new CarService(mock);
        Collection<Car> cars = new ArrayList<>();
        car=new Car("Opel","Corsa","NSR-107", LocalDate.of(2002,01,01),68, inUse.InUse,false, usageType.personal,170000);
        cars.add(car);

        Mockito.when(mock.getAllCars()).thenReturn(cars);
        Mockito.when(mock.getCarByTitle("NSR-107")).thenReturn(car);
        Mockito.doThrow(new AnotherCarFound(car)).when(mock).addCar(car);


    }
    @Test
    public void testGettingCars() {
        System.out.println(service.getAllCars());
    }
    @Test
    public void testGettingCar() throws CarNotFound {
        System.out.println(service.getCarByTitle("NSR-107"));
    }
    @Test(expected = AnotherCarFound.class)
    public void testDuplicate() throws AnotherCarFound, CarNotFound {
        service.addCar(new Car("Opel","Corsa","NSR-107", LocalDate.of(2002,01,01),68, inUse.InUse,false, usageType.personal,170000));
    }

    @Test
    public void testInuse(){
        System.out.println(service.getCarsInUse());
    }
    @Test
    public void testUnderCustody(){

        if((service.getCarsUnderCustody().size())==0){
            System.out.println("No car is under custody!");
        }
        else{
            System.out.println("Cars under custody:");
            System.out.println(service.getCarsUnderCustody());
        }
    }
    @Test
    public void testCustodySetter(){

        System.out.println(service.CustodyContains(car));
    }
    @Test
    public void testCustodySetterSecond(){
        service.setCustody(car);
        assertTrue(service.CustodyContains(car));
    }







}