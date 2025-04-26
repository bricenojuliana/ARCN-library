package eci.arcn.library.user.infrastructure.api;

import eci.arcn.library.user.application.useCases.DeleteUserUseCase;
import eci.arcn.library.user.application.useCases.RegisterNewUserUseCase;
import eci.arcn.library.user.application.useCases.UpdateUserUseCase;
import eci.arcn.library.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/users")
public class UserController {
    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Autowired
    public UserController(RegisterNewUserUseCase registerNewUserUseCase, DeleteUserUseCase deleteUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.registerNewUserUseCase = registerNewUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping
    public void registerUser(@RequestParam String id, @RequestParam String name, @RequestParam String email) {
        registerNewUserUseCase.execute(id, name, email);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        UserId userId = new UserId(id);
        deleteUserUseCase.execute(userId);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestParam String name, @RequestParam String email) {
        UserId userId = new UserId(id);
        updateUserUseCase.execute(userId, name, email);
    }

}
