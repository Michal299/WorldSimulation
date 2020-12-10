package Models.Worlds;

public class TakenPlaceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 637413919701836797L;
    
    public TakenPlaceException(String errorMessage) {
        super(errorMessage);
    }
}