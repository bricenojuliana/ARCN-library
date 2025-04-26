package eci.arcn.Fine.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import eci.arcn.library.fine.application.BlacklistService;
import eci.arcn.library.fine.application.FineService;
import eci.arcn.library.fine.application.GenerateFineUseCase;
import eci.arcn.library.fine.application.PayFineUseCase;
import eci.arcn.library.fine.domain.Fine;
import eci.arcn.library.fine.domain.FineId;
import eci.arcn.library.fine.domain.FineRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FineServiceTests {

    private FineRepository fineRepository;
    private BlacklistService blacklistService;
    private FineService fineService;
    private GenerateFineUseCase generateFineUseCase;
    private PayFineUseCase payFineUseCase;

    @BeforeEach
    public void setup() {
        fineRepository = mock(FineRepository.class);
        blacklistService = new BlacklistService(); // real instance
        fineService = new FineService(fineRepository);
        generateFineUseCase = new GenerateFineUseCase(fineRepository);
        payFineUseCase = new PayFineUseCase(fineRepository, blacklistService);
    }

    @Test
    public void testGenerarMulta() {
        // Arrange
        String userId = "user123";
        String amount = "5000";
        String dueDate = "2025-05-01";

        // Act
        generateFineUseCase.execute(userId, amount, dueDate);

        // Assert
        ArgumentCaptor<Fine> captor = ArgumentCaptor.forClass(Fine.class);
        verify(fineRepository, times(1)).save(captor.capture());

        Fine savedFine = captor.getValue();
        assertEquals(userId, savedFine.getUserId());
        assertEquals(amount, savedFine.getAmount());
        assertEquals(dueDate, savedFine.getDueDate());
        assertFalse(savedFine.isPaid());
        assertFalse(savedFine.isDelayed());
        assertEquals("", savedFine.getBook());
    }

    @Test
    public void testPagarMulta() {
        // Arrange
        FineId fineId = new FineId(UUID.randomUUID());
        Fine fine = new Fine(fineId, "user123", "6000", "2025-05-01", "book123", false, false);

        when(fineRepository.findById(fineId)).thenReturn(fine);
        when(fineRepository.findByUserId("user123")).thenReturn(List.of(
                new Fine(fineId, "user123", "6000", "2025-05-01", "book123", false, true)
        ));

        // Act
        payFineUseCase.execute(fineId, "user123");

        // Assert
        verify(fineRepository, times(1)).save(any(Fine.class));
        assertFalse(blacklistService.isBlacklisted("user123"));
    }



    @Test
    public void testEliminarDeBlacklistCuandoTodasLasMultasEstanPagas() {
        // Arrange
        String userId = "user123";
        FineId fineId = new FineId(UUID.randomUUID());

        Fine fine = new Fine(fineId, userId, "7000", "2025-05-01", "book123", false, false);
        Fine paidFine = new Fine(new FineId(UUID.randomUUID()), userId, "3000", "2025-04-01", "book456", false, true);

        blacklistService.addToBlacklist(userId);

        when(fineRepository.findById(fineId)).thenReturn(fine);
        when(fineRepository.findByUserId(userId)).thenReturn(List.of(
                new Fine(fineId, userId, "7000", "2025-05-01", "book123", false, true),
                paidFine
        ));

        // Act
        payFineUseCase.execute(fineId, userId);

        // Assert
        assertTrue(blacklistService.isBlacklisted(userId));
    }

    @Test
    public void testSigueEnBlacklistSiTieneMultasImpagas() {
        // Arrange
        String userId = "user456";
        FineId fineId = new FineId(UUID.randomUUID());

        Fine fine1 = new Fine(fineId, userId, "5000", "2025-05-01", "bookX", false, false);
        Fine fine2 = new Fine(new FineId(UUID.randomUUID()), userId, "4000", "2025-04-01", "bookY", false, false);

        blacklistService.addToBlacklist(userId);

        when(fineRepository.findById(fineId)).thenReturn(fine1);
        when(fineRepository.findByUserId(userId)).thenReturn(List.of(
                new Fine(fineId, userId, "5000", "2025-05-01", "bookX", false, true),
                fine2
        ));

        // Act
        payFineUseCase.execute(fineId, userId);

        // Assert
        assertTrue(blacklistService.isBlacklisted(userId));
    }

    @Test
    public void testErrorCuandoUsuarioVacio() {
        // Arrange
        FineId fineId = new FineId(UUID.randomUUID());
        Fine fine = new Fine(fineId, "", "5000", "2025-05-01", "bookX", false, false);

        when(fineRepository.findById(fineId)).thenReturn(fine);
        when(fineRepository.findByUserId("")).thenReturn(List.of());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            payFineUseCase.execute(fineId, "");
        });
    }
}
