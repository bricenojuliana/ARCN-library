package eci.arcn.library.fine.infrastructure;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;
import eci.arcn.library.fine.domain.FineRepository;


@Repository
public class JpaFineRepository implements FineRepository{

    private final FineEntytyRepository fineEntytyRepository;

    public JpaFineRepository(FineEntytyRepository fineEntytyRepository) {
        this.fineEntytyRepository = fineEntytyRepository;
    }

    @Override
    public Fine save(Fine fine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Fine findById(FineId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Iterable<Fine> findByUserId(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }

    @Override
    public void deleteById(FineId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    

}
