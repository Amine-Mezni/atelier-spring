package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    List<Vehicule> getAllVehicules();
    Vehicule getVehiculeById(Long idVehicule);
    Vehicule modifierVehicule(Long idVehicule, Vehicule vehicule);
    void supprimerVehicule(Long idVehicule);
    List<Vehicule> getVehiculesDisponibles();
    List<Vehicule> getVehiculesParCategorie(CategorieVehicule categorie);
    List<Vehicule> getVehiculesParAgence(Long idAgence);
}
