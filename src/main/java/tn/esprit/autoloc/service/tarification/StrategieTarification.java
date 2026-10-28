package tn.esprit.autoloc.service.tarification;

/**
 * Interface abstraite définissant la stratégie de calcul d'un tarif de location.
 * Patron GoF : Strategy
 */
public interface StrategieTarification {

    /**
     * Calcule le montant total de la location selon la politique tarifaire.
     * @param tarifJournalier Tarif de base journalier
     * @param jours Nombre de jours de location
     * @return Montant calculé
     */
    double calculer(double tarifJournalier, int jours);
}
