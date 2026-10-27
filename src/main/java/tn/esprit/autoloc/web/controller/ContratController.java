package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    @Operation(summary = "Établir un nouveau contrat de location")
    public ResponseEntity<Contrat> creerContrat(@RequestBody Contrat contrat) {
        Contrat nouveau = contratService.creerContrat(contrat);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Consulter l'ensemble des contrats")
    public ResponseEntity<List<Contrat>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un contrat spécifique par son identifiant")
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.getContratById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour les termes d'un contrat")
    public ResponseEntity<Contrat> modifierContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.modifierContrat(id, contrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat et ses paiements associés en cascade")
    public ResponseEntity<Void> supprimerContrat(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.noContent().build();
    }
}
