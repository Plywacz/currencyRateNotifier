package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 09.02.2020
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(Throwable cause, String resourceName) {
        super(cause);
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
