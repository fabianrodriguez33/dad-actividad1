package pe.edu.upeu.msgestiontaller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TallerDetalleResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private InstructorDTO instructor;
    private AlumnoDTO alumno;
}