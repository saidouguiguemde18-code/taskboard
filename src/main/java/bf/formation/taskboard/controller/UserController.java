package bf.formation.taskboard.controller;

import bf.formation.taskboard.DTO.UserRequestDTO;
import bf.formation.taskboard.DTO.UserResponseDTO;
import bf.formation.taskboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Couche Controller : expose l'API REST pour les utilisateurs.
 *
 * Utilise les DTOs pour isoler le modele de persistance de l'exposition HTTP.
 * Utilise @Valid pour declencher la validation declarative des donnees d'entree.
 *
 * Endpoints disponibles :
 *   POST   /api/users       -> creer un utilisateur
 *   GET    /api/users       -> lister tous les utilisateurs
 *   GET    /api/users/{id}  -> recuperer un utilisateur par id
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO created = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}