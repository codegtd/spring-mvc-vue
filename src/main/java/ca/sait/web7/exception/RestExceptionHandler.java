package ca.sait.web7.exception;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception)
    {
        ResourceNotFoundDetail excDet = new ResourceNotFoundDetail(
                "Resource nao encontrado",
                exception.getMessage(),
                exception.getClass().getName(),
                HttpStatus.NOT_FOUND.value(),
                new Date().getTime());

        return new ResponseEntity<>(excDet, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationErrorException(MethodArgumentNotValidException except)
    {
        ValidationErrorsDetailList listErrors = new ValidationErrorsDetailList(
                "Validation Errors List",
                except.getMessage(),
                except.getClass().getName(),
                HttpStatus.NOT_FOUND.value(),
                new Date().getTime(),
                new ArrayList<>());

        except.getBindingResult().getFieldErrors().forEach(item -> {
            listErrors.addError(item.getField(), item.getDefaultMessage());
        });
        
        return new ResponseEntity<>(listErrors, HttpStatus.BAD_REQUEST);
    }

}
