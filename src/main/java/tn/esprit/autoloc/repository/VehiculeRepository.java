package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findByImmatriculation(String immatriculation);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByCategorie(CategorieVehicule categorie);
    List<Vehicule> findByAgenceIdAgence(Long idAgence);
    List<Vehicule> findByAgenceVilleAndStatut(String ville, StatutVehicule statut);
}
