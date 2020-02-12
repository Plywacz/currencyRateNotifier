package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 09.02.2020
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectJsonInputException extends RuntimeException {

    private String fieldName;
    private String fieldValue;
    private String description;


    public IncorrectJsonInputException(String message) {
        super(message);
    }

    public IncorrectJsonInputException(Throwable cause, String fieldName, String fieldValue, String description) {
        super(cause);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.description = description;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public String getDescription() {
        return description;
    }

    public String getInfo() {
        return fieldName + fieldValue + description;
    }
}
