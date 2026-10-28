package tn.esprit.gestionprojets.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.gestionprojets.entities.ProjetDetail;
import tn.esprit.gestionprojets.repositories.ProjetDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetDetailServiceImpl implements IProjetDetailService {

    private final ProjetDetailRepository repository;

    @Override
    public List<ProjetDetail> retrieveAllProjetDetails() {
        return repository.findAll();
    }

    @Override
    public ProjetDetail retrieveProjetDetail(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProjetDetail addProjetDetail(ProjetDetail entity) {
        return repository.save(entity);
    }

    @Override
    public ProjetDetail updateProjetDetail(ProjetDetail entity) {
        return repository.save(entity);
    }

    @Override
    public void removeProjetDetail(Long id) {
        repository.deleteById(id);
    }
}
