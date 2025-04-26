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
        return userEntityRepository.findById(id.id()).map(this::toDomain);
    }


    @Override
    public void deleteById(UserId id) {
        userEntityRepository.deleteById(id.id());
    }

    @Override
    public Iterable<User> findAll() {
        return StreamSupport.stream(userEntityRepository.findAll().spliterator(), false)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<User> findByEmail(Email email) {
        return userEntityRepository.findByEmail(email.email()).map(this::toDomain);
    }


    @Override
    public Optional<User> findByName(String name) {
        return userEntityRepository.findByName(name).map(this::toDomain);
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

    private User toDomain(UserEntity entity) {
        User user = new User(
                new UserId(entity.getId()),
                entity.getName(),
                new Email(entity.getEmail()),
                entity.isActive(),
                entity.isEmailVerified(),
                entity.getRegisteredAt()
        );

        // Asignar borrowedBooks manualmente
        if (entity.getBorrowedBooks() != null) {
            for (UUID bookId : entity.getBorrowedBooks()) {
                user.borrowBook(bookId);
            }
        }
        return user;
    }

}
