package by.gsu.epamlab.exceptions;

public class DbConnectorException extends Exception{

    public DbConnectorException(String message) {
        super(message);
    }

    public DbConnectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
