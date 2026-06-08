package pe.edu.upeu.msgestiontaller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String correo;
}