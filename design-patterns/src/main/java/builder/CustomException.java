package builder;
public class CustomException extends Exception {
    private int errorCode;
    private String details;

    // Private Constructor that calls super() for Exception message
    private CustomException(String message) {
        super(message);
    }

    // Getters
    public int getErrorCode() { return errorCode; }
    public String getDetails() { return details; }

    // Private setters - only accessible to the Builder
    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "message='" + getMessage() + '\'' +
                ", errorCode=" + errorCode +
                ", details='" + details + '\'' +
                '}';
    }

    // Static Builder Class
    public static class CustomExceptionBuilder {
        private final CustomException exception;

        // Constructor requires a mandatory exception message
        public CustomExceptionBuilder(String message) {
            // The inner builder class uses the private constructor of CustomException
            this.exception = new CustomException(message);
        }

        public CustomExceptionBuilder errorCode(int errorCode) {
            this.exception.setErrorCode(errorCode);
            return this;
        }

        public CustomExceptionBuilder details(String details) {
            this.exception.setDetails(details);
            return this;
        }

        public CustomException build() {
            return this.exception;
        }
    }
}