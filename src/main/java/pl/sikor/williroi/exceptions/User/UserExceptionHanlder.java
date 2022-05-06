package pl.sikor.williroi.exceptions.User;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class UserExceptionHanlder {

    private Logger logger = LoggerFactory.getLogger(UserExceptionHanlder.class);

    @ExceptionHandler
    public final ResponseEntity<String> handleUserExceptions(Exception ex, WebRequest request){
        HttpHeaders httpHeaders = new HttpHeaders();

        if(ex instanceof UserDoesntExistException) {
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            UserDoesntExistException userDoesntExistException = (UserDoesntExistException) ex;
            return handleUserDoesntExistException(userDoesntExistException, httpHeaders, httpStatus, request);
        } else if (ex instanceof UserAlreadyExistException){
            HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            UserAlreadyExistException userAlreadyExistException = (UserAlreadyExistException) ex;
            return handleUserAlreadyExistException(userAlreadyExistException, httpHeaders, httpStatus, request);
        }
        
        else {
            logger.warn(ex.getMessage());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleOtherException(ex, httpHeaders, status, request);
        }
    }
    
    private ResponseEntity<String> handleUserDoesntExistException(UserDoesntExistException userDoesntExistException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {
        String exceptionContent = userDoesntExistException.getMessage();
        return handleExceptionInternal(userDoesntExistException, exceptionContent, httpHeaders, httpStatus, request);
    }
    private ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException userAlreadyExistException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {
        String exceptionContent = userAlreadyExistException.getMessage();
        return handleExceptionInternal(userAlreadyExistException, exceptionContent, httpHeaders, httpStatus, request);
    }


    private ResponseEntity<String> handleOtherException(Exception exception, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {
        String exceptionContent = exception.getMessage();
        return handleExceptionInternal(exception, exceptionContent, httpHeaders, httpStatus, request);
    }
    private ResponseEntity<String> handleExceptionInternal(Exception exception, String exceptionContent, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(exceptionContent, httpHeaders, httpStatus);
    }
}
