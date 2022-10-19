package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private Operation operation;
    private LocalDateTime date;
    private int montant;
    private int solde;

    public Transaction(Operation operation, int montant) {
        this.date = LocalDateTime.now();
        this.operation = operation;
        this.montant = montant;
        this.solde = montant;

    }

    public Transaction(Operation operation, int montant, int solde) {
        this.operation = operation;
        this.solde = solde;
        this.montant = montant;
        this.date = LocalDateTime.now();

    }

    public Operation getOperation() {
        return operation;
    }

    public int getMontant() {
        return montant;
    }

    public int getSolde() {
        return solde;
    }


    @Override
    public String toString() {
        return operation +
                ", " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) +
                ", montant=" + montant +
                ", solde=" + solde;
    }
}

