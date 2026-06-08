package pe.edu.upeu.msgestioninstructor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msgestioninstructor.dto.InstructorRequestDTO;
import pe.edu.upeu.msgestioninstructor.dto.InstructorResponseDTO;
import pe.edu.upeu.msgestioninstructor.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<InstructorResponseDTO> create(@Valid @RequestBody InstructorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAll() {
        return ResponseEntity.ok(instructorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponseDTO> update(@PathVariable Long id,
                                                        @Valid @RequestBody InstructorRequestDTO dto) {
        return ResponseEntity.ok(instructorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}