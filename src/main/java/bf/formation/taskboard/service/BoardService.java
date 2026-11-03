package bf.formation.taskboard.service;

import bf.formation.taskboard.DTO.BoardRequestDTO;
import bf.formation.taskboard.DTO.BoardResponseDTO;
import bf.formation.taskboard.exception.ResourceNotFoundException;
import bf.formation.taskboard.mappers.BoardMapper;
import bf.formation.taskboard.model.Board;
import bf.formation.taskboard.model.User;
import bf.formation.taskboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Couche Service pour la gestion des tableaux (Board).
 *
 * Utilise BoardMapper pour convertir les DTOs vers les entites JPA et inversement.
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final BoardMapper boardMapper;

    /**
     * Creer un nouveau tableau pour un utilisateur.
     */
    public BoardResponseDTO createBoard(Long ownerId, BoardRequestDTO requestDTO) {
        Long targetOwnerId = (ownerId != null) ? ownerId : requestDTO.getOwnerId();
        if (targetOwnerId == null) {
            throw new IllegalArgumentException("L'identifiant du proprietaire (ownerId) est obligatoire");
        }

        User owner = userService.findUserEntityById(targetOwnerId);
        Board board = boardMapper.toEntity(requestDTO);
        board.setOwner(owner);

        Board savedBoard = boardRepository.save(board);
        return boardMapper.toResponseDTO(savedBoard);
    }

    /**
     * Recuperer tous les tableaux d'un utilisateur sous forme de DTOs.
     */
    public List<BoardResponseDTO> getBoardsByOwner(Long ownerId) {
        userService.findUserEntityById(ownerId);

        return boardRepository.findByOwnerId(ownerId)
                .stream()
                .map(boardMapper::toResponseDTO)
                .toList();
    }

    /**
     * Recuperer un tableau par son identifiant sous forme de DTO.
     */
    public BoardResponseDTO getBoardById(Long id) {
        Board board = findBoardEntityById(id);
        return boardMapper.toResponseDTO(board);
    }

    /**
     * Supprimer un tableau par son identifiant.
     */
    public void deleteBoard(Long id) {
        Board board = findBoardEntityById(id);
        boardRepository.delete(board);
    }

    /**
     * Methode utilitaire interne pour recuperer l'entite Board.
     */
    public Board findBoardEntityById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tableau introuvable avec id=" + id));
    }
}