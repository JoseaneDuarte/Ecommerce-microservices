package com.ecommerce.storefront.dto;

import java.util.List;

public class PedidoDTO {
    private Long clienteId;
    private List<ItemPedidoDTO> itens; //classe separada

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<ItemPedidoDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoDTO> itens) { this.itens = itens; }

    @Override
    public String toString() {
        return "PedidoDTO{clienteId=" + clienteId + ", itens=" + itens + "}";
    }
}
