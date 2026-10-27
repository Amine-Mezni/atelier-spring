package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Maintenances", description = "Suivi des opérations d'entretien et réparations des véhicules")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    @Operation(summary = "Planifier une opération de maintenance")
    public ResponseEntity<Maintenance> planifierMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance nouvelle = maintenanceService.planifierMaintenance(maintenance);
        return new ResponseEntity<>(nouvelle, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lister l'historique de toutes les maintenances")
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenances());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter le détail d'une maintenance")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @GetMapping("/vehicule/{idVehicule}")
    @Operation(summary = "Consulter le carnet d'entretien d'un véhicule")
    public ResponseEntity<List<Maintenance>> getMaintenancesParVehicule(@PathVariable Long idVehicule) {
        return ResponseEntity.ok(maintenanceService.getMaintenancesParVehicule(idVehicule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour les données d'une maintenance")
    public ResponseEntity<Maintenance> modifierMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.modifierMaintenance(id, maintenance));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un enregistrement de maintenance")
    public ResponseEntity<Void> supprimerMaintenance(@PathVariable Long id) {
        maintenanceService.supprimerMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}
