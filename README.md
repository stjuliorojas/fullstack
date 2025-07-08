# Proyecto de Despliegue con Docker

Este proyecto muestra cómo desplegar una aplicación full‑stack (Frontend Angular, Backend Spring Boot y Base de Datos MySQL)
de forma reproducible y portátil usando Docker y Docker Compose.

---

## Requisitos previos

1. **Descargar e instalar Docker Desktop**

   * Windows y macOS: [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)
   * Linux: siga la guía oficial para su distribución en [https://docs.docker.com/engine/install](https://docs.docker.com/engine/install)

2. **Verificar instalación**

   ```bash
   docker --version      # Debe mostrar la versión instalada
   docker-compose --version  # (o docker compose version si usa CLI v2)
   ```

---
## generar el jar del proyecto 


## 1. Crear los Dockerfile

### 1.1 Frontend (Angular + NGINX)
dentro de nuestro proyecto de angular creamos un nuevo file llamado DockerFile con la D mayúscula

Ubicado en `front-end/app-front/Dockerfile`:

```dockerfile
# Imagen base de NGINX para servir Angular
FFROM node:20-alpine as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Etapa 2: Servidor NGINX
FROM nginx:stable-alpine
COPY --from=build /app/dist/app-front /usr/share/nginx/html
EXPOSE 80
```

### 2.2 Backend (Spring Boot)
dentro de nuestro proyecto de springboot creamos un nuevo file llamado "DockerFile" con la D mayúscula

Ubicado en `back-end/app-aut/Dockerfile`:

```dockerfile
# Imagen base de Java
FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/app-aut-0.0.1-SNAPSHOT.jar
# Directorio de trabajo
WORKDIR /app

# Copiar jar compilado
COPY ${JAR_FILE} app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando por defecto
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 3. Configurar `docker-compose.yml`

En la raíz del proyecto, cree o edite `docker-compose.yml` con el siguiente contenido:

```yaml
version: '3'

services:
  mysql:
    image: mysql:8.0.33
    container_name: mysql-db
    ports:
      - "3307:3306"
    environment:
      MYSQL_ROOT_PASSWORD: usersql
      MYSQL_PASSWORD: usersql
      MYSQL_DATABASE: seg
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 10s
      retries: 10
    restart: always

  backend:
    build: /back-end/app-aut
    ports:
      - "8080:8080"
    environment:
      DB_URL: jdbc:mysql://mysql:3306/seg?createDatabaseIfNotExist=true&serverTimeZone=UTC
      DB_USER_NAME: root
      DB_PASSWORD: usersql
    depends_on:
      mysql:
        condition: service_healthy
    restart: always

  frontend:
    build: ./front-end/app-front
    ports:
      - "4200:80"
    restart: always
```

---

## 4. Levantar los servicios

Ejecute el siguiente comando para construir las imágenes y arrancar los contenedores en segundo plano:

# abrimos la terminal del cmd, y entramos a la raiz de nuestro proyecto con la ruta especificada
```bash
cd C:\Users\nombre-pc\OneDrive\Escritorio\Docker\fullstack
```

# una vez dentro de nuestro archivo raiz ejecutamos el siguiente comando: 
```bash
docker-compose up --build -d  # Construye todas las imágenes y levanta los contenedores en segundo plano
```

Luego, verifique que todos los servicios estén activos:

```bash
docker-compose ps  # Lista los contenedores en ejecución y su estado para verificar que todo esté activo```

---

## 5. Acceder a la aplicación

* **Frontend (Angular):** [http://localhost:4200](http://localhost:4200)
* **Backend (Spring Boot API):** [http://localhost:8080](http://localhost:8080)
* **MySQL** (solo para aplicaciones): host `mysql-db`, puerto `3306` (externo `3307`), usuario `root`, contraseña `usersql`, base `seg`

---

## 6. Comandos útiles

* Ver logs en tiempo real:

  ```bash
  docker-compose logs -f  # Muestra los logs de todos los servicios en tiempo real, útil para depuración
  ```

- Detener y eliminar contenedores, redes y volúmenes:

  ```bash
  docker-compose down --volumes --remove-orphans  # Detiene y elimina contenedores, redes y volúmenes asociados
````

* Reconstruir solo un servicio:

  ```bash
  docker-compose build <servicio>
  ```


---

## 7. Estructura de carpetas

```

.
├── front-end/
│   └── app-front/
│       ├── Dockerfile
│       └── ... (código Angular)
├── back-end/
│   └── app-aut/
│       ├── Dockerfile
│       └── ... (código Spring Boot)
├── db/
│   └── init.sql
├── docker-compose.yml
└── README.md

```

---

## 8. Referencias

- Docker Desktop: https://www.docker.com/products/docker-desktop  
- Docker Compose: https://docs.docker.com/compose/  
- Dockerfile reference: https://docs.docker.com/engine/reference/builder/  

```
