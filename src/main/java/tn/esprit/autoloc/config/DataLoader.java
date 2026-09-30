package tn.esprit.autoloc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final VehiculeRepository vehiculeRepository;

    @Bean
    public CommandLineRunner initVehicules() {
        return args -> {
            if (vehiculeRepository.count() == 0) {
                Vehicule v1 = new Vehicule(
                        null,
                        "240-TUN-101",
                        "Peugeot",
                        "208",
                        CategorieVehicule.CITADINE,
                        new BigDecimal("90.00"),
                        StatutVehicule.DISPONIBLE
                );

                Vehicule v2 = new Vehicule(
                        null,
                        "235-TUN-404",
                        "Volkswagen",
                        "Passat",
                        CategorieVehicule.BERLINE,
                        new BigDecimal("150.00"),
                        StatutVehicule.DISPONIBLE
                );

                Vehicule v3 = new Vehicule(
                        null,
                        "238-TUN-789",
                        "Toyota",
                        "RAV4",
                        CategorieVehicule.SUV,
                        new BigDecimal("220.00"),
                        StatutVehicule.LOUE
                );

                vehiculeRepository.saveAll(List.of(v1, v2, v3));
            }
        };
    }
}
