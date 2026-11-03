package bf.formation.taskboard.exception;


/**
 * Exception levee lorsqu'une ressource viole une contrainte d'unicite
 * (par exemple : creation d'un utilisateur avec un email deja existant).
 * Elle sera interceptee par le GlobalExceptionHandler pour renvoyer un code HTTP 409 CONFLICT.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}