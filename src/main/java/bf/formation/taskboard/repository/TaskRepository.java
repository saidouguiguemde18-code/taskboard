package bf.formation.taskboard.repository;

import bf.formation.taskboard.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * SELECT * FROM tasks WHERE board_id = ?
     * Genere automatiquement par Spring Data a partir du nom de la methode.
     */
    List<Task> findByBoardId(Long boardId);
}
