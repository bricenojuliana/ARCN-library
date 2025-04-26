package eci.arcn.library.fine.application;

import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;
import eci.arcn.library.fine.domain.FineRepository;

public class PayFineUseCase {

    private final FineRepository fineRepository;
    private final BlacklistService blacklistService;

    public PayFineUseCase(FineRepository fineRepository, BlacklistService blacklistService) {
        this.fineRepository = fineRepository;
        this.blacklistService = blacklistService;
    }

    public void execute(FineId fineId, String userId) {
        Fine fine = fineRepository.findById(fineId);
        fine.markAsPaid();
        fineRepository.save(fine);

        boolean allFinesPaid = true;
        for (Fine f : fineRepository.findByUserId(userId)) {
            if (!f.isPaid()) {
                allFinesPaid = false;
                break;
            }
        }
        if (allFinesPaid) {
            blacklistService.removeFromBlacklist(userId);
        }

        if (userId.isEmpty()) {
            throw new IllegalArgumentException("Fine not found");
        }

        
    }


}
