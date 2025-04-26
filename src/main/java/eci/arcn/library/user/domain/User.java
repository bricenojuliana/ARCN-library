package eci.arcn.library.user.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class User {
    private final UserId id;
    private String name;
    private Email email;
    private boolean active;
    private boolean emailVerified;
    private LocalDateTime registeredAt;

    public User(String id, String name, String email) {
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(email, "Email must not be null");
        this.id = new UserId(id);
        this.name = name;
        this.email = new Email(email);
        this.active = true;
        this.emailVerified = false;
        this.registeredAt = LocalDateTime.now();
    }

    public User(UserId id, String name, Email email, boolean active, boolean emailVerified, LocalDateTime registeredAt) {
        Assert.notNull(id, "Id must not be null");
        Assert.notNull(name, "Name must not be null");
        Assert.notNull(email, "Email must not be null");
        Assert.notNull(registeredAt, "Registered date must not be null");
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
        this.emailVerified = emailVerified;
        this.registeredAt = registeredAt;
    }

    public void verifyEmail() {
        this.emailVerified = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updateName(String newName) {
        Assert.notNull(newName, "New name must not be null");
        this.name = newName;
    }

    public void updateInformation(String newName, Email newEmail) {
        Assert.hasText(newName, "New name must not be empty");
        Assert.notNull(newEmail, "Email must not be null");

        this.name = newName;
        this.email = newEmail;
    }

    // TODO: Implement fine management
    public boolean hasActiveFines() {
        // Placeholder for actual fine checking logic
        return false;
    }

}
