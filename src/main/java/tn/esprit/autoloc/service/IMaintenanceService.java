package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance planifierMaintenance(Maintenance maintenance);
    List<Maintenance> getAllMaintenances();
    Maintenance getMaintenanceById(Long idMaintenance);
    Maintenance modifierMaintenance(Long idMaintenance, Maintenance maintenance);
    void supprimerMaintenance(Long idMaintenance);
    List<Maintenance> getMaintenancesParVehicule(Long idVehicule);
}
