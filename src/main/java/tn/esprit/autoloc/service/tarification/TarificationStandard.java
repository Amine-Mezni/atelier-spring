package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/**
 * Politique tarifaire standard : tarif journalier × nombre de jours.
 */
@Component("STANDARD")
public class TarificationStandard implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        if (tarifJournalier <= 0 || jours <= 0) {
            throw new IllegalArgumentException("Le tarif journalier et le nombre de jours doivent être strictement positifs.");
        }
        return tarifJournalier * jours;
    }
}
