package eci.arcn.library.transaction;


import java.util.UUID;

public record LoanClosed(UUID transactionId, UUID copyId, String userId) {
}
