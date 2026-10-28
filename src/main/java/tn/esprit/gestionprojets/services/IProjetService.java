package tn.esprit.gestionprojets.services;

import tn.esprit.gestionprojets.entities.Projet;
import java.util.List;

public interface IProjetService {
    List<Projet> retrieveAllProjets();
    Projet retrieveProjet(Long id);
    Projet addProjet(Projet projet);
    Projet updateProjet(Projet projet);
    void removeProjet(Long id);
}
