package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule getVehiculeById(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec l'identifiant : " + idVehicule));
    }

    @Override
    public Vehicule modifierVehicule(Long idVehicule, Vehicule vehicule) {
        Vehicule existant = getVehiculeById(idVehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        if (vehicule.getAgence() != null) {
            existant.setAgence(vehicule.getAgence());
        }
        if (vehicule.getEquipements() != null) {
            existant.setEquipements(vehicule.getEquipements());
        }
        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimerVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new RuntimeException("Impossible de supprimer : Véhicule non trouvé avec l'identifiant : " + idVehicule);
        }
        vehiculeRepository.deleteById(idVehicule);
    }

    @Override
    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeRepository.findByStatut(StatutVehicule.DISPONIBLE);
    }

    @Override
    public List<Vehicule> getVehiculesParCategorie(CategorieVehicule categorie) {
        return vehiculeRepository.findByCategorie(categorie);
    }

    @Override
    public List<Vehicule> getVehiculesParAgence(Long idAgence) {
        return vehiculeRepository.findByAgenceIdAgence(idAgence);
    }
}
