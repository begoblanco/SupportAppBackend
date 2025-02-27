## Support App
Una startup nos ha solicitado una plataforma web para que sus empleados puedan solicitar un soporte técnico a su departamento informático en caso de necesidad.

## Server
localhost:8080

localhost:8080/api/support-request

mvn spring-boot:run to run app

localhost:8080/h2-console to check database

## Endpoints
<p>GET localhost:8080/api/support-requests/all</p>
<p>GET localhost:8080/api/support-requests/{id}</p>
<p>POST localhost:8080/api/support-requests/add</p>
<p>PUT localhost:8080/api/support-requests/update/{id}</p>
<p>DELETE localhost:8080/api/support-requests/delete/{id}</p>


## UML Diagram

![supportapp drawio](https://github.com/user-attachments/assets/9f12d53f-b996-422f-86bb-15dbb009acee)


## Postman Endpoints tested

### GET /all

<img src = https://github.com/user-attachments/assets/5178f886-4700-4f76-a15b-359a48f79d0e>


### GET /{id}

![support2](https://github.com/user-attachments/assets/53c95ad2-7140-4137-a131-d4dce49860f9)


### POST /add

![support3](https://github.com/user-attachments/assets/a8eb998b-6bb1-4bce-b61d-6e4d4d103073)


### PUT /update/{id}

![support4](https://github.com/user-attachments/assets/1c396a41-ed83-4f34-aa6c-febb146ceb95)


### DELETE /delete/{id}

![support5](https://github.com/user-attachments/assets/96840d8a-b551-498d-bb92-41cd3b7a9ad2)

## Tools
Spring Boot

Mockito

Postman
