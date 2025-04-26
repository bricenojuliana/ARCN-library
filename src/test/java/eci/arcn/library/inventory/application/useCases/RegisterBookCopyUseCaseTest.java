package eci.arcn.library.inventory.application.useCases;

import eci.arcn.library.inventory.application.useCases.RegisterBookCopyUseCase;
import eci.arcn.library.inventory.domain.book.BookId;
import eci.arcn.library.inventory.domain.copy.BarCode;
import eci.arcn.library.inventory.domain.copy.Copy;
import eci.arcn.library.inventory.domain.copy.CopyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterBookCopyUseCaseTest {

    private CopyRepository copyRepository;
    private RegisterBookCopyUseCase useCase;

    @BeforeEach
    void setUp() {
        copyRepository = mock(CopyRepository.class);
        useCase = new RegisterBookCopyUseCase(copyRepository);
    }

    @Test
    void shouldRegisterNewBookCopy() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        BookId bookId = new BookId(uuid);
        BarCode barCode = new BarCode("BARCODE-123");

        // Act
        useCase.execute(bookId, barCode);

        // Assert
        ArgumentCaptor<Copy> captor = ArgumentCaptor.forClass(Copy.class);
        verify(copyRepository, times(1)).save(captor.capture());

        Copy savedCopy = captor.getValue();
        assertEquals(bookId, savedCopy.getBookId());
        assertEquals(barCode, savedCopy.getBarCode());
    }
}

