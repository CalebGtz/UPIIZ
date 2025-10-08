package com.example.restaurant.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
    public static class PlatilloLinea {
        private Long id;
        private String nombre;
        private BigDecimal precio;
        public PlatilloLinea() {}
        public PlatilloLinea(Long id, String nombre, BigDecimal precio) {
            this.id = id; this.nombre = nombre; this.precio = precio;
        }
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public BigDecimal getPrecio() { return precio; }
        public void setPrecio(BigDecimal precio) { this.precio = precio; }
    }

    private Long id;
    private LocalDateTime fecha;
    private Long clienteId;
    private BigDecimal total;
    private List<PlatilloLinea> platillos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public List<PlatilloLinea> getPlatillos() { return platillos; }
    public void setPlatillos(List<PlatilloLinea> platillos) { this.platillos = platillos; }
}
