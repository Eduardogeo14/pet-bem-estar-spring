# Sistema Pet Bem-Estar

Projeto desenvolvido para a disciplina de Programação para Web I. O sistema permite o cadastro de tutores (clientes) e seus respectivos bichos de estimação.

## Funcionalidades
- CRUD completo de Tutores (Listar, Cadastrar, Editar, Remover).
- CRUD completo de Bichos, com escolha do dono através de um select.
- Listagem de bichos exibindo o nome do tutor.
- Validação de campos obrigatórios.

## Tecnologias Utilizadas
- Java / Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML / CSS

---

## Remover um cliente com bichos

Ao testar a remoção de um tutor que possui bichos cadastrados (como a Dona Rosa e seu cachorro), o sistema apresentou um erro **500 (Internal Server Error)**.

**Por que isso aconteceu?**
O banco de dados relacional protege a integridade referencial. Como a tabela de bichos possui uma chave estrangeira (`tutor_id`) apontando para a tabela de tutores, o banco impede que um tutor seja deletado enquanto houver bichos vinculados a ele. Se essa exclusão fosse permitida, os bichos ficariam "órfãos" no sistema, sem um dono válido.

**Como resolver o problema?**
Para a regra de negócio de um petshop, o mais indicado não é apagar o cliente do banco de dados, mas sim inativá-lo (mudar o status para inativo), mantendo seu histórico e seus bichos. Caso a exclusão real seja necessária, deve-se configurar a exclusão em cascata (Cascade) para apagar os bichos primeiro, ou apagar manualmente cada bicho do tutor antes de removê-lo.
