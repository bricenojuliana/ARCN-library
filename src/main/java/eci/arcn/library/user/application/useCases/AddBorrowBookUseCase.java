package eci.arcn.library.user.application.useCases;

import eci.arcn.library.shared.UseCase;
import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;

import java.util.UUID;

@UseCase
public class AddBorrowBookUseCase {
    private UserRepository userRepository;

    public void execute(UserId userId, UUID transactionId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.borrowBook(transactionId);
        userRepository.save(user);
    }
}
