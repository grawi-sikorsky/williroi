package pl.sikor.williroi.exceptions.User;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException(String msg){
        super(msg);
    }
}
