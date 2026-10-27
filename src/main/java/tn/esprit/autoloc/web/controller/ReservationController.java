package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Réservations", description = "Gestion des réservations de véhicules")
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    @Operation(summary = "Créer une réservation")
    public ResponseEntity<Reservation> creerReservation(@RequestBody Reservation reservation) {
        Reservation nouvelle = reservationService.creerReservation(reservation);
        return new ResponseEntity<>(nouvelle, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Consulter toutes les réservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une réservation par son identifiant")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/client/{idClient}")
    @Operation(summary = "Consulter les réservations d'un client")
    public ResponseEntity<List<Reservation>> getReservationsParClient(@PathVariable Long idClient) {
        return ResponseEntity.ok(reservationService.getReservationsParClient(idClient));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une réservation existante")
    public ResponseEntity<Reservation> modifierReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.modifierReservation(id, reservation));
    }

    @PatchMapping("/{id}/annuler")
    @Operation(summary = "Annuler une réservation")
    public ResponseEntity<Void> annulerReservation(@PathVariable Long id) {
        reservationService.annulerReservation(id);
        return ResponseEntity.noContent().build();
    }
}
