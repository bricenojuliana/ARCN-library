package eci.arcn.library.transaction;


import java.util.UUID;

public record LoanCreated(UUID transactionId, UUID copyId, String userID) {
}
