package eci.arcn.library.user.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UserId id);
    void deleteById(UserId id);
    Iterable<User> findAll();
    Optional<User> findByEmail(Email email);
    Optional<User> findByName(String name);
}
