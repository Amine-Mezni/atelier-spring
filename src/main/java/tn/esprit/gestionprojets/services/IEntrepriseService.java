package tn.esprit.gestionprojets.services;

import tn.esprit.gestionprojets.entities.Entreprise;
import java.util.List;

public interface IEntrepriseService {
    List<Entreprise> retrieveAllEntreprises();
    Entreprise retrieveEntreprise(Long id);
    Entreprise addEntreprise(Entreprise entity);
    Entreprise updateEntreprise(Entreprise entity);
    void removeEntreprise(Long id);
}
