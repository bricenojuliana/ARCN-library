package eci.arcn.library.user.infrastructure;

import eci.arcn.library.user.domain.Email;
import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.UserId;
import eci.arcn.library.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class JpaUserRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;

    public JpaUserRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId().id(),
                user.getName(),
                user.getEmail().email(),
                user.isActive(),
                user.isEmailVerified(),
                user.getRegisteredAt(),
                user.getBorrowedBooks().getItems());
        userEntityRepository.save(userEntity);
        return user;
    }

    @Override
    public Optional<User> findById(UserId id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id.id());
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            return Optional.of(new User(
                    new UserId(entity.getId()),
                    entity.getName(),
                    new Email(entity.getEmail()),
                    entity.isActive(),
                    entity.isEmailVerified(),
                    entity.getRegisteredAt()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(UserId id) {
        userEntityRepository.deleteById(id.id());
    }

    @Override
    public Iterable<User> findAll() {
        Iterable<UserEntity> userEntities = userEntityRepository.findAll();
        return StreamSupport.stream(userEntities.spliterator(), false)
                .map(entity -> new User(
                        new UserId(entity.getId()),
                        entity.getName(),
                        new Email(entity.getEmail()),
                        entity.isActive(),
                        entity.isEmailVerified(),
                        entity.getRegisteredAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        Optional<UserEntity> userEntity = userEntityRepository.findByEmail(email.email());
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            return Optional.of(new User(
                    new UserId(entity.getId()),
                    entity.getName(),
                    new Email(entity.getEmail()),
                    entity.isActive(),
                    entity.isEmailVerified(),
                    entity.getRegisteredAt()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<UserEntity> userEntity = userEntityRepository.findByName(name);
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            return Optional.of(new User(
                    new UserId(entity.getId()),
                    entity.getName(),
                    new Email(entity.getEmail()),
                    entity.isActive(),
                    entity.isEmailVerified(),
                    entity.getRegisteredAt()));
        }
        return Optional.empty();
    }

    @Override
    public Boolean borrowBook(UserId userId, UUID bookId) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(userId.id());
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            entity.getBorrowedBooks().add(bookId);
            userEntityRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean returnBook(UserId userId, UUID bookId) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(userId.id());
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            entity.getBorrowedBooks().remove(bookId);
            userEntityRepository.save(entity);
            return true;
        }
        return false;
    }
}
