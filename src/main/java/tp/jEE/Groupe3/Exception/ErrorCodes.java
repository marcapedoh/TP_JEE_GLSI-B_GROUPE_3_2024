package tp.jEE.Groupe3.Exception;

public enum ErrorCodes {

    TRANSACTION_NOT_VALID(1000);
    ErrorCodes(int code){
        this.errorCode=code;
    }
    private int errorCode;

    public int getErrorCode(){
        return errorCode;
    }
}
