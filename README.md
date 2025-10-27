# infnet-gestao-de-clientes-de-varejo
Gestão de Clientes de Varejo para Pequenas Empresas.

#### Objetivo
Projeto para a disciplina de Arquitetura de Plataformas em Java.

#### Descrição do Projeto
Projeto construído em Spring Boot e Java 17.

Permite realizar o gerenciamento de clientes de lojas de varejo através de APIs REST.


#### Usando as APIs
Acesse o http://localhost:8080/swagger-ui/index.html

E execute as collections desejadas.

#### Acessando o Banco de Dados embarcado
H2 é um sistema de gerenciamento de banco de dados relacional leve, de código aberto e na memória, escrito em Java, comumente usado com Spring Boot para desenvolvimento e testes.

Acesse o http://localhost:8080/h2-console
- JDBC URL: Buscar nos logs da aplicação - Exemplo: Database available at 'jdbc:h2:mem:a81549e4-312f-40c9-8400-8107697449a6'
- Usuário: sa
- Senha: Em branco - não preencher



#### Features implementadas

* Feature 1 
   - Organização do pacote
   - Criação da classe de domínio
   - Classe de serviço
   - Interfaces
   - Data Loader - Leitura de arquivo
   - Injeção de dependência
   - Criação de endpoints (Rest Controller)

- Feature 2
   - Classes abstratas e herança
   - Incrementar atributos das classes de domínios
   - Criação de todos os endpoints CRUD e inativar cliente
   - CRUD para os vendedores
   
- Feature 3
   - Camada de persistência com Spring Data JPA
   - Habilitando Feign Client para serviços externos
   - Validação de dados de entrada com anotações
   - Uso de Response Entity para tratar componentenes de resposta ao cliente
   - Tratamento de Exceções
   
- Feature 4
   - Centralização de erros e tratamento de exceções com @ControllerAdvice
   - Criação de relacionamentos entre clientes e endereços
   	- Implementar relacionamentos (OneToMany, ManyToMany, ManyToOne, OneToOne)
      

