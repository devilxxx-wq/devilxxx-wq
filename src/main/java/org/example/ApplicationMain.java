package org.example;


import org.example.model.Compte;
import org.example.services.CompteService;

public class ApplicationMain {
    public static void main(String[] args) {
        CompteService compteService = new CompteService();

        Compte compte = compteService.CreerCompte(60);

        compteService.Deposer(compte, 200);
        compteService.Retirer(compte, 100);
        compteService.Deposer(compte, 500);
        compteService.Deposer(compte, 30);

        System.out.println(compteService.Afficher(compte));
    }
}