# E-Commerce — Microsserviços com Spring Boot e RabbitMQ

## 📌 Sobre o Projeto:

Este projeto implementa uma arquitetura de microsserviços em Java com Spring Boot, simulando um e commerce com dois serviços principais:
•	storefront (vitrine) — onde o cliente faz pedidos.
•	warehouse (armazém) — que gerencia o estoque. 
A comunicação entre eles ocorre de forma:  
•	Síncrona — via HTTP
•	Assíncrona — via RabbitMQ  
Este repositório é a minha versão aperfeiçoada do projeto proposto no desafio da DIO, com ajustes, correções e regras adicionais implementadas.  
🛠 Tecnologias Utilizadas  
•	Java 23
•	Spring Boot
•	Spring Web
•	Spring Data JPA
•	H2 Database
•	Spring AMQP (RabbitMQ)
•	Lombok
 Como Rodar Localmente:  
1.	Clonar o repositório  
bash  
git clone https://github.com/JoseaneDuarte/Ecommerce-microservices.git  
2.	Subir o RabbitMQ (Docker, por exemplo)  
bash  
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management  
3.	Rodar o warehouse  
bash  
cd warehouse  
mvn spring-boot:run  
4.	Rodar o storefront  
bash  
cd storefront  
mvn spring-boot:run  

# 📌Imagens dos Testes Realizados: Síncrono e Assíncrono

## Síncrono
1- Tests Consulta HTTP direto ao Warehouse — Produto específico  
Objetivo: verificar se o serviço responde corretamente à consulta de estoque de um produto individual.  
Passos realizados:  
•	Foi feita uma requisição direta via HTTP para o endpoint:  
Código  
GET /estoques/produto/6  
•	O warehouse respondeu em tempo real com os dados de estoque do Produto 6.  
![Consulta de quantidade](./assets/Testes%20Pedido%20Síncrono%20consulta%20item.png)  

2- Teste Listar todos os estoques do Warehouse  
Objetivo: confirmar que o endpoint retorna a lista completa de produtos e quantidades no estoque.  
Passos realizados:  
•	Foi feita uma requisição via HTTP para:  
Código  
GET /estoques  
•	O warehouse retornou todos os registros da tabela de estoque.  

![Estoque completo](/assets/Teste%20Síncrono%20Lista%20Estoque.png)  

3- Teste Consultar o estoque de um produto com pouca quantidade (Produto 7)  
Objetivo: identificar situação de estoque baixo e preparar o cenário para o teste de estoque insuficiente.  
Passos realizados:  
•	Consulta HTTP para:  
Código  
GET /estoques/produto/7  
•	O warehouse respondeu com a quantidade disponível atual do Produto 7 (baixa), que será usada em teste posterior.  

![Estoque item 7](/assets/Exibido%20estoque%20de%20produto%20id%207.png)  


## Assíncrono

# 1- Teste Assíncrono com DTO:  
Objetivo: Validar o fluxo de comunicação assíncrona entre storefront e warehouse via RabbitMQ, processando um PedidoDTO e alterando o estoque no H2.  

![Criando e enviando pedido](/assets/Teste%20Assíncrono%20criação%20pedido%202x.png)  
![Terminal Warehouse após receber pedido](/assets/Teste%20Assíncrono%20de%20Pedido%20-%20resulta%20terminal.png)  

No RabbitMQ Management:  
O contador de mensagens deve subir quando o storefront publica, e zerar quando o warehouse consome.  

![Contador mensagem](/assets/Contador%20de%20Mensagens.png)  

 H2:  
         SELECT * FROM ESTOQUE WHERE PRODUTO_ID IN (6,7);  
o	As quantidades diminuíram de acordo com o pedido.  

![Estoque antes](/assets/Estoque%20do%20warehouse%20quantidades.png)  
![Estoque depois](/assets/h2%20Estoque%20warehouse%202.png)  

# 2- Teste de Estoque Insuficiente  
Objetivo: Garantir que o sistema não gere estoque negativo e alerte quando a quantidade solicitada for maior que a disponível.  
Cenário  
•	Produto 6 → tem 3, vai pedir 1 → baixa para 2   
•	Produto 7 → tem 1, vai pedir 3 → não baixa, alerta de saldo insuficiente exibido no console.  

![Criando pedido](/assets/Teste%20Assíncrono%20criação%20pedido%202x.png)  

Console mostrando o aviso de estoque insuficiente na última linha  
![Terminal do warehouse após pedido](/assets/Teste%20Assíncrono%20estoque%20insuficiente%20resultado%20terminal.png)  
 
Print do H2 confirmando que o Produto 7 manteve a quantidade original e Produto 6 houve a baixa de 1 unidade ficando 2 em estoque:  

![Estoque antes](/assets/Estoque%20do%20warehouse%20quantidades.png)  
![Estoque depois](/assets/h2%20Estoque%20warehouse%201.png)  






