# user-service API

Aplicação simples em spring boot para cadastro de usuário utilizando spring security, spring jpa, swagger, mysql.

### Api Crud de usuarios

Essa aplicação contem os seguintes endpoints

		- (GET) http://localhost:8081/user/list (Endpoint para listar todos os usuarios)
		- (GET) http://localhost:8081/user/info/{id} (Endpoint para obter a informação do usuario selecionado)
		- (POST) http://localhost:8081/user/{id} (Endpoint para atualizar email) 
		- (POST) http://localhost:8081/user/ (Endpoint para criar uma conta usuario)

 - Obs.: para qualquer endpoint usado, exceto http://localhost:8081/user/, tera que inseria no Request Header o Authorization, 
 onde seu valor vira do microserviço de autenticação - Endpoint : 
 Concatenar o token_type + access_token. 
 
 
### Configuração da api

Para poder startar aplicação, colocar nas configurações o seguinte:

```sh
	$ --spring.config.location=src/main/resources/application.yml --spring.config.name=application.yml
````

