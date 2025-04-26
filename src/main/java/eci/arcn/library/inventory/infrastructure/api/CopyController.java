package eci.arcn.library.inventory.infrastructure.api;

import eci.arcn.library.inventory.application.useCases.RegisterBookCopyUseCase;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.copy.BarCode;
import eci.arcn.library.inventory.domain.copy.Copy;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/copies")
public class CopyController {
    private RegisterBookCopyUseCase registerBookCopyUseCase;

    public CopyController(RegisterBookCopyUseCase registerBookCopyUseCase) {
        this.registerBookCopyUseCase = registerBookCopyUseCase;
    }

    @PostMapping
    public Copy registerBookCopy(@RequestBody CopyDTO copyDTO) {
        return registerBookCopyUseCase.execute(new BookId(copyDTO.getBookId()), new BarCode(copyDTO.getBarCode()));
    }
}
