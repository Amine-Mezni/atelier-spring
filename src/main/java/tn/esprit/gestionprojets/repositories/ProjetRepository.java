package tn.esprit.gestionprojets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionprojets.entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
