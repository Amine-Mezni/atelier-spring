package tn.esprit.autoloc.service.journalisation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implémentation du service de journalisation centralisé.
 */
@Service
@Slf4j
public class JournalisationServiceImpl implements JournalisationService {

    @Override
    public void journaliser(String message) {
        log.info("[AUTOLOC JOURNAL] {}", message);
    }

    @Override
    public void journaliserAction(String action, String entite, Object identifiant) {
        log.info("[AUTOLOC JOURNAL] Action: [{}] | Entité: [{}] | Référence: [{}]", action, entite, identifiant);
    }
}
