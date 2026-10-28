package tn.esprit.autoloc.service.journalisation;

/**
 * Contrat de service pour la journalisation des opérations.
 * Respecte le principe DIP (Dependency Inversion Principle).
 */
public interface JournalisationService {
    void journaliser(String message);
    void journaliserAction(String action, String entite, Object identifiant);
}
