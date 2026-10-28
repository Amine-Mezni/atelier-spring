package tn.esprit.gestionprojets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestionprojets.entities.Entreprise;
import tn.esprit.gestionprojets.repositories.EntrepriseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements IEntrepriseService {

    private final EntrepriseRepository repository;

    @Override
    public List<Entreprise> retrieveAllEntreprises() {
        return repository.findAll();
    }

    @Override
    public Entreprise retrieveEntreprise(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Entreprise addEntreprise(Entreprise entity) {
        return repository.save(entity);
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entity) {
        return repository.save(entity);
    }

    @Override
    public void removeEntreprise(Long id) {
        repository.deleteById(id);
    }
}
