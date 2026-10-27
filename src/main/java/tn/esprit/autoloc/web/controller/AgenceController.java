package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Agences", description = "Gestion des agences du réseau AutoLoc")
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    @Operation(summary = "Ajouter une nouvelle agence")
    public ResponseEntity<Agence> ajouterAgence(@RequestBody Agence agence) {
        Agence nouvelle = agenceService.ajouterAgence(agence);
        return new ResponseEntity<>(nouvelle, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Consulter toutes les agences")
    public ResponseEntity<List<Agence>> getAllAgences() {
        return ResponseEntity.ok(agenceService.getAllAgences());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une agence par son identifiant")
    public ResponseEntity<Agence> getAgenceById(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.getAgenceById(id));
    }

    @GetMapping("/ville/{ville}")
    @Operation(summary = "Filtrer les agences par ville")
    public ResponseEntity<List<Agence>> getAgencesParVille(@PathVariable String ville) {
        return ResponseEntity.ok(agenceService.getAgencesParVille(ville));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une agence existante")
    public ResponseEntity<Agence> modifierAgence(@PathVariable Long id, @RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.modifierAgence(id, agence));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    public ResponseEntity<Void> supprimerAgence(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }
}
