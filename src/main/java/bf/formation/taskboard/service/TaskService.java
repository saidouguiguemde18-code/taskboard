package bf.formation.taskboard.service;

import bf.formation.taskboard.DTO.TaskRequestDTO;
import bf.formation.taskboard.DTO.TaskResponseDTO;
import bf.formation.taskboard.DTO.TaskUpdateRequestDTO;
import bf.formation.taskboard.exception.ResourceNotFoundException;
import bf.formation.taskboard.mappers.TaskMapper;
import bf.formation.taskboard.model.Board;
import bf.formation.taskboard.model.Task;
import bf.formation.taskboard.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Couche Service pour la gestion des taches (Task).
 *
 * Utilise TaskMapper pour convertir les DTOs vers les entites JPA et inversement.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardService boardService;
    private final TaskMapper taskMapper;

    /**
     * Creer une nouvelle tache dans un tableau.
     */
    public TaskResponseDTO createTask(Long boardId, TaskRequestDTO requestDTO) {
        Board board = boardService.findBoardEntityById(boardId);

        Task task = taskMapper.toEntity(requestDTO);
        task.setBoard(board);

        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(savedTask);
    }

    /**
     * Recuperer toutes les taches d'un tableau sous forme de DTOs.
     */
    public List<TaskResponseDTO> getTasksByBoard(Long boardId) {
        boardService.findBoardEntityById(boardId);

        return taskRepository.findByBoardId(boardId)
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    /**
     * Recuperer une tache par son identifiant sous forme de DTO.
     */
    public TaskResponseDTO getTaskById(Long id) {
        Task task = findTaskEntityById(id);
        return taskMapper.toResponseDTO(task);
    }

    /**
     * Mettre a jour une tache (titre, description, statut).
     */
    public TaskResponseDTO updateTask(Long id, TaskUpdateRequestDTO requestDTO) {
        Task task = findTaskEntityById(id);
        taskMapper.updateEntityFromDTO(task, requestDTO);

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(updatedTask);
    }

    /**
     * Supprimer une tache par son identifiant.
     */
    public void deleteTask(Long id) {
        Task task = findTaskEntityById(id);
        taskRepository.delete(task);
    }

    /**
     * Methode utilitaire interne pour recuperer l'entite Task.
     */
    public Task findTaskEntityById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tache introuvable avec id=" + id));
    }
}