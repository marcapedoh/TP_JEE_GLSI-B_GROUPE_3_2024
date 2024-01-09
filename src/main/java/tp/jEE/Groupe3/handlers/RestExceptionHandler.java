package tp.jEE.Groupe3.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tp.jEE.Groupe3.Exception.EntityNotFoundException;
import tp.jEE.Groupe3.Exception.InvalidEntityException;
import tp.jEE.Groupe3.Exception.InvalidOperationException;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDAO> handleExcpetion(EntityNotFoundException excpetion, WebRequest webRequest){
        final HttpStatus notfound= HttpStatus.NOT_FOUND;
        final ErrorDAO errorDAO=ErrorDAO.builder()
                .code(excpetion.getErrorCodes())
                .httpCode(notfound.value())
                .message(excpetion.getMessage())
                .build();
        return  new ResponseEntity<>(errorDAO,notfound);
    }


    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDAO> handleException(InvalidOperationException exception, WebRequest webRequest) {

        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        final ErrorDAO errorDto = ErrorDAO.builder()
                .code(exception.getErrorCodes())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDAO> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDAO errorDto = ErrorDAO.builder()
                .code(exception.getErrorCodes())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }
}
