package m.plywacz.exchangeratesapi.exceptions;
/*
Author: BeGieU
Date: 09.02.2020
*/

public class IncorrectInputException extends RuntimeException {

    private final String fieldName;
    private final String fieldValue;
    private final String description;

    public IncorrectInputException(Throwable cause, String fieldName, String fieldValue, String description) {
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
}
