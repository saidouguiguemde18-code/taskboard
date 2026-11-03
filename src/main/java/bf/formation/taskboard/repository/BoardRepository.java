package bf.formation.taskboard.repository;

import bf.formation.taskboard.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * Query method derivee : Spring Data JPA traduit le nom de la methode
     * en : SELECT * FROM boards WHERE owner_id = ?
     *
     * "findByOwnerId" navigue directement dans la relation owner (User)
     * jusqu'a son champ id, sans avoir besoin d'ecrire de JPQL.
     */
    List<Board> findByOwnerId(Long ownerId);
}
