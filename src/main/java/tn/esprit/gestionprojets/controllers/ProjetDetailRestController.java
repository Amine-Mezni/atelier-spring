package tn.esprit.gestionprojets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionprojets.entities.ProjetDetail;
import tn.esprit.gestionprojets.services.IProjetDetailService;

import java.util.List;

@Tag(name = "Gestion ProjetDetail")
@RestController
@AllArgsConstructor
@RequestMapping("/projet-detail")
public class ProjetDetailRestController {

    IProjetDetailService projetDetailService;

    @Operation(description = "récupérer tous les détails de projets")
    @GetMapping("/retrieve-all-projet-details")
    public List<ProjetDetail> getProjetDetails() {
        return projetDetailService.retrieveAllProjetDetails();
    }

    @Operation(description = "récupérer le détail d'un projet par son id")
    @GetMapping("/retrieve-projet-detail/{id}")
    public ProjetDetail retrieveProjetDetail(@PathVariable("id") Long id) {
        return projetDetailService.retrieveProjetDetail(id);
    }

    @Operation(description = "ajouter un détail de projet")
    @PostMapping("/add-projet-detail")
    public ProjetDetail addProjetDetail(@RequestBody ProjetDetail pd) {
        return projetDetailService.addProjetDetail(pd);
    }

    @Operation(description = "mettre à jour un détail de projet")
    @PutMapping("/update-projet-detail")
    public ProjetDetail updateProjetDetail(@RequestBody ProjetDetail pd) {
        return projetDetailService.updateProjetDetail(pd);
    }

    @Operation(description = "supprimer un détail de projet")
    @DeleteMapping("/remove-projet-detail/{id}")
    public void removeProjetDetail(@PathVariable("id") Long id) {
        projetDetailService.removeProjetDetail(id);
    }
}
