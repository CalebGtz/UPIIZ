package com.example.restaurant.controllers;

import com.example.restaurant.dto.CreatePedidoRequest;
import com.example.restaurant.dto.PedidoResponse;
import com.example.restaurant.dto.UpdatePedidoRequest;
import com.example.restaurant.services.PedidoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/garufa/public/v1/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    private final PedidoService service;
    public PedidoController(PedidoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<PedidoResponse> create(@RequestBody CreatePedidoRequest req) {
        PedidoResponse saved = service.create(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        HttpHeaders headers = new HttpHeaders(); headers.setLocation(location);
        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable Long id) { return ResponseEntity.ok(service.findById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> update(@PathVariable Long id, @RequestBody UpdatePedidoRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

    @PostMapping("/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<PedidoResponse> addPlatillo(@PathVariable Long idPedido, @PathVariable Long idPlatillo) {
        return ResponseEntity.ok(service.addPlatillo(idPedido, idPlatillo));
    }

    @DeleteMapping("/{idPedido}/platillos/{idPlatillo}")
    public ResponseEntity<PedidoResponse> removePlatillo(@PathVariable Long idPedido, @PathVariable Long idPlatillo) {
        return ResponseEntity.ok(service.removePlatillo(idPedido, idPlatillo));
    }

    @GetMapping("/{idPedido}/platillos")
    public ResponseEntity<List<com.example.restaurant.dto.PedidoResponse.PlatilloLinea>> listPlatillos(@PathVariable Long idPedido) {
        return ResponseEntity.ok(service.listPlatillos(idPedido));
    }
}
