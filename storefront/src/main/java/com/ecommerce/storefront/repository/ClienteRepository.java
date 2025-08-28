package com.ecommerce.storefront.repository;

import com.ecommerce.storefront.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // métodos personalizados depois,opcional
}
