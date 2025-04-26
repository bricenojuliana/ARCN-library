package eci.arcn.library.inventory.application.useCases;

import eci.arcn.library.shared.UseCase;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.copy.BarCode;
import eci.arcn.library.inventory.domain.copy.Copy;
import eci.arcn.library.inventory.domain.copy.CopyRepository;

@UseCase
public class RegisterBookCopyUseCase {
    private final CopyRepository copyRepository;

    public RegisterBookCopyUseCase(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public Copy execute(BookId bookId, BarCode barCode) {
        return copyRepository.save(new Copy(bookId, barCode));
    }
}
