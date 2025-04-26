package eci.arcn.library.user.application.useCases;

import eci.arcn.library.UseCase;
import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserRepository;

@UseCase
public class RegisterNewUserUseCase {
    private final UserRepository userRepository;

    public RegisterNewUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String id, String name, String email) {
        User newUser = new User(id, name, email);
        userRepository.save(newUser);
    }
}
