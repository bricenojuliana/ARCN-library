package eci.arcn.library.fine.application;

import java.util.HashSet;
import java.util.Set;

public class BlacklistService {

    private final Set<String> blacklist = new HashSet<>();

    public void addToBlacklist(String userId) {
        blacklist.add(userId);
    }

    public void removeFromBlacklist(String userId) {
        blacklist.remove(userId);
    }

    public boolean isBlacklisted(String userId) {
        return blacklist.contains(userId);
    }
}
