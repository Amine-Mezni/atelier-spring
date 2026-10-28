package tn.esprit.gestionprojets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionprojets.entities.Entreprise;
import tn.esprit.gestionprojets.services.IEntrepriseService;

import java.util.List;

@Tag(name = "Gestion Entreprise")
@RestController
@AllArgsConstructor
@RequestMapping("/entreprise")
public class EntrepriseRestController {

    IEntrepriseService entrepriseService;

    @Operation(description = "récupérer toutes les entreprises de la base de données")
    @GetMapping("/retrieve-all-entreprises")
    public List<Entreprise> getEntreprises() {
        return entrepriseService.retrieveAllEntreprises();
    }

    @Operation(description = "récupérer une entreprise par son identifiant")
    @GetMapping("/retrieve-entreprise/{id}")
    public Entreprise retrieveEntreprise(@PathVariable("id") Long id) {
        return entrepriseService.retrieveEntreprise(id);
    }

    @Operation(description = "ajouter une nouvelle entreprise")
    @PostMapping("/add-entreprise")
    public Entreprise addEntreprise(@RequestBody Entreprise e) {
        return entrepriseService.addEntreprise(e);
    }

    @Operation(description = "mettre à jour une entreprise")
    @PutMapping("/update-entreprise")
    public Entreprise updateEntreprise(@RequestBody Entreprise e) {
        return entrepriseService.updateEntreprise(e);
    }

    @Operation(description = "supprimer une entreprise")
    @DeleteMapping("/remove-entreprise/{id}")
    public void removeEntreprise(@PathVariable("id") Long id) {
        entrepriseService.removeEntreprise(id);
    }
}
