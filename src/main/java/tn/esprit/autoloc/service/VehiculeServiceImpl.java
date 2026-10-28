package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.journalisation.JournalisationService;
import tn.esprit.autoloc.service.tarification.TarificationService;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

import java.util.List;

/**
 * Service métier pour la gestion des véhicules.
 * Refactorisé pour respecter :
 * - SRP : délégation de la validation à VehiculeValidator et des logs à JournalisationService
 * - DIP : dépendance vers des interfaces / contrats injectés par constructeur
 * - OCP / Strategy : délégation du calcul tarifaire à TarificationService
 */
@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;
    private final TarificationService tarificationService;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        // 1. Validation métier isolée (SRP)
        vehiculeValidator.valider(vehicule);

        // 2. Journalisation centralisée (SRP)
        journalisationService.journaliserAction("AJOUT", "Vehicule", vehicule.getImmatriculation());

        // 3. Persistance
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
        vehiculeValidator.valider(vehicule);
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
        journalisationService.journaliserAction("MODIFICATION", "Vehicule", idVehicule);
        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimerVehicule(Long idVehicule) {
        if (!vehiculeRepository.existsById(idVehicule)) {
            throw new RuntimeException("Impossible de supprimer : Véhicule non trouvé avec l'identifiant : " + idVehicule);
        }
        journalisationService.journaliserAction("SUPPRESSION", "Vehicule", idVehicule);
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

    @Override
    public double calculerTarif(Long idVehicule, int jours, String typePolitique) {
        Vehicule vehicule = getVehiculeById(idVehicule);
        return tarificationService.calculerPrix(typePolitique, vehicule.getTarifJournalier().doubleValue(), jours);
    }
}
