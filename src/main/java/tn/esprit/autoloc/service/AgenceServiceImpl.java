package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence getAgenceById(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée avec l'identifiant : " + idAgence));
    }

    @Override
    public Agence modifierAgence(Long idAgence, Agence agence) {
        Agence existante = getAgenceById(idAgence);
        existante.setNom(agence.getNom());
        existante.setVille(agence.getVille());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    public void supprimerAgence(Long idAgence) {
        if (!agenceRepository.existsById(idAgence)) {
            throw new RuntimeException("Impossible de supprimer : Agence non trouvée avec l'identifiant : " + idAgence);
        }
        agenceRepository.deleteById(idAgence);
    }

    @Override
    public List<Agence> getAgencesParVille(String ville) {
        return agenceRepository.findByVille(ville);
    }
}
