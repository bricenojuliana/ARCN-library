package eci.arcn.library.fine.domain;


import lombok.Getter;
import org.springframework.util.Assert;
import java.time.LocalDate;

@Getter
public class Fine {
    private FineId id;
    private String userId;
    private String amount;
    private String dueDate;
    private boolean paid;

    public Fine(String userId, String amount, String dueDate) {
        Assert.notNull(userId, "UserId must not be null");
        Assert.notNull(amount, "Amount must not be null");
        Assert.notNull(dueDate, "Due date must not be null");
        this.id = new FineId();
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paid = false;
    }

    public void markAsPaid() {
        this.paid = true;
    }
    

}
