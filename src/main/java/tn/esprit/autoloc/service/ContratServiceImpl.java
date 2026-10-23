package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.ContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;

    @Override
    public Contrat creerContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat getContratById(Long idContrat) {
        return contratRepository.findById(idContrat)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé avec l'identifiant : " + idContrat));
    }

    @Override
    public Contrat modifierContrat(Long idContrat, Contrat contrat) {
        Contrat existant = getContratById(idContrat);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        if (contrat.getReservation() != null) {
            existant.setReservation(contrat.getReservation());
        }
        return contratRepository.save(existant);
    }

    @Override
    public void supprimerContrat(Long idContrat) {
        if (!contratRepository.existsById(idContrat)) {
            throw new RuntimeException("Impossible de supprimer : Contrat non trouvé avec l'identifiant : " + idContrat);
        }
        contratRepository.deleteById(idContrat);
    }
}
