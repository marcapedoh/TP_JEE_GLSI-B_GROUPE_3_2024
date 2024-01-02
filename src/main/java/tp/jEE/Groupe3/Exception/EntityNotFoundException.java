package tp.jEE.Groupe3.Exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException{

    @Getter
    private ErrorCodes errorCodes;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
