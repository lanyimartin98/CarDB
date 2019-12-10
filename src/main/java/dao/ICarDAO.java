package dao;

import exceptions.AnotherCarFound;
import exceptions.CarNotFound;
import model.Car;

import java.util.Collection;

public interface ICarDAO {
    void addCar(Car car) throws AnotherCarFound, CarNotFound;
    Car getCarByTitle(String title) throws CarNotFound;
    Collection<Car> getAllCars();
}
