package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 09.02.2020
*/

public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(Throwable cause, String resourceName) {
        super(cause);
        this.resourceName = resourceName;
    }
}
