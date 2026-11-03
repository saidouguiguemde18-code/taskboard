package bf.formation.taskboard.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Format standardise renvoye a l'utilisateur/client en cas d'erreur HTTP.
 *
 * @JsonInclude(JsonInclude.Include.NON_NULL) permet de ne pas afficher
 * la propriete 'validationErrors' si aucune erreur de validation de champ n'existe.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;
}