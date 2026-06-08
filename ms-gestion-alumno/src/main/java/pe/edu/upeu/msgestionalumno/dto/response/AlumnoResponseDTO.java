package pe.edu.upeu.msgestionalumno.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoResponseDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private String correo;
}