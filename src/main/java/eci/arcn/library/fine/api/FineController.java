package eci.arcn.library.fine.api;

import eci.arcn.library.fine.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineRepository fineRepository;

    public FineController(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody FineRequest request) {
        Fine fine = new Fine(
                new FineId(),
                request.getUserId(),
                request.getAmount(),
                request.getDueDate(),
                request.getBook(),
                request.isDelayed()
        );
        return ResponseEntity.ok(fineRepository.save(fine));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fine> getFineById(@PathVariable UUID id) {
        return ResponseEntity.ok(fineRepository.findById(new FineId(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Fine>> getFinesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(fineRepository.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFine(@PathVariable UUID id) {
        fineRepository.deleteById(new FineId(id));
        return ResponseEntity.noContent().build();
    }
}
