package eci.arcn.library.inventory.application;

import eci.arcn.library.inventory.domain.copy.Copy;
import eci.arcn.library.inventory.domain.copy.CopyId;
import eci.arcn.library.inventory.domain.copy.CopyRepository;
import eci.arcn.library.transaction.LoanClosed;
import eci.arcn.library.transaction.LoanCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventListener {
    private final CopyRepository copyRepository;

    public InventoryEventListener(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @ApplicationModuleListener
    public void handle(LoanCreated event) {
        Copy copy = copyRepository.findById(new CopyId(event.copyId()));
        copy.makeUnavailable();
        copyRepository.save(copy);
    }

    @ApplicationModuleListener
    public void handle(LoanClosed event) {
        Copy copy = copyRepository.findById(new CopyId(event.copyId()));
        copy.makeAvailable();
        copyRepository.save(copy);
    }

}
