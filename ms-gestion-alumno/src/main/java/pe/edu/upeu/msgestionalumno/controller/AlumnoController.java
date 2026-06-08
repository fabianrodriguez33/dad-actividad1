package pe.edu.upeu.msgestionalumno.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msgestionalumno.dto.request.AlumnoRequestDTO;
import pe.edu.upeu.msgestionalumno.dto.response.AlumnoResponseDTO;
import pe.edu.upeu.msgestionalumno.service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> create(@Valid @RequestBody AlumnoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<AlumnoResponseDTO>> getAll() {
        return ResponseEntity.ok(alumnoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody AlumnoRequestDTO dto) {
        return ResponseEntity.ok(alumnoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}