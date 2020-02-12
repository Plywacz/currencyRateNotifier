package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 12.02.2020
*/

public class EntityDuplicateException extends RuntimeException {
    public EntityDuplicateException(String message) {
        super(message);
    }
    public EntityDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
