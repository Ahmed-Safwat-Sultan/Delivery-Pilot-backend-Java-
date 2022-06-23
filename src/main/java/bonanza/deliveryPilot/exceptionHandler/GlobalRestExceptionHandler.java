package bonanza.deliveryPilot.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity badRequest(Exception error) {
        log.error("illegalArgument ---",error.getMessage(), error);
        error.printStackTrace();
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity databaseIntegrityError(Exception error){
        log.error("data base integrity ---", error.getMessage(), error);
        error.printStackTrace();
        return new ResponseEntity<>("Violating data integrity", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
