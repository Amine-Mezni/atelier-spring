package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.PaiementRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;

    @Override
    public Paiement enregistrerPaiement(Paiement paiement) {
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDate.now());
        }
        return paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement getPaiementById(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec l'identifiant : " + idPaiement));
    }

    @Override
    public void supprimerPaiement(Long idPaiement) {
        if (!paiementRepository.existsById(idPaiement)) {
            throw new RuntimeException("Impossible de supprimer : Paiement non trouvé avec l'identifiant : " + idPaiement);
        }
        paiementRepository.deleteById(idPaiement);
    }

    @Override
    public List<Paiement> getPaiementsParContrat(Long idContrat) {
        return paiementRepository.findByContratIdContrat(idContrat);
    }
}
