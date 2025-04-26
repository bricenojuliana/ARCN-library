package eci.arcn.library.fine.application;



import org.springframework.stereotype.Service;

import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineRepository;

@Service
public class GenerateFineUseCase {

    private final FineRepository fineRepository;

    public GenerateFineUseCase(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public void execute(String userId, String amount, String dueDate) {

        Fine fine = new Fine(userId, amount, dueDate);

        fineRepository.save(fine);
    }
}
