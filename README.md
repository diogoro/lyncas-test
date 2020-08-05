# lyncas-test

Para iniciar a aplicação basta executar o arquivo docker-compose.yml, que está na raiz do repositório, executando o comando **docker-compose up**.

# Acesso à aplicação
Para acessar o frontend da aplicação basta digitar http://localhost:8080
Para o backend temos o endpoint pessoas http://localhost:8080/pessoas, que serve para consulta, criação, edição e exclusão de pessoas da aplicação e o endpoint source http://localhost:8080/source que retorna o endereço deste repositório.

A documentação swagger está em http://localhost:8080/swagger-ui.html

Para acessar os links acima basta informar o usuário admin e senha admin. O endpoint source não precisa de autenticação.

# Stack

 - Spring-boot
 - Thymeleaf
 - Mysql 

## Docker hub
https://hub.docker.com/r/diogoro/lyncas-test
