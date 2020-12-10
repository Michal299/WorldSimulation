package Models.Utilities.Parsers;

public class ParseErrorException extends Exception {

    private static final long serialVersionUID = 1397141058347475245L;

    ParseErrorException(String errorMessage) {
        super(errorMessage);
    }
}