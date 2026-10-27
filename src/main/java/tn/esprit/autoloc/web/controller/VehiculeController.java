package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Véhicules", description = "Gestion du parc de véhicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    @Operation(summary = "Ajouter un véhicule au parc")
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) {
        Vehicule nouveau = vehiculeService.ajouterVehicule(vehicule);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Consulter l'ensemble de la flotte de véhicules")
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter les détails d'un véhicule")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.getVehiculeById(id));
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Lister uniquement les véhicules disponibles à la location")
    public ResponseEntity<List<Vehicule>> getVehiculesDisponibles() {
        return ResponseEntity.ok(vehiculeService.getVehiculesDisponibles());
    }

    @GetMapping("/categorie/{categorie}")
    @Operation(summary = "Filtrer les véhicules par catégorie")
    public ResponseEntity<List<Vehicule>> getVehiculesParCategorie(@PathVariable CategorieVehicule categorie) {
        return ResponseEntity.ok(vehiculeService.getVehiculesParCategorie(categorie));
    }

    @GetMapping("/agence/{idAgence}")
    @Operation(summary = "Lister les véhicules affectés à une agence")
    public ResponseEntity<List<Vehicule>> getVehiculesParAgence(@PathVariable Long idAgence) {
        return ResponseEntity.ok(vehiculeService.getVehiculesParAgence(idAgence));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier les informations d'un véhicule")
    public ResponseEntity<Vehicule> modifierVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.modifierVehicule(id, vehicule));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Retirer un véhicule du parc")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }
}
