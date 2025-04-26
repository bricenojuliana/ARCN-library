package eci.arcn.library.user.infrastructure;

import eci.arcn.library.user.domain.Email;
import eci.arcn.library.user.domain.UserId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UUID> borrowedBooks; 


    public UserEntity() {}
}
