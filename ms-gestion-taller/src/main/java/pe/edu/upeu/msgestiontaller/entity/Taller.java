package pe.edu.upeu.msgestiontaller.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "talleres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Long instructorId;

    @Column(nullable = false)
    private Long alumnoId;
}