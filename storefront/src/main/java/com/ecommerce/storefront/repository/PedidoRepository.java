package com.ecommerce.storefront.repository;



import com.ecommerce.storefront.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
