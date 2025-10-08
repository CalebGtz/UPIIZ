package com.example.restaurant.services;

import com.example.restaurant.entities.Platillo;
import com.example.restaurant.exceptions.NotFoundException;
import com.example.restaurant.repositories.PlatilloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatilloService {
    private final PlatilloRepository repo;

    public PlatilloService(PlatilloRepository repo) { this.repo = repo; }

    public Platillo create(Platillo p) { return repo.save(p); }
    public List<Platillo> findAll() { return repo.findAll(); }
    public Platillo findById(Long id) { return repo.findById(id).orElseThrow(() -> new NotFoundException("Platillo no encontrado: " + id)); }
    public Platillo update(Long id, Platillo data) {
        Platillo p = findById(id);
        p.setNombre(data.getNombre());
        p.setDescripcion(data.getDescripcion());
        p.setPrecio(data.getPrecio());
        return repo.save(p);
    }
    public void delete(Long id) {
        Platillo p = findById(id);
        repo.delete(p);
    }
}
