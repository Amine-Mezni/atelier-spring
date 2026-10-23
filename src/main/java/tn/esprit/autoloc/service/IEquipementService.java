package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    List<Equipement> getAllEquipements();
    Equipement getEquipementById(Long idEquipement);
    Equipement modifierEquipement(Long idEquipement, Equipement equipement);
    void supprimerEquipement(Long idEquipement);
}
