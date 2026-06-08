package pe.edu.upeu.msgestioninstructor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.msgestioninstructor.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByCorreo(String correo);
}