package pe.edu.upeu.msgestioninstructor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorResponseDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String correo;
}