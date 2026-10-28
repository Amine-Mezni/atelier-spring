package tn.esprit.gestionprojets.services;

import tn.esprit.gestionprojets.entities.Equipe;
import java.util.List;

public interface IEquipeService {
    List<Equipe> retrieveAllEquipes();
    Equipe retrieveEquipe(Long id);
    Equipe addEquipe(Equipe entity);
    Equipe updateEquipe(Equipe entity);
    void removeEquipe(Long id);
}
