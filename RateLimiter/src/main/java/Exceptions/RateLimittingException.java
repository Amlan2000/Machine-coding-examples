package Exceptions;

public class RateLimittingException extends RuntimeException{
    public RateLimittingException(String msg){
        super(msg);
    }
}
