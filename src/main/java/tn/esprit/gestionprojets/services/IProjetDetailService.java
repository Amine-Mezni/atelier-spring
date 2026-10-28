package tn.esprit.gestionprojets.services;

import tn.esprit.gestionprojets.entities.ProjetDetail;
import java.util.List;

public interface IProjetDetailService {
    List<ProjetDetail> retrieveAllProjetDetails();
    ProjetDetail retrieveProjetDetail(Long id);
    ProjetDetail addProjetDetail(ProjetDetail entity);
    ProjetDetail updateProjetDetail(ProjetDetail entity);
    void removeProjetDetail(Long id);
}
