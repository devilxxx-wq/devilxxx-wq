package org.example.services;

import org.example.model.Compte;

public class CompteService {

    public Compte CreerCompte(int montantInitial) {
        return new Compte(montantInitial);
    }

    public int Deposer(Compte compte, int montantDepot) {
        return compte.Deposer(montantDepot);
    }

    public int Retirer(Compte compte, int montantRetrait) {
        return compte.Retirer(montantRetrait);
    }

    public String Afficher(Compte compte) {
        return compte.Historique();
    }
}
