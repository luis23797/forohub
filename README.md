# Proyecto ForoHub
![Static Badge](https://img.shields.io/badge/Estado%20del%20proyecto-Terminado-green)
- [Descripcion del proyecto](#descripcion-del-proyecto)
- [Estado del proyecto](#estado-del-proyecto)
- [Caracteristicas de la aplicacion](#caracteristicas-de-la-aplicacion)
- [Instalacion](#instalacion)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
# Descripcion del proyecto
Este es un proyecto que consiste en la codificacion de una aplicacion Web Java que implementa JPA para el guardado de topicos de un foro, tiene autentificacion por medio de JWToken utilizando la libreria auth0

# Estado del proyecto
:heavy_check_mark: Proyecto terminado :heavy_check_mark:

# Caracteristicas de la aplicacion
ForoHub cuenta con los siguientes endpoints
* `/curso`
* `/topico` 
* `/login` 


Por medio de esta aplicacion Web se pueden hacer requisiones para hacer insercion y extraccion a la base de datos, utilizando el endpoint correspondiente para cada solicitud

## /login
Mediante este endpoint se puede obtener un JWToken para utilizar las demas requisiciones, debido a que forohub requiere de autorizacion para la consulta y creacion de topicos es importante considerar este endpoint


### Logearse
```
POST /login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "nombre": "usuario",
  "contraseña": "contraseña"
}
```
Devuelve un Token de autentificacion para ser utilizado en la app

## /curso
Mediante este endpoint se pueden crear y consultar cursos de diferentes categorias, por ahora las categorias admitidas son: "CIENCIA_DE_DATOS,
BACKEND,
FRONTEND".

### Obtener Cursos
```
GET /curso HTTP/1.1
Host: localhost:8080
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI
```
Esta requisicion devolvera una paginacion de Cursos en grupos de 10

### Crear cursos
```
POST /curso HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI

{
  "nombre": "Introduccion al desarrollo web",
  "categoria": "FRONTEND"
}
```

## /topico
Este endpoint es para la creacion, modificacio, lectura y eliminacion de topicos, permite cada una de estas solicitudes.

### Obtener Topicos
```
GET /topico HTTP/1.1
Host: localhost:8080
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI
```
Esta requisicion devolvera una paginacion de Topicos en grupos de 10


### Obtener topico

```
GET /topicos/1 HTTP/1.1
Host: localhost:8080
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI
```
Devuelve un topico en especifico

### Crear topicos
```
POST /topicos HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI

{
  "titulo": "¿Cómo usar Spring Boot?",
  "mensaje": "Necesito ayuda para entender controladores.",
  "autor_id": 1,
  "curso_id": 1
}
```
### Modificar un topico
```
PUT /topicos/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI

{
  "titulo": "¿Cómo usar Spring Boot?",
  "mensaje": "Necesito ayuda para entender controladores."
  "status": "true"
}
```
### Eliminar un topico
```
DELETE /topicos/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Authorization: Bearer TU_TOKEN_AQUI
```

# Instalacion 
* Clonar el repositorio usando:
```
git clone https://github.com/luis23797/forohub.git
```
* Crear una base de datos local y configurar los parametros de acceso a la base de datos en las propiedades de la aplicacion
```
// application.properties -> Archivo a editar

spring.datasource.url=jdbc:postgresql://${Tu_HOST}/forohub
spring.datasource.username=${Usuario_De_BD}
spring.datasource.password=${Contraseña_De_UBD}
```
# Tecnologias utilizadas
- Java
- Spring Web
- Spring JPA
- Flyway Migration
- Lombok
