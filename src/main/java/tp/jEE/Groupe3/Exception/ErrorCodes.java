package tp.jEE.Groupe3.Exception;

public enum ErrorCodes {

    TRANSACTION_NOT_VALID(1000),
    COMPTE_NOT_VALID(2000),
    COMPTE_NOT_FOUND(2001),
    CLIENT_NOT_VALID(3000),
    CLIENT_NOT_FOUND(3001);
    ErrorCodes(int code){
        this.errorCode=code;
    }
    private int errorCode;

    public int getErrorCode(){
        return errorCode;
    }
}
