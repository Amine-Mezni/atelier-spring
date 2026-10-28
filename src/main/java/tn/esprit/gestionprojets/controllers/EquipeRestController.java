package tn.esprit.gestionprojets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionprojets.entities.Equipe;
import tn.esprit.gestionprojets.services.IEquipeService;

import java.util.List;

@Tag(name = "Gestion Equipe")
@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {

    IEquipeService equipeService;

    @Operation(description = "récupérer toutes les équipes de la base de données")
    @GetMapping("/retrieve-all-equipes")
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }

    @Operation(description = "récupérer une équipe par son identifiant")
    @GetMapping("/retrieve-equipe/{id}")
    public Equipe retrieveEquipe(@PathVariable("id") Long id) {
        return equipeService.retrieveEquipe(id);
    }

    @Operation(description = "ajouter une nouvelle équipe")
    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody Equipe eq) {
        return equipeService.addEquipe(eq);
    }

    @Operation(description = "mettre à jour une équipe")
    @PutMapping("/update-equipe")
    public Equipe updateEquipe(@RequestBody Equipe eq) {
        return equipeService.updateEquipe(eq);
    }

    @Operation(description = "supprimer une équipe")
    @DeleteMapping("/remove-equipe/{id}")
    public void removeEquipe(@PathVariable("id") Long id) {
        equipeService.removeEquipe(id);
    }
}
