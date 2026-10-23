package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement enregistrerPaiement(Paiement paiement);
    List<Paiement> getAllPaiements();
    Paiement getPaiementById(Long idPaiement);
    void supprimerPaiement(Long idPaiement);
    List<Paiement> getPaiementsParContrat(Long idContrat);
}
