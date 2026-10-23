package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;
import tn.esprit.autoloc.repository.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation creerReservation(Reservation reservation) {
        if (reservation.getStatut() == null) {
            reservation.setStatut(StatutReservation.EN_ATTENTE);
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long idReservation) {
        return reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'identifiant : " + idReservation));
    }

    @Override
    public Reservation modifierReservation(Long idReservation, Reservation reservation) {
        Reservation existante = getReservationById(idReservation);
        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        if (reservation.getClient() != null) {
            existante.setClient(reservation.getClient());
        }
        if (reservation.getVehicule() != null) {
            existante.setVehicule(reservation.getVehicule());
        }
        return reservationRepository.save(existante);
    }

    @Override
    public void annulerReservation(Long idReservation) {
        Reservation existante = getReservationById(idReservation);
        existante.setStatut(StatutReservation.ANNULEE);
        reservationRepository.save(existante);
    }

    @Override
    public List<Reservation> getReservationsParClient(Long idClient) {
        return reservationRepository.findByClientIdClient(idClient);
    }
}
