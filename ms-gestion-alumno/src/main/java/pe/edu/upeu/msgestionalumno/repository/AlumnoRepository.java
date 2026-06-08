package pe.edu.upeu.msgestionalumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.msgestionalumno.entity.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    boolean existsByCodigo(String codigo);
    boolean existsByCorreo(String correo);
}