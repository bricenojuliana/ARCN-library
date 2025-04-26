package eci.arcn.library.user.infrastructure;

import eci.arcn.library.user.domain.Email;
import eci.arcn.library.user.domain.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;
    private boolean active;
    private boolean emailVerified;
    private LocalDateTime registeredAt;

    public UserEntity() {}
}
