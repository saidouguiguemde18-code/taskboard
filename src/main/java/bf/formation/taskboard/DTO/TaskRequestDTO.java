package bf.formation.taskboard.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO pour la creation d'une tache dans un tableau.
 * Le statut initial (A_FAIRE) est automatiquement applique par le service metier.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {

    @NotBlank(message = "Le titre de la tache est obligatoire")
    @Size(min = 2, max = 255, message = "Le titre doit comporter entre 2 et 255 caracteres")
    private String title;

    @Size(max = 1000, message = "La description ne peut pas depasser 1000 caracteres")
    private String description;
}