package tn.esprit.gestionprojets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestionprojets.entities.Equipe;
import tn.esprit.gestionprojets.repositories.EquipeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository repository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return repository.findAll();
    }

    @Override
    public Equipe retrieveEquipe(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Equipe addEquipe(Equipe entity) {
        return repository.save(entity);
    }

    @Override
    public Equipe updateEquipe(Equipe entity) {
        return repository.save(entity);
    }

    @Override
    public void removeEquipe(Long id) {
        repository.deleteById(id);
    }
}
