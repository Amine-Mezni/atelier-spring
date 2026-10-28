package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

/**
 * Politique tarifaire entreprise (Partie C Étape 4) : réduction de 15 %.
 * Démontre le principe OCP (Open/Closed Principle) :
 * Ajoutée sans modifier d'une seule ligne le code de TarificationService !
 */
@Component("ENTREPRISE")
public class TarificationEntreprise implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        if (tarifJournalier <= 0 || jours <= 0) {
            throw new IllegalArgumentException("Le tarif journalier et le nombre de jours doivent être strictement positifs.");
        }
        return (tarifJournalier * jours) * 0.85;
    }
}
