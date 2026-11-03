package bf.formation.taskboard.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO utilise pour la creation d'un tableau.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDTO {

    @NotBlank(message = "Le titre du tableau est obligatoire")
    @Size(min = 2, max = 100, message = "Le titre doit comporter entre 2 et 100 caracteres")
    private String title;

    /**
     * Identifiant du proprietaire (User).
     * Peut etre fourni dans le corps JSON ou transmis en parametre d'URL.
     */
    private Long ownerId;
}