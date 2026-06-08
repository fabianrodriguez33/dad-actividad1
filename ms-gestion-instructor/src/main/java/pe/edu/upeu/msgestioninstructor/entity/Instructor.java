package pe.edu.upeu.msgestioninstructor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 120)
    private String especialidad;

    @Column(nullable = false, unique = true, length = 120)
    private String correo;
}