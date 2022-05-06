package pl.sikor.williroi.exceptions.User;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
