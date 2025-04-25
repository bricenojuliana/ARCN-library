package eci.arcn.library.transaction.api;

import eci.arcn.library.transaction.application.RequestBookUseCase;
import eci.arcn.library.transaction.application.ReturnBookUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final RequestBookUseCase requestBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;

    public TransactionController(RequestBookUseCase requestBookUseCase, ReturnBookUseCase returnBookUseCase) {
        this.requestBookUseCase = requestBookUseCase;
        this.returnBookUseCase = returnBookUseCase;
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestBook(@RequestParam String bookId, @RequestParam String userId) {
        requestBookUseCase.execute(bookId, userId);
        return ResponseEntity.ok("Libro solicitado con éxito.");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam String transactionId) {
        returnBookUseCase.execute(transactionId);
        return ResponseEntity.ok("Libro devuelto con éxito.");
    }
}