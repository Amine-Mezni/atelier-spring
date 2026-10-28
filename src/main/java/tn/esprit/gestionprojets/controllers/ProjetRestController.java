package tn.esprit.gestionprojets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionprojets.entities.Projet;
import tn.esprit.gestionprojets.services.IProjetService;

import java.util.List;

@Tag(name = "Gestion Projet")
@RestController
@AllArgsConstructor
@RequestMapping("/projet")
public class ProjetRestController {

    IProjetService projetService;

    // http://localhost:8089/gestionprojets/projet/retrieve-all-projets
    @Operation(description = "récupérer toutes les projets de la base de données")
    @GetMapping("/retrieve-all-projets")
    public List<Projet> getProjets() {
        return projetService.retrieveAllProjets();
    }

    // http://localhost:8089/gestionprojets/projet/retrieve-projet/1
    @Operation(description = "récupérer un projet par son identifiant")
    @GetMapping("/retrieve-projet/{id}")
    public Projet retrieveProjet(@PathVariable("id") Long id) {
        return projetService.retrieveProjet(id);
    }

    // http://localhost:8089/gestionprojets/projet/add-projet
    @Operation(description = "ajouter un nouveau projet")
    @PostMapping("/add-projet")
    public Projet addProjet(@RequestBody Projet p) {
        return projetService.addProjet(p);
    }

    // http://localhost:8089/gestionprojets/projet/update-projet
    @Operation(description = "mettre à jour un projet existant")
    @PutMapping("/update-projet")
    public Projet updateProjet(@RequestBody Projet p) {
        return projetService.updateProjet(p);
    }

    // http://localhost:8089/gestionprojets/projet/remove-projet/1
    @Operation(description = "supprimer un projet par son identifiant")
    @DeleteMapping("/remove-projet/{id}")
    public void removeProjet(@PathVariable("id") Long id) {
        projetService.removeProjet(id);
    }
}
