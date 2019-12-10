package service;

import dao.CarDAO;
import dao.ICarDAO;
import exceptions.AnotherCarFound;
import exceptions.CarNotFound;
import model.Car;
import model.inUse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class CarService {
    ICarDAO carDAO;
    public CarService(ICarDAO carDAO){this.carDAO=carDAO;};

    public void addCar(Car car) throws AnotherCarFound, CarNotFound{
        carDAO.addCar(car);

    }
    public Car getCarByTitle(String title) throws CarNotFound{
        return carDAO.getCarByTitle(title);

    }
    public Collection<Car> getAllCars(){
        return carDAO.getAllCars();

    }
    public Collection<Car> getCarsInUse(){
        Collection<Car> result=new ArrayList<>();
        Collection<Car>cars =carDAO.getAllCars();
        for(Car c:cars){
            if(c.getInUse().equals(inUse.InUse)){
                result.add(c);
            }
        }
        return result;
    }
    public Collection<Car> getCarsUnderCustody(){
        Collection<Car> result=new ArrayList<>();
        Collection<Car>cars =carDAO.getAllCars();
        for(Car c:cars){
            if(c.isUnderCustody()==true){
                result.add(c);
            }
        }
        return result;
    }
    public void setCustody(Car car){
        car.setUnderCustody(true);
    }
    public boolean CustodyContains(Car car){
        Collection<Car> cars=getCarsUnderCustody();
        return cars.contains(car);
    }

}
