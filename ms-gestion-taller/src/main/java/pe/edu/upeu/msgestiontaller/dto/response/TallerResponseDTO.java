package pe.edu.upeu.msgestiontaller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TallerResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long instructorId;
    private Long alumnoId;
}