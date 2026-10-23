package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat creerContrat(Contrat contrat);
    List<Contrat> getAllContrats();
    Contrat getContratById(Long idContrat);
    Contrat modifierContrat(Long idContrat, Contrat contrat);
    void supprimerContrat(Long idContrat);
}
