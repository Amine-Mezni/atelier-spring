package tn.esprit.gestionprojets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestionprojets.entities.Projet;
import tn.esprit.gestionprojets.repositories.ProjetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetServiceImpl implements IProjetService {

    private final ProjetRepository projetRepository;

    @Override
    public List<Projet> retrieveAllProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projet retrieveProjet(Long id) {
        return projetRepository.findById(id).orElse(null);
    }

    @Override
    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Projet updateProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public void removeProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
