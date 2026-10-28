package tn.esprit.gestionprojets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionprojets.entities.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
