package org.example.test;

import org.assertj.core.groups.Tuple;
import org.example.model.Compte;
import org.example.model.Operation;
import org.example.model.Transaction;
import org.example.services.CompteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompteServiceTest {

    @Test
    void creerCompteAvecMontantInitial() {

        int montantInitial = 30;

        var compte = new CompteService().CreerCompte(montantInitial);

        assertThat(compte.getSolde()).isEqualTo(30);
        assertThat(compte.getTransactions()).hasSize(1);
        assertThat(compte.getTransactions()).extracting(Transaction::getMontant, Transaction::getSolde, Transaction::getOperation)
                .containsExactly(Tuple.tuple(30, 30, Operation.CREATION));
    }

    @Test
    void incrementerCompteDe100EuroQuandDepot100Euro() {

        Compte compte = new Compte(200);
        int montantDepot = 100;

        var montantFinal = new CompteService().Deposer(compte, montantDepot);

        assertThat(montantFinal).isEqualTo(300);
    }

    @Test
    void decrementerCompteDe200EuroQuandDepot200Euro() {

        Compte compte = new Compte(300);
        int montantRetrait = 200;

        var montantFinal = new CompteService().Retirer(compte, montantRetrait);

        assertThat(montantFinal).isEqualTo(100);
    }

    @Test
    void afficherHistoriqueQuandClientConsulteOperations() {

        CompteService compteService = new CompteService();
        Compte compte = compteService.CreerCompte(400);

        compteService.Deposer(compte, 400);
        compteService.Deposer(compte, 40);
        compteService.Retirer(compte, 60);
        compteService.Deposer(compte, 150);

        assertThat(compte.getTransactions()).extracting(Transaction::getMontant, Transaction::getSolde, Transaction::getOperation)
                .containsExactly(
                        Tuple.tuple(400, 400, Operation.CREATION),
                        Tuple.tuple(400, 800, Operation.DEPOT),
                        Tuple.tuple(40, 840, Operation.DEPOT),
                        Tuple.tuple(60, 780, Operation.RETRAIT),
                        Tuple.tuple(150, 930, Operation.DEPOT));
    }

    @Test
    void ThrowIllegalExceptionQuandFondInsuffisant() {

        CompteService CompteService = new CompteService();
        var compte = CompteService.CreerCompte(110);


        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> compte.Retirer(120));


        Assertions.assertEquals(String.format("Impossible de retirer [%d] : Solde insuffisant  [%d] ", 120, 110), thrown.getMessage());
    }

}
