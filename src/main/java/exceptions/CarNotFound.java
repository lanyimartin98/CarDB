package exceptions;

public class CarNotFound extends Throwable {
    public CarNotFound(String title){
        super(title+" not found in the database");
    }
}
