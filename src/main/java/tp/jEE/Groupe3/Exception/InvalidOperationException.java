package tp.jEE.Groupe3.Exception;

public class InvalidOperationException extends RuntimeException{

    private ErrorCodes errorCodes;

    public InvalidOperationException(String message){
        super(message);
    }
    public  InvalidOperationException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
