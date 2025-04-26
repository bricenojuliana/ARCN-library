package eci.arcn.library.fine.domain;

import lombok.Getter;

@Getter
public class Fine {
    private FineId id;
    private String userId;
    private String amount;
    private String dueDate;
    private String book;
    private boolean delayed;
    private boolean paid;

    public Fine(FineId id,String userId, String amount, String dueDate, String book, boolean delayed) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.book = book;
        this.delayed = delayed;
        this.paid = false;
    }

    public Fine(String userId, String amount, String dueDate) {
        this.id = new FineId();
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.book = "";
        this.delayed = false;
        this.paid = false;
    }
    

    public void markAsPaid() {
        this.paid = true;
    }

    public String getBook() {
        return this.book;
    }

    public boolean isDelayed() {
        return this.delayed;
    }
}

