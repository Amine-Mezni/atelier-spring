package tn.esprit.autoloc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final EquipementRepository equipementRepository;
    private final EmployeRepository employeRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final ContratRepository contratRepository;
    private final PaiementRepository paiementRepository;
    private final MaintenanceRepository maintenanceRepository;

    @Override
    public void run(String... args) {
        if (agenceRepository.count() > 0) {
            log.info("Données déjà présentes en base de données. Initialisation ignorée.");
            return;
        }

        log.info("Initialisation des données de test AutoLoc...");

        // 1. Agences
        Agence agenceTunis = new Agence(null, "Agence Tunis Centre", "Tunis", "Avenue Habib Bourguiba", "+216 71 100 200", null, null);
        Agence agenceSousse = new Agence(null, "Agence Sousse Plage", "Sousse", "Boulevard 14 Janvier", "+216 73 300 400", null, null);
        agenceTunis = agenceRepository.save(agenceTunis);
        agenceSousse = agenceRepository.save(agenceSousse);

        // 2. Employés
        Employe emp1 = new Employe(null, "Trabelsi", "Anis", RoleEmploye.MANAGER, agenceTunis);
        Employe emp2 = new Employe(null, "Gharbi", "Sarra", RoleEmploye.AGENT, agenceTunis);
        employeRepository.save(emp1);
        employeRepository.save(emp2);

        // 3. Équipements
        Equipement eqGps = equipementRepository.save(new Equipement(null, "GPS Haute Précision", null));
        Equipement eqSiege = equipementRepository.save(new Equipement(null, "Siège Bébé Confort", null));
        Equipement eqCoffre = equipementRepository.save(new Equipement(null, "Coffre de Toit 400L", null));

        // 4. Véhicules
        Vehicule v1 = new Vehicule(null, "220-TN-1001", "Volkswagen", "Golf 8", CategorieVehicule.CITADINE,
                new BigDecimal("120.00"), StatutVehicule.DISPONIBLE, agenceTunis, new ArrayList<>(List.of(eqGps)), null, null);
        Vehicule v2 = new Vehicule(null, "221-TN-2002", "Peugeot", "3008", CategorieVehicule.SUV,
                new BigDecimal("180.00"), StatutVehicule.DISPONIBLE, agenceTunis, new ArrayList<>(List.of(eqGps, eqCoffre)), null, null);
        Vehicule v3 = new Vehicule(null, "222-TN-3003", "Mercedes-Benz", "Classe C", CategorieVehicule.BERLINE,
                new BigDecimal("260.00"), StatutVehicule.DISPONIBLE, agenceSousse, new ArrayList<>(List.of(eqGps, eqSiege)), null, null);
        v1 = vehiculeRepository.save(v1);
        v2 = vehiculeRepository.save(v2);
        v3 = vehiculeRepository.save(v3);

        // 5. Client
        Client client = new Client(null, "Ben Salah", "Mohamed", "mohamed.bensalah@email.com",
                "+216 22 111 222", "TN-987654321", LocalDate.now().minusMonths(2), null);
        client = clientRepository.save(client);

        // 6. Réservation
        Reservation reservation = new Reservation(null, LocalDate.now().plusDays(2), LocalDate.now().plusDays(5),
                StatutReservation.CONFIRMEE, client, v1, null);
        reservation = reservationRepository.save(reservation);

        // 7. Contrat
        Contrat contrat = new Contrat(null, LocalDate.now(), new BigDecimal("360.00"), true, reservation, null);
        contrat = contratRepository.save(contrat);

        // 8. Paiement
        Paiement paiement = new Paiement(null, new BigDecimal("360.00"), LocalDate.now(), ModePaiement.CARTE, contrat);
        paiementRepository.save(paiement);

        // 9. Maintenance
        Maintenance maintenance = new Maintenance(null, LocalDate.now().minusWeeks(1), LocalDate.now().minusDays(5),
                "Vidange et révision générale des 30 000 km", v2);
        maintenanceRepository.save(maintenance);

        log.info("Données de test initialisées avec succès pour AutoLoc.");
    }
}
