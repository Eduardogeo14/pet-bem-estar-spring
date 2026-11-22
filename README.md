# Sistema Pet Bem-Estar

Projeto desenvolvido para a disciplina de Programação para Web I (N2). O sistema permite o gerenciamento de clientes (tutores) e seus respectivos bichos de estimação, com autenticação de usuários.

## Nome do Banco de Dados
O projeto utiliza o MySQL. O banco configurado é: `petbemestar_spring`

## Como executar
1. Crie o banco de dados `petbemestar_spring` no MySQL.
2. No terminal, na raiz do projeto, execute: `./mvnw spring-boot:run` (ou `.\mvnw.cmd spring-boot:run` no Windows).
3. Acesse `http://localhost:8080/login`.

## O que foi feito nesta etapa (N2)

1. **Camada de Serviço:** Foram criadas as classes `TutorService`, `BichoService` e `UsuarioService`. Os controllers delegam as operações para os serviços, e apenas os serviços acessam os DAOs.
2. **Regra de Exclusão:** O sistema impede que um tutor com bichos seja removido. O `TutorService` lança uma `RegraDeNegocioException` com uma mensagem amigável, exibida na tela de listagem.
3. **Telefone Único:** O `TutorService` valida se o telefone já pertence a outro tutor (cadastro e edição), impedindo duplicações e exibindo o erro no formulário.
4. **Ficha do Tutor:** Página de detalhes acessível pelo nome do tutor, exibindo nome, telefone e todos os bichos vinculados (consulta JPQL no DAO).
5. **Login e Sessão:** Implementado com `BCryptPasswordEncoder` para criptografia de senha. Um `LoginInterceptor` protege as rotas do sistema, redirecionando usuários não logados para a tela de login.
6. **Identidade Visual:** Aplicação da paleta de cores da cliente (Verde, Laranja, Fundo Creme) e logo no topo de todas as páginas.

## Tecnologias Utilizadas
- Java 17 / Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML / CSS
