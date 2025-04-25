package eci.arcn.library.inventory.application.useCases;

import eci.arcn.library.UseCase;
import eci.arcn.library.inventory.domain.copy.CopyId;
import eci.arcn.library.inventory.domain.copy.CopyRepository;

@UseCase
public class DeleteBookCopyFromInventoryUseCase {
    private final CopyRepository copyRepository;

    public DeleteBookCopyFromInventoryUseCase(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    public void execute(CopyId copyId) {
        copyRepository.deleteById(copyId);
    }
}
