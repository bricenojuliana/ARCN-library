package eci.arcn.library.user.application.useCases;

import eci.arcn.library.UseCase;
import eci.arcn.library.user.domain.Email;
import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;
import org.springframework.util.Assert;

@UseCase
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserId userId, String newName, String newEmail) {
        Assert.notNull(userId, "UserId must not be null");
        Assert.hasText(newName, "New name must not be empty");
        Assert.hasText(newEmail, "New email must not be empty");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.updateInformation(newName, new Email(newEmail));
        userRepository.save(user);
    }
}
