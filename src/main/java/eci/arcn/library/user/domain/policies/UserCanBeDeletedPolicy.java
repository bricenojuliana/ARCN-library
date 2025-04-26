package eci.arcn.library.user.domain.policies;

import eci.arcn.library.user.domain.User;

public interface UserCanBeDeletedPolicy {
    boolean canBeDeleted(User user);
}
