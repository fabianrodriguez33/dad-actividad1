package pe.edu.upeu.msgestiontaller.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.msgestiontaller.dto.request.TallerRequestDTO;
import pe.edu.upeu.msgestiontaller.dto.response.TallerDetalleResponseDTO;
import pe.edu.upeu.msgestiontaller.dto.response.TallerResponseDTO;
import pe.edu.upeu.msgestiontaller.service.TallerService;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
public class TallerController {

    private final TallerService tallerService;

    @PostMapping
    public ResponseEntity<TallerResponseDTO> create(@Valid @RequestBody TallerRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tallerService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TallerResponseDTO>> getAll() {
        return ResponseEntity.ok(tallerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tallerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TallerResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody TallerRequestDTO dto) {
        return ResponseEntity.ok(tallerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tallerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<TallerDetalleResponseDTO> getDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(tallerService.getDetalle(id));
    }
}