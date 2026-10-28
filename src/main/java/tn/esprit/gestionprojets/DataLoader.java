package tn.esprit.gestionprojets;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.gestionprojets.entities.Entreprise;
import tn.esprit.gestionprojets.entities.Equipe;
import tn.esprit.gestionprojets.entities.Projet;
import tn.esprit.gestionprojets.entities.ProjetDetail;
import tn.esprit.gestionprojets.repositories.EntrepriseRepository;
import tn.esprit.gestionprojets.repositories.EquipeRepository;
import tn.esprit.gestionprojets.repositories.ProjetDetailRepository;
import tn.esprit.gestionprojets.repositories.ProjetRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProjetRepository projetRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EquipeRepository equipeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Entreprise esprit = Entreprise.builder()
                .nom("ESPRIT")
                .adresse("Ariana, Tunis")
                .build();
        esprit = entrepriseRepository.save(esprit);

        Equipe teamDev = Equipe.builder()
                .nom("Equipe Alpha Dev")
                .domaine("Web & Cloud")
                .build();
        teamDev = equipeRepository.save(teamDev);

        ProjetDetail detail = ProjetDetail.builder()
                .description("Système d'information d'entreprise")
                .technologie("Spring Boot 3 & Angular")
                .cout(15000.0)
                .dateDebut(new Date())
                .build();

        List<Equipe> equipeList = new ArrayList<>();
        equipeList.add(teamDev);

        Projet p1 = Projet.builder()
                .sujet("Plateforme ASI")
                .dateDebut(new Date())
                .dateFin(new Date(System.currentTimeMillis() + 86400000L * 90))
                .entreprise(esprit)
                .projetDetail(detail)
                .equipes(equipeList)
                .build();

        // detail is automatically cascaded and persisted via Projet.projetDetail (CascadeType.ALL)
        projetRepository.save(p1);

        System.out.println(">>> Demo data loaded in gestionprojets!");
    }
}
