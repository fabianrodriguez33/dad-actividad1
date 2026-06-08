package pe.edu.upeu.msgestiontaller.exception;

public class TallerNotFoundException extends RuntimeException {
    public TallerNotFoundException(String message) {
        super(message);
    }
}