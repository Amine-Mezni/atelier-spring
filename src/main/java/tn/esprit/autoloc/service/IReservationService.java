package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation creerReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long idReservation);
    Reservation modifierReservation(Long idReservation, Reservation reservation);
    void annulerReservation(Long idReservation);
    List<Reservation> getReservationsParClient(Long idClient);
}
