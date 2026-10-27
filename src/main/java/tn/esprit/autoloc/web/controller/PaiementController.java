package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Paiements", description = "Gestion des règlements et paiements des contrats")
public class PaiementController {

    private final IPaiementService paiementService;

    @PostMapping
    @Operation(summary = "Enregistrer un nouveau paiement")
    public ResponseEntity<Paiement> enregistrerPaiement(@RequestBody Paiement paiement) {
        Paiement nouveau = paiementService.enregistrerPaiement(paiement);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Lister l'ensemble des paiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement par son identifiant")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.getPaiementById(id));
    }

    @GetMapping("/contrat/{idContrat}")
    @Operation(summary = "Consulter les paiements rattachés à un contrat")
    public ResponseEntity<List<Paiement>> getPaiementsParContrat(@PathVariable Long idContrat) {
        return ResponseEntity.ok(paiementService.getPaiementsParContrat(idContrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> supprimerPaiement(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
        return ResponseEntity.noContent().build();
    }
}
