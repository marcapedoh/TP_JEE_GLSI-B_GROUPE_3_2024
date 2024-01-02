package tp.jEE.Groupe3.Exception;

import lombok.Getter;

import java.util.List;

public class InvalidEntityException extends RuntimeException{

    @Getter
    public ErrorCodes errorCodes;
    @Getter
    public List<String> errors;

    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }

    public InvalidEntityException(String message,ErrorCodes errorCodes, List<String> errors){
        super(message);
        this.errorCodes=errorCodes;
        this.errors=errors;
    }
}
