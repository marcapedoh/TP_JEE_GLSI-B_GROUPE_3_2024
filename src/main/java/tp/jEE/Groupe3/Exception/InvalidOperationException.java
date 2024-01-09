package tp.jEE.Groupe3.Exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException{
    @Getter
    private ErrorCodes errorCodes;

    public InvalidOperationException(String message){
        super(message);
    }
    public  InvalidOperationException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
