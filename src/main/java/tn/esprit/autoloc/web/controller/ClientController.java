package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Clients", description = "Gestion des clients de l'application AutoLoc")
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    @Operation(summary = "Inscrire un nouveau client")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        Client nouveau = clientService.ajouterClient(client);
        return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Récupérer la liste complète des clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter le profil d'un client par son identifiant")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier les coordonnées d'un client")
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifierClient(id, client));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer le compte d'un client")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}
