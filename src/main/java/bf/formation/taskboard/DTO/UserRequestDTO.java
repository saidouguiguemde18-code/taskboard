package bf.formation.taskboard.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas depasser 100 caracteres")
    private String name;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit etre valide")
    @Size(max = 100, message = "L'email ne doit pas depasser 100 caracteres")
    private String email;
}