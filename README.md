<div align="center">

# 🏗️ Examen Parcial 2 — Diseño de Aplicaciones Distribuidas

**Arquitectura de Microservicios con Spring Boot · Spring Cloud · Docker · JWT**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-2025-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/compose/)
[![MySQL](https://img.shields.io/badge/MySQL-8.4-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)

</div>

---

## 📐 Arquitectura del Sistema

```
                          ┌─────────────────────────────┐
                          │   ms-admin-config-server     │
                          │      :8888 (Config)          │
                          │   Spring Cloud Config Server │
                          └──────────────┬───────────────┘
                                         │ provee configuración
              ┌──────────────────────────▼──────────────────────────┐
              │             ms-admin-registry-server                 │
              │                  :8761 (Eureka)                      │
              │           Netflix Eureka Server                      │
              └──────────────────────────┬──────────────────────────┘
                                         │ registro y descubrimiento
                          ┌──────────────▼───────────────┐
          CLIENTES ───────►   ms-admin-api-gateway        │
         (HTTP/REST)       │      :8080 (Gateway)         │
                          │   Spring Cloud Gateway        │
                          └──┬─────────────┬──────────┬──┘
                             │             │          │
              ┌──────────────▼──┐  ┌───────▼──────┐  ┌▼────────────────┐
              │  ms-gestion-    │  │ ms-gestion-  │  │  ms-gestion-    │
              │  instructor     │  │   alumno     │  │    taller       │
              │    :8082        │◄─►    :8083     ◄─►     :8084        │
              │ OpenFeign/gRPC  │  │  OpenFeign   │  │  OpenFeign      │
              │    MySQL        │  │    MySQL     │  │    MySQL        │
              └─────────────────┘  └──────────────┘  └─────────────────┘

              ┌─────────────────────────────┐
              │        auth-service          │
              │           :8085             │
              │    JWT + Circuit Breaker     │
              │        PostgreSQL           │
              └─────────────────────────────┘
```

---

## 📦 Microservicios

| # | Servicio | Puerto | Patrón | BD | Descripción |
|---|---|---|---|---|---|
| 1 | `ms-admin-config-server` | `8888` | Config Server | — | Centraliza configuración externa vía Git |
| 2 | `ms-admin-registry-server` | `8761` | Service Registry | — | Registro y descubrimiento de servicios (Eureka) |
| 3 | `ms-admin-api-gateway` | `8080` | Gateway (BFF) | — | Punto único de entrada y enrutamiento |
| 4 | `ms-gestion-instructor` | `8082` | Hexagonal | MySQL | CRUD de instructores con validaciones |
| 5 | `ms-gestion-alumno` | `8083` | Hexagonal | MySQL | CRUD de alumnos con validaciones |
| 6 | `ms-gestion-taller` | `8084` | Hexagonal | MySQL | CRUD de talleres (compuesto) |
| 7 | `auth-service` | `8085` | Seguridad | PostgreSQL | JWT + Resilience4j (Circuit Breaker + Retry) |

---

## 🛠️ Tecnologías

| Categoría | Tecnología |
|---|---|
| **Lenguaje** | Java 21 |
| **Framework** | Spring Boot 3.5 |
| **Cloud** | Spring Cloud 2025 (Config, Eureka, Gateway, OpenFeign) |
| **Seguridad** | Spring Security + JWT + Resilience4j |
| **Persistencia** | Spring Data JPA + Hibernate |
| **Bases de Datos** | MySQL 8.4 (negocio) · PostgreSQL 16 (auth) |
| **Contenedores** | Docker + Docker Compose |
| **Documentación** | Springdoc OpenAPI 2.x (Swagger UI) |
| **Build** | Apache Maven |

---

## 🚀 Cómo ejecutar

### Opción A — Docker Compose (recomendado)

> Requiere: Docker Desktop instalado y corriendo

**Paso 1: Compilar todos los microservicios**

```bash
# Desde la carpeta raíz examen-parcial-2/
for svc in ms-admin-config-server ms-admin-registry-server ms-admin-api-gateway \
           ms-gestion-instructor ms-gestion-alumno ms-gestion-taller auth-service; do
  echo "▶ Compilando $svc..."
  cd $svc && ./mvnw clean package -DskipTests -q && cd ..
done
echo "✅ Todos los JAR compilados"
```

**Paso 2: Levantar toda la infraestructura**

```bash
docker compose up --build
```

**Paso 3: Verificar que los servicios están vivos**

```bash
# Ver servicios corriendo
docker compose ps

# Ver logs de un servicio específico
docker compose logs -f ms-gestion-instructor
```

---

### Opción B — Ejecución local (sin Docker)

> Requiere: MySQL y PostgreSQL corriendo en `localhost`

Iniciar en este orden estricto:

```
1️⃣  ms-admin-config-server      → esperar que esté UP
2️⃣  ms-admin-registry-server    → esperar que esté UP
3️⃣  ms-admin-api-gateway        → esperar que esté UP
4️⃣  auth-service                → (en paralelo con 5, 6, 7)
5️⃣  ms-gestion-instructor       → (en paralelo)
6️⃣  ms-gestion-alumno           → (en paralelo)
7️⃣  ms-gestion-taller           → (en paralelo)
```

---

## 🌐 URLs de acceso

| Servicio | URL | Descripción |
|---|---|---|
| **Eureka Dashboard** | http://localhost:8761 | Ver servicios registrados |
| **API Gateway** | http://localhost:8080 | Punto de entrada principal |
| **Auth Service** | http://localhost:8080/api/auth | Login y registro |
| **Swagger — Instructor** | http://localhost:8082/swagger-ui.html | Documentación API |
| **Swagger — Alumno** | http://localhost:8083/swagger-ui.html | Documentación API |
| **Swagger — Taller** | http://localhost:8084/swagger-ui.html | Documentación API |

---

## 🔐 Autenticación JWT

Todas las rutas protegidas requieren el header `Authorization: Bearer <token>`.

### 1. Registrar usuario

```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "fullName": "Julio Rodriguez",
  "dni": "12345678",
  "email": "julio@upeu.edu.pe",
  "universityCode": "202101234",
  "phone": "987654321",
  "username": "julioFabian",
  "password": "upeu2024"
}
```

### 2. Login (obtener token)

```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "julioFabian",
  "password": "upeu2024"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "roles": ["ROLE_USER"],
  "username": "julioFabian",
  "error": null
}
```

---

## 📡 Endpoints principales (vía Gateway :8080)

### Instructores
| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/instructores` | Listar todos |
| `GET` | `/api/instructores/{id}` | Obtener por ID |
| `POST` | `/api/instructores` | Crear instructor |
| `PUT` | `/api/instructores/{id}` | Actualizar |
| `DELETE` | `/api/instructores/{id}` | Eliminar |

**Body (crear/actualizar):**
```json
{
  "nombre": "Carlos Mendoza",
  "especialidad": "Spring Boot y Microservicios",
  "correo": "carlos@upeu.edu.pe"
}
```

### Alumnos
| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/alumnos` | Listar todos |
| `GET` | `/api/alumnos/{id}` | Obtener por ID |
| `POST` | `/api/alumnos` | Crear alumno |
| `PUT` | `/api/alumnos/{id}` | Actualizar |
| `DELETE` | `/api/alumnos/{id}` | Eliminar |

**Body (crear/actualizar):**
```json
{
  "nombre": "Ana García",
  "codigo": "2021-100234",
  "correo": "ana@upeu.edu.pe"
}
```

### Talleres
| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/talleres` | Listar todos |
| `GET` | `/api/talleres/{id}` | Obtener por ID |
| `POST` | `/api/talleres` | Crear taller |
| `PUT` | `/api/talleres/{id}` | Actualizar |
| `DELETE` | `/api/talleres/{id}` | Eliminar |

**Body (crear/actualizar):**
```json
{
  "nombre": "Taller de Microservicios",
  "descripcion": "Implementación con Spring Cloud",
  "instructorId": 1,
  "alumnoId": 1
}
```

---

## 🗄️ Bases de datos

| Base de Datos | Motor | Servicios que la usan |
|---|---|---|
| `instructor_db` | MySQL 8.4 | `ms-gestion-instructor` |
| `alumno_db` | MySQL 8.4 | `ms-gestion-alumno` |
| `taller_db` | MySQL 8.4 | `ms-gestion-taller` |
| `auth_db` | PostgreSQL 16 | `auth-service` |

> Las tablas se crean automáticamente con `spring.jpa.hibernate.ddl-auto=update`

---

## 📋 Entregables

- [x] Código fuente de cada microservicio
- [x] Documentación API con Swagger/OpenAPI
- [x] Diagrama de arquitectura
- [x] Configuraciones por ambiente (dev, prod) en `config-repo/`
- [x] Scripts `docker-compose.yml` para despliegue
- [x] Autenticación JWT con Resilience4j (Circuit Breaker + Retry)
- [x] Colección Postman para pruebas

---

## 👨‍💻 Autor

**Julio Fabián Rodríguez Bazán**  
Estudiante de Ingeniería de Sistemas — Universidad Peruana Unión  
Curso: Diseño de Aplicaciones Distribuidas

---

<div align="center">
  <sub>Desarrollado con ☕ Java y mucho Spring Boot</sub>
</div>
