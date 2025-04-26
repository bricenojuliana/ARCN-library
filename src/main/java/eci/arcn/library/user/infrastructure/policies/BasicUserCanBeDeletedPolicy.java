package eci.arcn.library.user.infrastructure.policies;

import eci.arcn.library.user.domain.User;
import eci.arcn.library.user.domain.policies.UserCanBeDeletedPolicy;
import org.springframework.stereotype.Component;

@Component
public class BasicUserCanBeDeletedPolicy implements UserCanBeDeletedPolicy {
    @Override
    public boolean canBeDeleted(User user) {
        // Por ahora, solo validamos que no tenga multas
        return !user.hasActiveFines();
    }
}
