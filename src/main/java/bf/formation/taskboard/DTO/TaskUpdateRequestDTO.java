package bf.formation.taskboard.DTO;


import bf.formation.taskboard.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO pour la mise a jour complete d'une tache (titre, description, statut).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateRequestDTO {

    @NotBlank(message = "Le titre de la tache est obligatoire")
    @Size(min = 2, max = 255, message = "Le titre doit comporter entre 2 et 255 caracteres")
    private String title;

    @Size(max = 1000, message = "La description ne peut pas depasser 1000 caracteres")
    private String description;

    @NotNull(message = "Le statut est obligatoire (valeurs possibles: A_FAIRE, EN_COURS, TERMINE)")
    private TaskStatus status;
}