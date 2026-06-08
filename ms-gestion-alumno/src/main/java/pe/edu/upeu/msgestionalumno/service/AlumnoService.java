package pe.edu.upeu.msgestionalumno.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.msgestionalumno.dto.request.AlumnoRequestDTO;
import pe.edu.upeu.msgestionalumno.dto.response.AlumnoResponseDTO;
import pe.edu.upeu.msgestionalumno.entity.Alumno;
import pe.edu.upeu.msgestionalumno.exception.AlumnoNotFoundException;
import pe.edu.upeu.msgestionalumno.repository.AlumnoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Transactional
    public AlumnoResponseDTO create(AlumnoRequestDTO dto) {
        if (alumnoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un alumno con ese código");
        }
        if (alumnoRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un alumno con ese correo");
        }
        return toResponse(alumnoRepository.save(toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public AlumnoResponseDTO getById(Long id) {
        return toResponse(getEntity(id));
    }

    @Transactional(readOnly = true)
    public List<AlumnoResponseDTO> getAll() {
        return alumnoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public AlumnoResponseDTO update(Long id, AlumnoRequestDTO dto) {
        Alumno alumno = getEntity(id);
        alumno.setNombre(dto.getNombre());
        alumno.setCodigo(dto.getCodigo());
        alumno.setCorreo(dto.getCorreo());
        return toResponse(alumnoRepository.save(alumno));
    }

    @Transactional
    public void delete(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new AlumnoNotFoundException("Alumno no encontrado: " + id);
        }
        alumnoRepository.deleteById(id);
    }

    private Alumno getEntity(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno no encontrado: " + id));
    }

    private Alumno toEntity(AlumnoRequestDTO dto) {
        return Alumno.builder()
                .nombre(dto.getNombre())
                .codigo(dto.getCodigo())
                .correo(dto.getCorreo())
                .build();
    }

    private AlumnoResponseDTO toResponse(Alumno entity) {
        return AlumnoResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .codigo(entity.getCodigo())
                .correo(entity.getCorreo())
                .build();
    }
}