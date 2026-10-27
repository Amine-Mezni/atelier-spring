package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Employés", description = "Gestion du personnel des agences AutoLoc")
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    @Operation(summary = "Enregistrer un nouvel employé")
    public ResponseEntity<Employe> ajouterEmploye(@RequestBody Employe employe) {
        Employe nouveau = employeService.ajouterEmploye(employe);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Liste de tous les employés")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        return ResponseEntity.ok(employeService.getAllEmployes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un employé par son identifiant")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployeById(id));
    }

    @GetMapping("/agence/{idAgence}")
    @Operation(summary = "Obtenir les employés d'une agence")
    public ResponseEntity<List<Employe>> getEmployesParAgence(@PathVariable Long idAgence) {
        return ResponseEntity.ok(employeService.getEmployesParAgence(idAgence));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier les informations d'un employé")
    public ResponseEntity<Employe> modifierEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.modifierEmploye(id, employe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employé")
    public ResponseEntity<Void> supprimerEmploye(@PathVariable Long id) {
        employeService.supprimerEmploye(id);
        return ResponseEntity.noContent().build();
    }
}
