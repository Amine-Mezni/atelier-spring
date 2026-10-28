package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/**
 * Politique tarifaire week-end : réduction de 10 %.
 */
@Component("WEEKEND")
public class TarificationWeekend implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        if (tarifJournalier <= 0 || jours <= 0) {
            throw new IllegalArgumentException("Le tarif journalier et le nombre de jours doivent être strictement positifs.");
        }
        return (tarifJournalier * jours) * 0.90;
    }
}
