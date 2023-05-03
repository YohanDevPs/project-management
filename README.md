# Status: Prototipagem :hammer:

# Objetivo
- Desenvolver uma aplicação de gerenciamento de vendas e abastecimento para micros empreendedores. Aqui estão algumas funcionalidades: filtros de clientes e fornecedores por status de pagamento e entrega, geração de relatórios e emissao de notas.
- Oferecer oportunidade de desenvolvimento profissional, portifolio e neetwork entre os membros da equipe.

### Funcionalidades
  
- Cadastro/ deleção/ atualização/ listar e achar um de cliente
- Cadastro/ deleção/ atualização do endereço do cliente (cliente pode ter mais de um endereço talvez devemos listar os endereços, mais não é importante, design decide)
- Cadastro/ deleção/ atualização/ listar e achar uma venda do cliente
- **Recursos de filtros dos clientes (Resgatar bom cliente = (período entre data inicial e final) AND (valor total de compras durante o período), além de outros filtros, status de pagamento e etc.**
- Adicionar/atualizar/deletar e achar estoques, será possível vender para cliente quando retirar de estoque, assim como devemos adicionar no estoque em reabastecimento.
- Relatórios sobre os custos de vendas e compras
- Cadastro/ deleção/ atualização/ listar e achar fornecedores.
- Cadastro/ deleção/ atualização/ listar e achar operações de abastecimentos dos fornecedores.
- **Chat com cliente, abrir chat do WhatsApp.**
- **Definir como será tratada inconsistência de dados.**

### Back-end
- Java 17 :white_check_mark:
- Spring Boot 3.0.2 :white_check_mark:
- Arquitetura RESTful :white_check_mark:
- Integração com MySql :white_check_mark:
- Testes com JUnit 5 e Mockito
- Migrations com Flyway 
- Implementação de testes de integração com Rest assured e Testcontainers
- Implementação de autentificação de usuarios com Spring Security
- Desenvolver Relatórios com JasperReports
- Implementar busca paginada
- Dockerização
- Implementar na AWS

### Front-end (Não iniciado)

- Será desenvolvido em Angular.

### Alguns Wireframes - Por:  [Gabriel de Matos](https://www.behance.net/gabrieldematos)

<details>
  <summary>Clique aqui para ver a lista de clientes</summary>
  
![image](https://user-images.githubusercontent.com/87953006/235389671-b8465326-457f-43c5-86a9-e683aa437283.png)
</details>

<details>
  <summary>Clique aqui para ver a Login</summary>
  
![image](https://user-images.githubusercontent.com/87953006/235389717-9c211e45-cf2c-4424-8b98-40db3a158328.png)
</details>

<details>
  <summary>Clique aqui para ver a lista de produtos</summary>
  
 ![image](https://user-images.githubusercontent.com/87953006/235389769-eb49ac7a-33d5-44b0-9962-82efbc91f6ee.png)
</details>


### Diagrama do banco de dados nesse momento (Será reorganizado e modificado com o tempo)
<details>
  <summary>Clique aqui para ver o diagrama</summary>
  
![image](https://user-images.githubusercontent.com/87953006/234709651-41887f7e-a2b3-4ca9-885c-d38d0274339c.png)
</details>


