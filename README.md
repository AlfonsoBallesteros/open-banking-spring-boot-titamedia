# test ecomerce experimentality

## Desarrollo

Documentacion: 
http://localhost:8080/swagger-ui/index.html

Desde la base de datos correr:

```
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

Para iniciar su aplicación en el perfil de desarrollador, ejecute:

```
./mvnw
```
## Construccion para produccion

### Packaging como jar

To build the final jar and optimize the product application for production, run:

```
./mvnw -Pprod
```

Para asegurarse de que todo funcionó, ejecute:

```
java -jar target/*.jar
```

### Packaging as war

Para empaquetar su aplicación como una war para implementarla en un servidor de aplicaciones, ejecute:

```
./mvnw -Pprod
```

## Uso de Docker para simplificar el desarrollo (opcional)

Then run:

```
docker run -it $(docker build -q .)
```