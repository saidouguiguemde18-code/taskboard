package bf.formation.taskboard.DTO;

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

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
 
}