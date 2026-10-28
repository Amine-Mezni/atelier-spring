package tn.esprit.gestionprojets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionprojets.entities.ProjetDetail;

@Repository
public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {
}
