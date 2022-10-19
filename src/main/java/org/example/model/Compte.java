package org.example.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.example.model.Operation.*;

public class Compte {
    private final String uuid = UUID.randomUUID().toString();
    private final List<Transaction> transactions = new ArrayList<>();
    private int solde;


    public Compte(int montant) {
        this.solde = montant;
        var transaction = new Transaction(CREATION, montant);
        this.transactions.add(transaction);
    }

    public int Deposer(int montant) {
        plus(montant);
        transactions.add(new Transaction(DEPOT, montant, solde));
        return solde;
    }

    public int Retirer(int montant) {
        if (solde < montant) {
            throw new IllegalStateException(String.format("Impossible de retirer [%d] : Solde insuffisant  [%d] ", montant, solde));
        }

        moins(montant);
        transactions.add(new Transaction(RETRAIT, montant, solde));
        return solde;
    }

    public String Historique() {
        return String.format("Compte %s Historique : %n%n%s", uuid,
                transactions.stream()
                        .map(Transaction::toString)
                        .collect(Collectors.joining("\n")));
    }

    private void moins(int montant) {
        this.solde -= montant;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "compte{" +
                "solde=" + solde +
                ", transactions=" + transactions +
                '}';
    }


    private void plus(int montant) {
        this.solde += montant;
    }

    public int getSolde() {
        return solde;
    }
}


