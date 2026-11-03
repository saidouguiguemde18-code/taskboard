package bf.formation.taskboard.controller;

import bf.formation.taskboard.DTO.BoardRequestDTO;
import bf.formation.taskboard.DTO.BoardResponseDTO;
import bf.formation.taskboard.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Couche Controller : expose l'API REST pour les tableaux (Board).
 *
 * Endpoints disponibles :
 *   POST   /api/boards (avec ownerId en query param OU dans le body) -> creer un tableau
 *   GET    /api/boards?ownerId={id}                                 -> lister les tableaux d'un utilisateur
 *   GET    /api/boards/{id}                                         -> recuperer un tableau par id
 *   DELETE /api/boards/{id}                                         -> supprimer un tableau
 */
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * Creer un tableau.
     * Accepte l'ownerId soit en parametre d'URL (?ownerId=1), soit dans le corps JSON.
     */
    @PostMapping
    public ResponseEntity<BoardResponseDTO> createBoard(
            @RequestParam(required = false) Long ownerId,
            @Valid @RequestBody BoardRequestDTO requestDTO) {

        BoardResponseDTO created = boardService.createBoard(ownerId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Lister les tableaux d'un utilisateur.
     * Exemple : GET /api/boards?ownerId=1
     */
    @GetMapping
    public ResponseEntity<List<BoardResponseDTO>> getBoards(@RequestParam Long ownerId) {
        return ResponseEntity.ok(boardService.getBoardsByOwner(ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDTO> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}