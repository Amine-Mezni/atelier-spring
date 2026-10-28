package tn.esprit.autoloc.service.validation;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;

/**
 * Composant dédié à la validation métier des véhicules.
 * Respecte le principe SRP (Single Responsibility Principle) :
 * une seule responsabilité -> vérifier l'intégrité et les règles des véhicules.
 */
@Component
public class VehiculeValidator {

    public void valider(Vehicule vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être null.");
        }
        if (vehicule.getTarifJournalier() == null || vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être strictement positif.");
        }
        if (vehicule.getImmatriculation() == null || vehicule.getImmatriculation().trim().isEmpty()) {
            throw new IllegalArgumentException("L'immatriculation du véhicule est obligatoire.");
        }
        if (vehicule.getMarque() == null || vehicule.getMarque().trim().isEmpty()) {
            throw new IllegalArgumentException("La marque du véhicule est obligatoire.");
        }
        if (vehicule.getModele() == null || vehicule.getModele().trim().isEmpty()) {
            throw new IllegalArgumentException("Le modèle du véhicule est obligatoire.");
        }
        if (vehicule.getCategorie() == null) {
            throw new IllegalArgumentException("La catégorie du véhicule est obligatoire.");
        }
    }
}
