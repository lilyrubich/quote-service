## Quotes

A simple Spring boot application that demonstrates the usage of RESTful API using Spring boot, Liquibase and H2.

## Tools and Technologies used

* Java 20.0.2
* Spring boot 3.1.4
* H2
* Liquibase 4.24.0
* Maven
* JUnit 4.12
* Swagger


## Open H2 console

Database details are available in H2 console at the link below

```bash
http://localhost:8080/h2-console
```

Fill out the opened form with the following data:

```bash
JDBC URL: jdbc:h2:mem:quoteservice
User name: sa
Password password: password
```

## Explore Rest APIs

The app defines following APIs 
(or you can use swagger-ui at the link: http://localhost:8080/swagger-ui/index.html)
```
POST /quoteService/user/add

Example URL: http://localhost:8080/quoteService/user/add
Example Request body:

{
    "login":"lilian",
    "email": "lilian@java.com",
    "password": "lil12345"
}
```
```
POST /quoteService/quote/add

Example URL: http://localhost:8080/quoteService/quote/add
Example Request body:

{
    "content":"My first quote",
    "authorId": "0f33341b-d3fb-4f0b-6d6d-d1739dc4acb1"
}
```
```
POST /quoteService/quote/update

Example URL: http://localhost:8080/quoteService/quote/update
Example Request body:

{
    "id": "1f56791b-d3fb-0f0b-9a4f-d1739dc4acb1",
    "content": "No more internal power struggles",
    "rating": 5,
    "authorId": "0f33341b-d3fb-4f0b-6d6d-d1739dc4acb1"
}
```   
```
DELETE /quoteService/quote/delete?id={id}

Example URL: http://localhost:8080/quoteService/quote/delete?id=5f56791b-d3fb-4f0b-9a4f-d1739dc4acb1
```
```
GET /quoteService/quote/getQuotes

Example URL: http://localhost:8080/quoteService/quote/getQuotes
```
```
GET /quoteService/quote/getTop10

Example URL: http://localhost:8080/quoteService/quote/getTop10
```
```
GET /quoteService/quote/getWorst10

Example URL: http://localhost:8080/quoteService/quote/getWorst10
```
```
GET /quoteService/quote/getRandomQuote

Example URL: http://localhost:8080/quoteService/quote/getRandomQuote
```
```
GET http://localhost:8080/quoteService/vote/upvote?quoteId={quoteId}&userId={userId}

Example URL: http://localhost:8080/quoteService/vote/upvote?quoteId=1f56791b-d3fb-0f0b-9a4f-d1739dc4acb1&userId=0f33341b-d3fb-4f0b-6d6d-d1739dc4acb1
```
```
GET http://localhost:8080/quoteService/vote/downvote?quoteId={quoteId}&userId={userId}

Example URL: http://localhost:8080/quoteService/vote/downvote?quoteId=1f56791b-d3fb-0f0b-9a4f-d1739dc4acb1&userId=0f33341b-d3fb-4f0b-6d6d-d1739dc4acb1
```

You can test them using postman or any other rest client.