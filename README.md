# workshop-springboot-mongo-db

## Descrição do Projeto

Este projeto demonstra como criar uma API REST usando Spring Boot integrada com o banco de dados NoSQL MongoDB. O objetivo é exemplificar operações CRUD (Create, Read, Update, Delete) em coleções do MongoDB por meio de endpoints HTTP. É um recurso útil para aprender a interação entre aplicações Java Spring e bancos de dados NoSQL. A api simula uma rede social, onde temos usuários, que podem fazer posts e comentar em posts de outros usuários.

## 🚀 Tecnologias Utilizadas

- **Java 11 (ou superior)**
- **Spring Boot**
- **Spring Data MongoDB**
- **MongoDB**
- **Maven**
- **Postman**

## ⚙️ Instalação e Configuração

1. Clone este repositório:
    
    ```
    git clone https://github.com/LucasRibasCardoso/workshop-spring-boot-mongodb.git
    ```
    
2. Acesse o diretório do projeto:
    
    ```
    cd workshop-spring-boot-mongodb
    ```
    
3. Certifique-se de que possui:
    - Java 11 (ou superior)
    - Maven
    - MongoDB em execução (`localhost:27017`)
4. Configure o arquivo `application.properties` com a URI do MongoDB:
    
    ```
    spring.data.mongodb.uri=mongodb://localhost:27017/workshop-mongo
    ```
    
5. Execute o projeto:
    
    ```
    mvn spring-boot:run
    ```
    

## 📮 Como Usar a API

Você pode testar os endpoints com o Postman:

### Usuários

- **Buscar todos os usuários**
    
    `GET http://localhost:8080/users`
    
- **Buscar usuário por ID**
    
    `GET http://localhost:8080/users/{id}`
    
- **Adicionar um usuário**
    
    `POST http://localhost:8080/users`
    
    **Body:**
    
    ```
    {
      "name": "João da Silva",
      "email": "joao.silva@gmail.com"
    }
    ```
    
- **Deletar usuário**
    
    `DELETE http://localhost:8080/users/{id}`
    
- **Atualizar um usuário**
    
    `PUT http://localhost:8080/users/{id}`
    
    **Body:**
    
    ```
    {
      "name": "Maria do Carmo",
      "email": "maria.carmo@gmail.com"
    }
    ```
    
- **Buscar posts de um usuário**
    
    `GET http://localhost:8080/users/{id}/posts`
    

### Posts

- **Buscar post por ID**
    
    `GET http://localhost:8080/posts/{id}`
    
- **Buscar posts por título (contendo string)**
    
    `GET http://localhost:8080/posts/titlesearch?text=partiu`
    
- **Buscar posts por string e intervalo de datas**
    
    `GET http://localhost:8080/posts/fullsearch?text=boa&minDate=2018-03-22&maxDate=2018-03-24`
    

## 🔍 Consultas Customizadas com @Query

Consultas personalizadas utilizando MongoDB com Spring Data:

```
@Query("{ 'title':  { $regex:  ?0, $options: 'i' } }")
List<Post> searchTitle(String text);
```

```
@Query("{ $and: [ {date:  {$gte: ?1}}, {date: {$lte:  ?2}}, { $or:  [ { 'title':  { $regex:  ?0, $options: 'i' } }, { 'body':  { $regex:  ?0, $options: 'i' } }, { 'comments.text':  { $regex:  ?0, $options: 'i' } } ] } ] }")
List<Post> fullSearch(String text, Date minDate, Date maxDate);
```

1. `searchTitle(String text)`: retorna posts cujo título contenha a string `text` (busca case-insensitive).
2. `fullSearch(String text, Date minDate, Date maxDate)`: retorna posts com `text` em título, corpo ou comentários, entre as datas `minDate` e `maxDate`.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT.

## 👨‍💻 Autor

Desenvolvido por [Lucas Ribas Cardoso](https://github.com/LucasRibasCardoso).
