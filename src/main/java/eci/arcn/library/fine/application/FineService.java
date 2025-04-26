package eci.arcn.library.fine.application;

import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;
import eci.arcn.library.fine.domain.FineRepository;

public class FineService {

    private  FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public void pagarMulta(FineId id) {
        Fine multa = fineRepository.findById(id);

        if (multa.isPaid()) {
            throw new IllegalStateException("La multa ya fue pagada");
        }

      
        Fine multaPagada = new Fine(
                multa.getId(),
                multa.getUserId(),
                multa.getAmount(),
                multa.getDueDate(),
                multa.getBook(),
                multa.isDelayed(),
                true // pagada
        );

        fineRepository.save(multaPagada);
    }
}
