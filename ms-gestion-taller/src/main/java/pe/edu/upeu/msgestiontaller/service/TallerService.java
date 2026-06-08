package pe.edu.upeu.msgestiontaller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.msgestiontaller.client.AlumnoClient;
import pe.edu.upeu.msgestiontaller.client.InstructorClient;
import pe.edu.upeu.msgestiontaller.dto.request.TallerRequestDTO;
import pe.edu.upeu.msgestiontaller.dto.response.AlumnoDTO;
import pe.edu.upeu.msgestiontaller.dto.response.InstructorDTO;
import pe.edu.upeu.msgestiontaller.dto.response.TallerDetalleResponseDTO;
import pe.edu.upeu.msgestiontaller.dto.response.TallerResponseDTO;
import pe.edu.upeu.msgestiontaller.entity.Taller;
import pe.edu.upeu.msgestiontaller.exception.TallerNotFoundException;
import pe.edu.upeu.msgestiontaller.repository.TallerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;
    private final InstructorClient instructorClient;
    private final AlumnoClient alumnoClient;

    @Transactional
    public TallerResponseDTO create(TallerRequestDTO dto) {
        Taller taller = Taller.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .instructorId(dto.getInstructorId())
                .alumnoId(dto.getAlumnoId())
                .build();

        return toResponse(tallerRepository.save(taller));
    }

    @Transactional(readOnly = true)
    public TallerResponseDTO getById(Long id) {
        return toResponse(getEntity(id));
    }

    @Transactional(readOnly = true)
    public List<TallerResponseDTO> getAll() {
        return tallerRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public TallerResponseDTO update(Long id, TallerRequestDTO dto) {
        Taller taller = getEntity(id);
        taller.setNombre(dto.getNombre());
        taller.setDescripcion(dto.getDescripcion());
        taller.setInstructorId(dto.getInstructorId());
        taller.setAlumnoId(dto.getAlumnoId());
        return toResponse(tallerRepository.save(taller));
    }

    @Transactional
    public void delete(Long id) {
        if (!tallerRepository.existsById(id)) {
            throw new TallerNotFoundException("Taller no encontrado: " + id);
        }
        tallerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TallerDetalleResponseDTO getDetalle(Long id) {
        Taller taller = getEntity(id);
        InstructorDTO instructor = instructorClient.getInstructorById(taller.getInstructorId());
        AlumnoDTO alumno = alumnoClient.getAlumnoById(taller.getAlumnoId());

        return TallerDetalleResponseDTO.builder()
                .id(taller.getId())
                .nombre(taller.getNombre())
                .descripcion(taller.getDescripcion())
                .instructor(instructor)
                .alumno(alumno)
                .build();
    }

    private Taller getEntity(Long id) {
        return tallerRepository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException("Taller no encontrado: " + id));
    }

    private TallerResponseDTO toResponse(Taller entity) {
        return TallerResponseDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .instructorId(entity.getInstructorId())
                .alumnoId(entity.getAlumnoId())
                .build();
    }
}