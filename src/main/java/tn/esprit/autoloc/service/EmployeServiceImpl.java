package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    @Override
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe getEmployeById(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'identifiant : " + idEmploye));
    }

    @Override
    public Employe modifierEmploye(Long idEmploye, Employe employe) {
        Employe existant = getEmployeById(idEmploye);
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        if (employe.getAgence() != null) {
            existant.setAgence(employe.getAgence());
        }
        return employeRepository.save(existant);
    }

    @Override
    public void supprimerEmploye(Long idEmploye) {
        if (!employeRepository.existsById(idEmploye)) {
            throw new RuntimeException("Impossible de supprimer : Employé non trouvé avec l'identifiant : " + idEmploye);
        }
        employeRepository.deleteById(idEmploye);
    }

    @Override
    public List<Employe> getEmployesParAgence(Long idAgence) {
        return employeRepository.findByAgenceIdAgence(idAgence);
    }
}
