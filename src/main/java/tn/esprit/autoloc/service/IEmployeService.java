package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    List<Employe> getAllEmployes();
    Employe getEmployeById(Long idEmploye);
    Employe modifierEmploye(Long idEmploye, Employe employe);
    void supprimerEmploye(Long idEmploye);
    List<Employe> getEmployesParAgence(Long idAgence);
}
