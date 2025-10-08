# Restaurant API (Spring Boot)

API REST para gestionar **Clientes**, **Platillos** y **Pedidos** con relación muchos-a-muchos vía `DetallePedido`.

## Requisitos
- Java 21
- Maven 3.9+

## Ejecutar en desarrollo (H2 en memoria)
```bash
mvn spring-boot:run
```

## Endpoints
- **Clientes**: `/garufa/public/v1/clientes`
- **Platillos**: `/garufa/public/v1/platillos`
- **Pedidos**: `/garufa/public/v1/pedidos`
  - Agregar platillo: `POST /garufa/public/v1/pedidos/{idPedido}/platillos/{idPlatillo}`
  - Quitar platillo: `DELETE /garufa/public/v1/pedidos/{idPedido}/platillos/{idPlatillo}`
  - Listar platillos: `GET /garufa/public/v1/pedidos/{idPedido}/platillos`

## Producción (MySQL/Aiven)
Usa `src/main/resources/application-prod.properties` con tus credenciales y arranca con:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
