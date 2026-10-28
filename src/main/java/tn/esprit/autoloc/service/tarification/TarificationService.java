package tn.esprit.autoloc.service.tarification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Service de calcul de tarification utilisant le patron GoF Strategy.
 * Reçoit dynamiquement toutes les stratégies Spring dans une Map injectée.
 */
@Service
@RequiredArgsConstructor
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public double calculerPrix(String typePolitique, double tarifJournalier, int jours) {
        if (typePolitique == null || typePolitique.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type de politique tarifaire est obligatoire.");
        }

        String cle = typePolitique.trim().toUpperCase();
        StrategieTarification strategie = strategies.get(cle);

        if (strategie == null) {
            // Recherche insensible à la casse
            strategie = strategies.entrySet().stream()
                    .filter(e -> e.getKey().equalsIgnoreCase(typePolitique.trim()))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Type de tarif inconnu : '" + typePolitique + 
                            "'. Politiques disponibles : " + getPolitiquesDisponibles()));
        }

        return strategie.calculer(tarifJournalier, jours);
    }

    public Set<String> getPolitiquesDisponibles() {
        return strategies.keySet();
    }
}
