package bf.formation.taskboard.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO renvoye pour representer un tableau.
 * Expose les informations cles sans charger recursivement toutes les taches
 * ni risquer une boucle infinie avec l'entite User.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDTO {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private Long ownerId;
    private String ownerName;
}