package pe.edu.upeu.msgestioninstructor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.msgestioninstructor.dto.InstructorRequestDTO;
import pe.edu.upeu.msgestioninstructor.dto.InstructorResponseDTO;
import pe.edu.upeu.msgestioninstructor.entity.Instructor;
import pe.edu.upeu.msgestioninstructor.exception.InstructorNotFoundException;
import pe.edu.upeu.msgestioninstructor.repository.InstructorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Transactional
    public InstructorResponseDTO create(InstructorRequestDTO dto) {
        if (instructorRepository.existsByCorreo(dto.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un instructor con ese correo");
        }
        return toResponse(instructorRepository.save(toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public InstructorResponseDTO getById(Long id) {
        return toResponse(getEntity(id));
    }

    @Transactional(readOnly = true)
    public List<InstructorResponseDTO> getAll() {
        return instructorRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public InstructorResponseDTO update(Long id, InstructorRequestDTO dto) {
        Instructor instructor = getEntity(id);
        instructor.setNombre(dto.getNombre());
        instructor.setEspecialidad(dto.getEspecialidad());
        instructor.setCorreo(dto.getCorreo());
        return toResponse(instructorRepository.save(instructor));
    }

    @Transactional
    public void delete(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new InstructorNotFoundException("Instructor no encontrado: " + id);
        }
        instructorRepository.deleteById(id);
    }

    private Instructor getEntity(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor no encontrado: " + id));
    }

    private Instructor toEntity(InstructorRequestDTO dto) {
        return Instructor.builder()
                .nombre(dto.getNombre())
                .especialidad(dto.getEspecialidad())
                .correo(dto.getCorreo())
                .build();
    }

    private InstructorResponseDTO toResponse(Instructor entity) {
        return InstructorResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .especialidad(entity.getEspecialidad())
                .correo(entity.getCorreo())
                .build();
    }
}