package by.laguta.skryaga.service.exception;

public class ExchangeRateUpdateException extends Exception {

    public ExchangeRateUpdateException(String message) {
        super(message);
    }

    public ExchangeRateUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
