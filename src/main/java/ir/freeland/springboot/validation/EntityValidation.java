package ir.freeland.springboot.validation;


public class EntityValidation<T> {

	private final T entity;
    private final boolean hasError;
    private final String errorMessage;
    private final int errorCode;

    public EntityValidation(T entity, boolean hasError, String errorMessage, int errorCode) {
        this.entity = entity;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public T getEntity() {
        return entity;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

