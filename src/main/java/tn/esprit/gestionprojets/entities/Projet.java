package tn.esprit.gestionprojets.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProjetDetail projetDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    private Entreprise entreprise;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "projet_equipes",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    private List<Equipe> equipes;
}
