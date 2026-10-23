package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.MaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance planifierMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance getMaintenanceById(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new RuntimeException("Maintenance non trouvée avec l'identifiant : " + idMaintenance));
    }

    @Override
    public Maintenance modifierMaintenance(Long idMaintenance, Maintenance maintenance) {
        Maintenance existante = getMaintenanceById(idMaintenance);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        if (maintenance.getVehicule() != null) {
            existante.setVehicule(maintenance.getVehicule());
        }
        return maintenanceRepository.save(existante);
    }

    @Override
    public void supprimerMaintenance(Long idMaintenance) {
        if (!maintenanceRepository.existsById(idMaintenance)) {
            throw new RuntimeException("Impossible de supprimer : Maintenance non trouvée avec l'identifiant : " + idMaintenance);
        }
        maintenanceRepository.deleteById(idMaintenance);
    }

    @Override
    public List<Maintenance> getMaintenancesParVehicule(Long idVehicule) {
        return maintenanceRepository.findByVehiculeIdVehicule(idVehicule);
    }
}
