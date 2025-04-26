package eci.arcn.library.user.application.useCases;

import eci.arcn.library.shared.UseCase;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;
import eci.arcn.library.user.domain.policies.UserCanBeDeletedPolicy;

@UseCase
public class DeleteUserUseCase {
    private final UserRepository userRepository;
    private final UserCanBeDeletedPolicy userCanBeDeletedPolicy;

    public DeleteUserUseCase(UserRepository userRepository, UserCanBeDeletedPolicy userCanBeDeletedPolicy) {
        this.userRepository = userRepository;
        this.userCanBeDeletedPolicy = userCanBeDeletedPolicy;
    }

    public void execute(UserId userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!userCanBeDeletedPolicy.canBeDeleted(user)) {
            throw new IllegalStateException("Cannot delete user: has active fines or borrowed books");
        }

        userRepository.deleteById(userId);
    }
}
