package eci.arcn.library.transaction.infrastructure;


import eci.arcn.library.transaction.domain.Transaction;
import eci.arcn.library.transaction.domain.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserRestrictionScheduler {

    private final TransactionRepository transactionRepository;

    @Autowired
    public UserRestrictionScheduler( TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Se ejecuta todos los días a las 00:00 AM
    @Scheduled(cron = "0 00 00 * * ?")
    public void checkUserRestrictions() {
        System.out.println("Iniciando verificación de restricciones de usuarios...");

        List<Transaction> allTransactions = transactionRepository.findAll();
        for (Transaction transaction : allTransactions) {
            boolean hasOverdueBooks = transactionRepository.hasOverdueBooks(transaction.getTransactionId(), LocalDate.now());

            if (hasOverdueBooks) {
                transaction.setExpiration(true);
                transactionRepository.update(transaction);
                System.out.println("Transacción restringiendo al usuario: " + transaction.getUserId());
            }
        }

        System.out.println("Verificación de restricciones completada.");
    }
}
