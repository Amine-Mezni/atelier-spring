package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence agence);
    List<Agence> getAllAgences();
    Agence getAgenceById(Long idAgence);
    Agence modifierAgence(Long idAgence, Agence agence);
    void supprimerAgence(Long idAgence);
    List<Agence> getAgencesParVille(String ville);
}
