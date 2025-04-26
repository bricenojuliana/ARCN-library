package eci.arcn.library.user.application;

import eci.arcn.library.transaction.LoanClosed;
import eci.arcn.library.transaction.LoanCreated;
import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEventListener {
    private UserRepository userRepository;

    public UserEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApplicationModuleListener
    public void handle(LoanCreated event) {
        Optional<User> user = userRepository.findById(new UserId(event.userID()));
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.borrowBook(event.transactionId());
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @ApplicationModuleListener
    public void handle(LoanClosed event) {
        Optional<User> user = userRepository.findById(new UserId(event.userId()));
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.returnBook(event.transactionId());
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
