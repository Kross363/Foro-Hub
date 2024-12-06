# Foro Hub

## Descripción
Foro Hub es una aplicación de backend que replica el funcionamiento de un foro en el que los usuarios pueden interactuar con tópicos. El proyecto está desarrollado en Java y utiliza el framework Spring Boot para implementar una API REST. Esta API permite gestionar tópicos y usuarios con funcionalidades CRUD y un sistema de autenticación y autorización.

## Tecnologías Utilizadas
- **Lenguaje**: Java 17
- **Gestor de dependencias**: Maven 4
- **Framework**: Spring Boot 3
- **Base de datos**: MySQL

## Dependencias

### Generales
- **Lombok**: Para reducir la verbosidad del código mediante la generación automática de getters, setters y otros métodos comunes.
- **Spring Web**: Para crear y gestionar los controladores REST.
- **Spring Boot DevTools**: Para facilitar el desarrollo mediante el reinicio automático de la aplicación.
- **Spring Security**: Para implementar autenticación y autorización.

### Base de Datos
- **MySQL Driver**: Para la conexión con la base de datos MySQL.
- **Spring Data JPA**: Para interactuar con la base de datos usando repositorios.
- **Flyway Migration**: Para gestionar las migraciones de esquemas de la base de datos.
- **Validation**: Para aplicar reglas de negocio y validar los datos de entrada.

## Funcionalidades
La API REST de Foro Hub permite:

### Gestión de Tópicos
- **Crear un nuevo tópico**: Los usuarios pueden publicar nuevos tópicos.
- **Listar todos los tópicos**: La API proporciona una lista completa de todos los tópicos registrados.
- **Mostrar un tópico específico**: Se puede consultar la información de un tópico en particular mediante su identificador.
- **Actualizar un tópico**: Permite modificar los detalles de un tópico existente.
- **Eliminar un tópico**: Los usuarios pueden eliminar un tópico.

### Gestión de Usuarios
- Crear, listar, actualizar y eliminar información relacionada con los usuarios registrados en la plataforma.

## Características de la API
- **Modelo REST**: Todas las rutas están diseñadas siguiendo las mejores prácticas REST.
- **Validaciones**: Se implementan validaciones de reglas de negocio para asegurar la consistencia de los datos.
- **Persistencia**: Integración con MySQL para almacenar de forma segura la información en la base de datos.
- **Seguridad**: Autenticación y autorización implementadas mediante Spring Security.

## Endpoints Principales
Tópicos
- POST /topicos: Crear un nuevo tópico.
- GET /topicos: Listar todos los tópicos.
- GET /topicos/{id}: Mostrar un tópico específico.
- PUT /topicos/{id}: Actualizar un tópico.
- DELETE /topicos/{id}: Eliminar un tópico.
Usuarios
- POST /usuarios: Crear un nuevo usuario.
- GET /usuarios: Listar todos los usuarios.
- GET /usuarios/{id}: Mostrar un usuario específico.
- PUT /usuarios/{id}: Actualizar un usuario.
- DELETE /usuarios/{id}: Eliminar un usuario.

## Seguridad

La API implementa un sistema de autenticación con JWT y encriptacion de tipo bcrypt y autorización utilizando Spring Security. Los usuarios deben autenticarse para acceder a los endpoints protegidos.

