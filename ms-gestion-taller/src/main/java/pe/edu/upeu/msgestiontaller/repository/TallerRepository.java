package pe.edu.upeu.msgestiontaller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.msgestiontaller.entity.Taller;

@Repository
public interface TallerRepository extends JpaRepository<Taller, Long> {
}