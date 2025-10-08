package com.example.restaurant.services;

import com.example.restaurant.entities.Cliente;
import com.example.restaurant.exceptions.NotFoundException;
import com.example.restaurant.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) { this.repo = repo; }

    public Cliente create(Cliente c) { return repo.save(c); }
    public List<Cliente> findAll() { return repo.findAll(); }
    public Cliente findById(Long id) { return repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente no encontrado: " + id)); }
    public Cliente update(Long id, Cliente data) {
        Cliente c = findById(id);
        c.setNombre(data.getNombre());
        c.setEmail(data.getEmail());
        c.setTelefono(data.getTelefono());
        return repo.save(c);
    }
    public void delete(Long id) {
        Cliente c = findById(id);
        repo.delete(c);
    }
}
