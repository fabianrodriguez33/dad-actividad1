package pe.edu.upeu.msgestiontaller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoDTO {
    private Long id;
    private String nombre;
    private String codigo;
    private String correo;
}