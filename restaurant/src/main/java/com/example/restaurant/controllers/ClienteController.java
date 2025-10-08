package com.example.restaurant.controllers;

import com.example.restaurant.entities.Cliente;
import com.example.restaurant.services.ClienteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/garufa/public/v1/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente c) {
        Cliente saved = service.create(c);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        HttpHeaders headers = new HttpHeaders(); headers.setLocation(location);
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente c) { return ResponseEntity.ok(service.update(id, c)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }
}
