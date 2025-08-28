package com.ecommerce.storefront.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.storefront.dto.EstoqueDTO;

@Service
public class EstoqueClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String WAREHOUSE_URL = "http://localhost:8081/estoques/produto/";

    public Integer consultarEstoque(Long produtoId) {
        ResponseEntity<EstoqueDTO> response =
                restTemplate.getForEntity(WAREHOUSE_URL + produtoId, EstoqueDTO.class);

        EstoqueDTO estoque = response.getBody();
        return (estoque != null) ? estoque.getQuantidade() : 0;
    }
}
