# Clientes API

## Contenido
- [Introduccion](#introduccion)
- [Nuevas Funcionalidades](#nuevas-funcionalidades)
- [Arquitectura](#arquitectura)
- [Patrones y Principios](#patrones)
- [Iniciar](#iniciar)
- [Test](#test)
- [Documentacion](#documentacion)
- [Contacto](#contacto)

## Introduccion
Una empresa está desarrollando un sistema de gestión de clientes para ofrecer una mejor experiencia a sus usuarios. Se requiere un microservicio que permita manejar  
el registro, consulta y análisis de datos de clientes. El objetivo es que este servicio sea escalable, seguro y cumpla con las mejores prácticas de desarrollo de software.

## Nuevas Funcionalidades

Crear nuevos clientes mediante un endpoint que permita registrar nombre,  apellido, edad y fecha de nacimiento.
Consultar un conjunto de métricas sobre los clientes existentes, como el promedio de edad y la desviación estándar de las edades.
Listar todos los clientes registrados con sus datos completos y un cálculo derivado, como una fecha estimada para un evento futuro basado en los datos del cliente (por ejemplo, esperanza de vida).

- **Dependencies**  
  Dentro del archivo `pom.xml` se agregaron las siguientes dependencias:
   - `spring-boot-starter-web`
   -  `spring-boot-starter-json`
   - `spring-boot-starter-data-jpa`
   - `h2`
   - `spring-boot-devtools`
   - `lombok`
   - `jakarta.persistence-api`
   - `mockito`
   - `springdoc-openapi-starter-webmvc-ui`


- **Entidad modelo**  
  Definimos el modelo para representar un registro de cliente. Lo podemos encontrar en el paquete `entity`
- **API Request y Response**  
  Representa la solicitud y respuesta de la API. La podemos encontrar en el paquete `dto`
- **Client Service**  
  Capa de servicio donde añadiremos los casos de uso necesarios para llevar a cabo toda la lógica de negocio. La podemos encontrar en el paquete `service`
- **Client Controller**  
  En esta capa de control, solo podemos actuar como punto de interacción con el mundo externo (como usuarios, sistemas externos o interfaces gráficas) y ser el núcleo de la aplicación.
- **Application Configuration**  
  Archivo `application.properties` donde tenemos todas nuestras configuraciones de ambiente
- **Main Application**  
  Clase principal donde iniciamos nuestra aplicación Spring Boot.

Cada entidad de cliente esta definido por el siguiente esquema:
- `id`: identificador para cada cliente
- `name`: nombre del cliente
- `last_name`: apellido del cliente
- `date_of_birth`: fecha de nacimiento del cliente

### Arquitectura

La arquitectura de la aplicación sigue el patrón de diseño MVC (Modelo-Vista-Controlador) que se divide en tres capas principales:
- **Modelo**: Representa la estructura de datos y las reglas de negocio. package respository, entity y service
- **Vista**: Presenta los datos al usuario y maneja la interacción. package dto
- **Controlador**: Actúa como intermediario entre el modelo y la vista. package controller


![mvc.png](clients%2Fclients%2Fsrc%2Fmain%2Fresources%2Fimg%2Fmvc.png)

### Patrones y Principios

Los patrones y principios que se aplicaron en el desarrollo de la aplicación son:
- **Dependency Injection (DI)**: Se utilizó la anotación `@Component, @Service y  @Controller` para inyectar dependencias en las clases.
- **Singleton**: Se utilizó la funcion calculateDateOfDeath() de la entidad modelo.
- **Open/Closed Principle**: Se utilizó las interfaces para abrir la extensión y cerrar la modificación.
- **Factory Method**: Se utilizó la anotación `@Bean y @Configuration` en la clase WebSecurityConfig.
- **Inversion of Control (IoC)**: Se utilizó en el controller y en el service para inyectar dependencias en las clases.

## Iniciar

### Local
Independientemente del IDE que utilices, debemos posicionarnos en la carpeta raiz clients\ y ejecutar el siguiente comando:

```  
docker-compose up -d  
```  
Para detener la ejecucion:

```  
docker-compose stop  
```  

### IMPORTANTE
Para poder probar nuestros enpoints en postman, ya sea desde **local** o con endpoints de **aws** es necesario ingresar con el tipo de autenticacion basica con los siguientes datos:

|  Username|Password  |
|--|--|
|  admin| admin |

Caso que no lo hagamos vamos a tener estos errores cuando ejecutemos nuestros endpoints:

![listclientfail.png](clients%2Fclients%2Fsrc%2Fmain%2Fresources%2Fimg%2Flistclientfail.png)

Por favor ingresar con las siguientes configuraciones de esta forma:

![listclientok.png](clients%2Fclients%2Fsrc%2Fmain%2Fresources%2Fimg%2Flistclientok.png)

- Accede a la aplicación en http://localhost:8080/listclientes desde `postman` y probar los siguientes endpoints:

### Crear Cliente

#### POST
##### Request

``` 
curl --location 'localhost:8080/creacliente'  
```   
##### Responses

``` 
200 Ok  
```   
### Listar Clientes
##### Request

``` 
curl --location 'localhost:8080/listclientes'  
``` 
#### GET
##### Responses
```json 
[
  {
    "nombre": "Federico",
    "apellido": "Garcia",
    "edad": 30,
    "fecha_de_nacimiento": "1994-01-01",
    "esperanza_de_vida": "2074-01-01"
  },
  {
    "nombre": "Maria",
    "apellido": "Lopez",
    "edad": 25,
    "fecha_de_nacimiento": "1999-05-12",
    "esperanza_de_vida": "2079-05-12"
  },
  {
    "nombre": "Juan",
    "apellido": "Perez",
    "edad": 40,
    "fecha_de_nacimiento": "1983-07-20",
    "esperanza_de_vida": "2063-07-20"
  }
]
```   
### Obtener KPIS de Clientes

#### GET

##### Request
``` 
curl --location 'localhost:8080/kpideclientes'  
```  
##### Responses

```json 
{    
"edad_promedio":  28.75,  
  
"edad_desviacion":  7.39509972887452  
}  
```   
### AWS
En este apartado se pasan los endpoints donde esta alojada la api en la nube de AWS:
- GET clients: 54.91.58.16/listclientes
- GET clientskpi: 54.91.58.16/kpideclientes
- POST creaclients: 54.91.58.16/creacliente

Una pequeña muestra de como esta corriendo en la instancia EC2 de aws:

![awsok.png](clients%2Fclients%2Fsrc%2Fmain%2Fresources%2Fimg%2Fawsok.png)

## Test
Los test se alojan en la carpeta `src\test\java`, pero se deben ejecutar desde `\clientsproject\clients\clients` con el siguiente comando:

```
mvn test
```



## Documentación

Ejecutar la app desde local con docker e ingresar a la url de Swagger:
http://localhost:8080/swagger-ui/index.html#/

## Contacto

Para mas información, por favor contactar a:

- fede17rosales@gmail.com
- [Perfil de Linkedin](https://www.linkedin.com/in/federico-nicolas-rosales-cabrera-0b6092111/)
