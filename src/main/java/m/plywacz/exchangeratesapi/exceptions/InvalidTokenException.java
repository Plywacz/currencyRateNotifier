package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 19.02.2020
*/

public class InvalidTokenException extends RuntimeException  {
    public InvalidTokenException(String message) {
        super(message);
    }
}
