package exceptions;

public class InvalidData extends Throwable {
    public InvalidData(String data){
        super(data);
    }

}
