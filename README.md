# Clientes API

## Contenido
- [Introduccion](#introduccion)
- [Nuevas Funcionalidades](#nuevas-funcionalidades)
- [Iniciar](#iniciar)
- [Contacto](#contacto)

## Introduccion
Una empresa está desarrollando un sistema de gestión de clientes para ofrecer una mejor experiencia a sus usuarios. Se requiere un microservicio que permita manejar
el registro, consulta y análisis de datos de clientes. El objetivo es que este servicio sea escalable, seguro y cumpla con las mejores prácticas de desarrollo de software.
## Nuevas Funcionalidades

- Crear nuevos clientes mediante un endpoint que permita registrar nombre,  apellido, edad y fecha de nacimiento. 
- Consultar un conjunto de métricas sobre los clientes existentes, como el promedio de edad y la desviación estándar de las edades.
- Listar todos los clientes registrados con sus datos completos y un cálculo derivado, como una fecha estimada para un evento futuro basado en los datos del cliente (por ejemplo, esperanza de vida).

1. **Dependencies**
   Dentro del archivo `pom.xml` se agregaron las siguientes dependencias:
   `spring-boot-starter-web`
   `spring-boot-starter-json`
   `spring-boot-starter-data-jpa`
   `h2`
   `spring-boot-devtools`
   `lombok`
   `jakarta.persistence-api`
   `mockito`
   `springdoc-openapi-starter-webmvc-ui`
   `spring-boot-starter-amqp`

2. **Entidad modelo**
   Definimos el modelo para representar un registro de cliente. Lo podemos encontrar en el paquete `entity`
3. **API Request y Response **
   Representa la solicitud y respuesta de la API. La podemos encontrar en el paquete `dto`
4. **Client Service**
   Capa de servicio donde añadiremos los casos de uso necesarios para llevar a cabo toda la lógica de negocio. La podemos encontrar en el paquete `service`
5. **Client Controller**
   En esta capa de control, solo podemos actuar como punto de interacción con el mundo externo (como usuarios, sistemas externos o interfaces gráficas) y ser el núcleo de la aplicación. 
6. **Application Configuration**
   Tenemos toda nuestras variables de configuracion en `application.properties`
7. **Main Application**
   Donde iniciamos nuestra aplicación Spring Boot.

Cada entidad de cliente esta definido por el siguiente esquema:
- `id`: identificador para cada cliente
- `name`: nombre del cliente
- `last_name`: apellido del cliente
- `date_of_birth`: fecha de nacimiento del cliente

## Iniciar
- Independientemente del IDE que utilices, puedes ejecutar `Run` o `Alt + Shift + F10`  para iniciar la aplicación.
- Accede a la aplicación en http://localhost:8080/listclientes desde `postman` o directamente ingresar a la url de Swagger: http://localhost:8080/swagger-ui/index.html#/
- Y ya puedes probar los siguientes endpoints:

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


## Contacto

Para mas información, por favor contactar a:

- fede17rosales@gmail.com
- [Linkedin](https://www.linkedin.com/in/federico-nicolas-rosales-cabrera-0b6092111/)