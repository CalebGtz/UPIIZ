package com.example.restaurant.dto;

import java.util.List;

public class UpdatePedidoRequest {
    private Long clienteId;
    private List<Long> platillosIds;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<Long> getPlatillosIds() { return platillosIds; }
    public void setPlatillosIds(List<Long> platillosIds) { this.platillosIds = platillosIds; }
}
