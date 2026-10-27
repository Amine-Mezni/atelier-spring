package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Équipements", description = "Gestion du catalogue des équipements optionnels")
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping
    @Operation(summary = "Ajouter un équipement au catalogue")
    public ResponseEntity<Equipement> ajouterEquipement(@RequestBody Equipement equipement) {
        Equipement nouveau = equipementService.ajouterEquipement(equipement);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lister tous les équipements disponibles")
    public ResponseEntity<List<Equipement>> getAllEquipements() {
        return ResponseEntity.ok(equipementService.getAllEquipements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un équipement par son identifiant")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.getEquipementById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier le libellé d'un équipement")
    public ResponseEntity<Equipement> modifierEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.modifierEquipement(id, equipement));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un équipement du catalogue")
    public ResponseEntity<Void> supprimerEquipement(@PathVariable Long id) {
        equipementService.supprimerEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
