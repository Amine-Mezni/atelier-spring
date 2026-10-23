package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    Client ajouterClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long idClient);
    Client modifierClient(Long idClient, Client client);
    void supprimerClient(Long idClient);
}
