package com.example.restaurant.services;

import com.example.restaurant.dto.CreatePedidoRequest;
import com.example.restaurant.dto.PedidoResponse;
import com.example.restaurant.dto.UpdatePedidoRequest;
import com.example.restaurant.entities.*;
import com.example.restaurant.exceptions.NotFoundException;
import com.example.restaurant.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepo;
    private final ClienteRepository clienteRepo;
    private final PlatilloRepository platilloRepo;
    private final DetallePedidoRepository detalleRepo;

    public PedidoService(PedidoRepository pedidoRepo, ClienteRepository clienteRepo, PlatilloRepository platilloRepo, DetallePedidoRepository detalleRepo) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
        this.platilloRepo = platilloRepo;
        this.detalleRepo = detalleRepo;
    }

    @Transactional
    public PedidoResponse create(CreatePedidoRequest req) {
        Cliente cliente = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado: " + req.getClienteId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(BigDecimal.ZERO);
        pedido = pedidoRepo.save(pedido);

        if (req.getPlatillosIds() != null) {
            for (Long pid : req.getPlatillosIds()) {
                Platillo pl = platilloRepo.findById(pid)
                        .orElseThrow(() -> new NotFoundException("Platillo no encontrado: " + pid));
                DetallePedido d = new DetallePedido();
                d.setPlatillo(pl);
                d.setPrecioActual(pl.getPrecio());
                d.setPedido(pedido);
                detalleRepo.save(d);
                pedido.addDetalle(d);
                pedido.setTotal(pedido.getTotal().add(pl.getPrecio()));
            }
        }
        pedido = pedidoRepo.save(pedido);
        return toResponse(pedido);
    }

    public List<PedidoResponse> findAll() {
        return pedidoRepo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PedidoResponse findById(Long id) {
        Pedido p = pedidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + id));
        return toResponse(p);
    }

    @Transactional
    public PedidoResponse update(Long id, UpdatePedidoRequest req) {
        Pedido pedido = pedidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + id));

        if (req.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(req.getClienteId())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado: " + req.getClienteId()));
            pedido.setCliente(cliente);
        }

        if (req.getPlatillosIds() != null) {
            for (DetallePedido d : new ArrayList<>(pedido.getDetalles())) {
                pedido.removeDetalle(d);
                detalleRepo.delete(d);
            }
            pedido.setTotal(BigDecimal.ZERO);
            for (Long pid : req.getPlatillosIds()) {
                Platillo pl = platilloRepo.findById(pid)
                        .orElseThrow(() -> new NotFoundException("Platillo no encontrado: " + pid));
                DetallePedido d = new DetallePedido();
                d.setPlatillo(pl);
                d.setPrecioActual(pl.getPrecio());
                d.setPedido(pedido);
                detalleRepo.save(d);
                pedido.addDetalle(d);
                pedido.setTotal(pedido.getTotal().add(pl.getPrecio()));
            }
        }
        pedido = pedidoRepo.save(pedido);
        return toResponse(pedido);
    }

    @Transactional
    public void delete(Long id) {
        Pedido p = pedidoRepo.findById(id).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + id));
        for (DetallePedido d : new ArrayList<>(p.getDetalles())) {
            p.removeDetalle(d);
            detalleRepo.delete(d);
        }
        pedidoRepo.delete(p);
    }

    @Transactional
    public PedidoResponse addPlatillo(Long idPedido, Long idPlatillo) {
        Pedido p = pedidoRepo.findById(idPedido).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + idPedido));
        Platillo pl = platilloRepo.findById(idPlatillo).orElseThrow(() -> new NotFoundException("Platillo no encontrado: " + idPlatillo));
        DetallePedido d = new DetallePedido();
        d.setPedido(p);
        d.setPlatillo(pl);
        d.setPrecioActual(pl.getPrecio());
        detalleRepo.save(d);
        p.addDetalle(d);
        p.setTotal(p.getTotal().add(pl.getPrecio()));
        pedidoRepo.save(p);
        return toResponse(p);
    }

    @Transactional
    public PedidoResponse removePlatillo(Long idPedido, Long idPlatillo) {
        Pedido p = pedidoRepo.findById(idPedido).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + idPedido));
        DetallePedido toRemove = p.getDetalles().stream()
                .filter(d -> d.getPlatillo() != null && idPlatillo.equals(d.getPlatillo().getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("El platillo " + idPlatillo + " no está en el pedido " + idPedido));
        p.removeDetalle(toRemove);
        detalleRepo.delete(toRemove);
        if (toRemove.getPrecioActual() != null) {
            p.setTotal(p.getTotal().subtract(toRemove.getPrecioActual()));
        }
        pedidoRepo.save(p);
        return toResponse(p);
    }

    public List<PedidoResponse.PlatilloLinea> listPlatillos(Long idPedido) {
        Pedido p = pedidoRepo.findById(idPedido).orElseThrow(() -> new NotFoundException("Pedido no encontrado: " + idPedido));
        return p.getDetalles().stream()
                .map(d -> new PedidoResponse.PlatilloLinea(
                        d.getPlatillo().getId(),
                        d.getPlatillo().getNombre(),
                        d.getPrecioActual()
                ))
                .collect(Collectors.toList());
    }

    private PedidoResponse toResponse(Pedido p) {
        PedidoResponse resp = new PedidoResponse();
        resp.setId(p.getId());
        resp.setFecha(p.getFecha());
        resp.setClienteId(p.getCliente() != null ? p.getCliente().getId() : null);
        resp.setTotal(p.getTotal());
        resp.setPlatillos(p.getDetalles().stream()
                .map(d -> new PedidoResponse.PlatilloLinea(
                        d.getPlatillo().getId(),
                        d.getPlatillo().getNombre(),
                        d.getPrecioActual()
                ))
                .collect(Collectors.toList()));
        return resp;
    }
}
