package bf.formation.taskboard.controller;

import bf.formation.taskboard.DTO.TaskRequestDTO;
import bf.formation.taskboard.DTO.TaskResponseDTO;
import bf.formation.taskboard.DTO.TaskUpdateRequestDTO;
import bf.formation.taskboard.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Couche Controller : expose l'API REST pour les taches (Task).
 *
 * Endpoints disponibles :
 *   POST   /api/boards/{boardId}/tasks           -> creer une tache
 *   GET    /api/boards/{boardId}/tasks           -> lister les taches d'un tableau
 *   GET    /api/boards/{boardId}/tasks/{taskId}  -> recuperer une tache par id
 *   PUT    /api/boards/{boardId}/tasks/{taskId}  -> mettre a jour une tache
 *   DELETE /api/boards/{boardId}/tasks/{taskId}  -> supprimer une tache
 */
@RestController
@RequestMapping("/api/boards/{boardId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @PathVariable Long boardId,
            @Valid @RequestBody TaskRequestDTO requestDTO) {

        TaskResponseDTO created = taskService.createTask(boardId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskService.getTasksByBoard(boardId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTaskById(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId,
            @Valid @RequestBody TaskUpdateRequestDTO requestDTO) {

        return ResponseEntity.ok(taskService.updateTask(taskId, requestDTO));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long boardId,
            @PathVariable Long taskId) {

        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}