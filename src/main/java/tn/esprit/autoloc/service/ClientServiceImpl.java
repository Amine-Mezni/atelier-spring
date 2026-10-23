package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client ajouterClient(Client client) {
        if (client.getDateInscription() == null) {
            client.setDateInscription(LocalDate.now());
        }
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'identifiant : " + idClient));
    }

    @Override
    public Client modifierClient(Long idClient, Client client) {
        Client existant = getClientById(idClient);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        return clientRepository.save(existant);
    }

    @Override
    public void supprimerClient(Long idClient) {
        if (!clientRepository.existsById(idClient)) {
            throw new RuntimeException("Impossible de supprimer : Client non trouvé avec l'identifiant : " + idClient);
        }
        clientRepository.deleteById(idClient);
    }
}
