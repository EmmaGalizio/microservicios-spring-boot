
# Microservicios con Spring Boot - Nivel 1

Proyecto simple que consiste de tres aplicaciones en Spring Boot para poner en práctica el diseño de una arquitectura basada en microservicios. 
Además se tran temas como la comunicación entre servicios, descubrimiento de servicios y balanceo de cargas.

## Comunicación
Para comunitar los servicios se utiliza una instancia de RestTemplate, y una instancia de WebClient. Toda la comunicación en este ejemplo es síncrona.

## Descubrimiento de servicios
Para el descubrimiento de servicios se utiliza una aplicación servidor que contiene un registro de todos los servicios que se están ejecutando. 
Cuando un servicio se inicia, lo primero que hace es notificar al servidor de descubrimiento para que lo registre y pueda saber en donde está cada servicio.

Para que un cliente pueda interactuar con un servicio hay dos formas en que puede hacerlo.
Por un lado, está el descubrimiento de servicios del lado del cliente, en este caso, el cliente le pregunta al servidor de descrubrimiento dónde lo puede encontrar, 
y si se encuentra registrado el servidor de descubrimiento le brinda esa información, y ahí el cliente puede hacer las solicitudes que necesite al servicio.
Esta opción tiene la desventaja de que es necesario realizar un salto extra durante la comunicación.

Por otro lado, está el descrubrimiento de servicios del lado del servidor. En este caso, el cliente directamente le pasa la solicitud completa al servidor de descrubrimiento 
y es el servidor el que interactúa con el servicio y le da la respuesta al cliente (similar a un API Gateway supongo, si es que no es justamente eso). 
La desventaja de esto es que el servidor de descrubrimiento puede generar un cuello de botella.

Spring Cloud utiliza descrubrimiento de servicios del lado del cliente. Para eso utiliza Eureka, es un servicio open source para proporcionar servicios de balanceo de carga y descrubrimiento de servicios.