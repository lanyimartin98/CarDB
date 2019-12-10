package exceptions;

import model.Car;

public class AnotherCarFound extends Throwable {
    public AnotherCarFound(Car car){
        super(car.toString());
    }
}
