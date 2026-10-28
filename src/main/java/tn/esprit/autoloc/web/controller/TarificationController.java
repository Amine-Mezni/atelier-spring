package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.service.tarification.TarificationService;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/tarification")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Tarification", description = "Calcul dynamique des tarifs de location avec le patron GoF Strategy")
public class TarificationController {

    private final TarificationService tarificationService;
    private final IVehiculeService vehiculeService;

    @GetMapping("/politiques")
    @Operation(summary = "Lister les politiques tarifaires disponibles (Strategy)")
    public ResponseEntity<Set<String>> getPolitiques() {
        return ResponseEntity.ok(tarificationService.getPolitiquesDisponibles());
    }

    @GetMapping("/calculer")
    @Operation(summary = "Calculer le prix d'une location selon une politique tarifaire")
    public ResponseEntity<Map<String, Object>> calculer(
            @RequestParam(defaultValue = "STANDARD") String politique,
            @RequestParam double tarifJournalier,
            @RequestParam int jours) {
        double total = tarificationService.calculerPrix(politique, tarifJournalier, jours);
        return ResponseEntity.ok(Map.of(
                "politique", politique.toUpperCase(),
                "tarifJournalier", tarifJournalier,
                "jours", jours,
                "montantTotal", total
        ));
    }

    @GetMapping("/vehicule/{idVehicule}")
    @Operation(summary = "Calculer le tarif de location pour un véhicule existant")
    public ResponseEntity<Map<String, Object>> calculerPourVehicule(
            @PathVariable Long idVehicule,
            @RequestParam(defaultValue = "STANDARD") String politique,
            @RequestParam int jours) {
        double total = vehiculeService.calculerTarif(idVehicule, jours, politique);
        return ResponseEntity.ok(Map.of(
                "idVehicule", idVehicule,
                "politique", politique.toUpperCase(),
                "jours", jours,
                "montantTotal", total
        ));
    }
}
