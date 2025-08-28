package com.ecommerce.storefront.dto;

public class ItemPedidoDTO {
    private Long produtoId;
    private Integer quantidade;

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "ItemPedidoDTO{produtoId=" + produtoId + ", quantidade=" + quantidade + "}";
    }
}
