package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.EquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement getEquipementById(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé avec l'identifiant : " + idEquipement));
    }

    @Override
    public Equipement modifierEquipement(Long idEquipement, Equipement equipement) {
        Equipement existant = getEquipementById(idEquipement);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    public void supprimerEquipement(Long idEquipement) {
        if (!equipementRepository.existsById(idEquipement)) {
            throw new RuntimeException("Impossible de supprimer : Équipement non trouvé avec l'identifiant : " + idEquipement);
        }
        equipementRepository.deleteById(idEquipement);
    }
}
