package com.example.restaurant.controllers;

import com.example.restaurant.entities.Platillo;
import com.example.restaurant.services.PlatilloService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/garufa/public/v1/platillos")
@CrossOrigin(origins = "*")
public class PlatilloController {
    private final PlatilloService service;
    public PlatilloController(PlatilloService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Platillo> create(@RequestBody Platillo p) {
        Platillo saved = service.create(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        HttpHeaders headers = new HttpHeaders(); headers.setLocation(location);
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Platillo>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Platillo> findById(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Platillo> update(@PathVariable Long id, @RequestBody Platillo p) { return ResponseEntity.ok(service.update(id, p)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
