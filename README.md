# Quiz Master 🧠

Quiz Master es una robusta API REST y plataforma web monolítica desarrollada con **Spring Boot** para la creación, gestión y realización de evaluaciones interactivas. El proyecto destaca por implementar una arquitectura limpia en capas, seguridad avanzada mediante tokens y soporte para múltiples tipos de preguntas.

---

## 📸 Vista Previa del Proyecto

![Pantalla de Inicio](static/screenshots/panel.png)

---

![Panel de Gestión](static/screenshots/quiz.png)

---

## 🚀 Características Clave

* **Temáticas con Identidad Propia:** El juego no es un trivial genérico. Cuenta con un sistema de categorías dinámicas con descripciones, colores de interfaz (`color`) e iconos (`icono`) únicos guardados en base de datos.
* **Autenticación Segura (JWT):** Control de acceso y autorización mediante Spring Security y JSON Web Tokens (`JwtTokenProvider`).
* **Arquitectura Orientada a Objetos:** Modelado avanzado de herencia de entidades para soportar preguntas de Selección Múltiple, Selección Única y Verdadero/Falso.
* **Persistencia Robusta:** Gestión de datos relacionales utilizando Spring Data JPA y precarga de datos mediante `import.sql`.
* **Controladores Duales:** Exposición de endpoints REST (`PreguntaRestController`) e interfaces dinámicas con Thymeleaf (`PreguntaController`).
* **Contenerización:** Configuración lista para producción mediante Docker Compose.
* **Documentación Viva:** OpenAPI / Swagger integrado para la prueba interactiva de la API.

---

## 🧠 Universo Quiz Master (Temáticas del Sistema)

La aplicación viene con un dataset inicial precargado optimizado para perfiles curiosos, programadores y mentes analíticas:

1. **💻 Cibercultura y Frikismo:** Desde las entrañas del código y la historia hacker hasta los secretos mejor guardados del cine, la literatura y los videojuegos.
2. **🌿 Biología Extrema:** Criaturas indestructibles, mecanismos de defensa letales, venenos exóticos y rarezas de la flora y fauna mundial.
3. **🌌 Paradojas Cósmicas:** Viajes en el tiempo, física cuántica, agujeros negros y experimentos mentales que desafían la lógica humana.
4. **⚖️ Legalmente Absurdo:** Normas vigentes, prohibiciones extrañas y leyes históricas de distintos países que parecen una auténtica broma.
5. **🧭 Manual de Supervivencia:** Qué hacer en situaciones límite, cómo sobrevivir a la naturaleza salvaje y los peligros ocultos en la gastronomía extrema.

---

## 🛠️ Stack Tecnológico

* **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
* **Base de Datos:** H2 / PostgreSQL (Configurable mediante perfiles de Spring)
* **Frontend Monolítico:** Thymeleaf, Bootstrap, CSS Custom
* **Herramientas:** Maven, Docker, Swagger / OpenAPI

---

## 📂 Estructura del Proyecto

El código sigue las mejores prácticas de estructuración de proyectos en el ecosistema Java:

* `config/`: Configuraciones de seguridad (CORS, JWT) y MVC.
* `controller/`: Controladores web y endpoints de la API REST.
* `dto/`: Objetos de Transferencia de Datos para desacoplar la API de la base de datos.
* `entity/`: Modelos de datos del dominio (Usuarios, Roles, Preguntas, Temáticas).
* `repository/`: Interfaces de acceso a datos que extienden de JpaRepository.
* `security/`: Filtros y lógica de interceptación de peticiones JWT.
* `service/`: Capa de negocio donde reside la lógica principal de la aplicación.

---

## ⚙️ Instalación y Uso Local

### Prerrequisitos
* Java 17 o superior
* Maven 3.x o utilizar el Maven Wrapper incluido (`./mvnw`)
* Docker (Opcional, para levantar el entorno completo)

### Pasos para ejecutar

1. Clona este repositorio:
```bash
   git clone [https://github.com/Radu-A/quiz-master.git](https://github.com/Radu-A/quiz-master.git)
   cd quiz-master
```
2. Importa y actualiza el proyecto:

* Abre el proyecto en tu IDE preferido (IntelliJ, VS Code, etc.).
* Ve al menú de Maven y selecciona "Reload All Maven Projects" (o "Update") para asegurarte de que todas las dependencias del pom.xml se descarguen correctamente.
 
3. Ejecuta la aplicación:
Puedes usar el wrapper de Maven desde la terminal:
```bash
   ./mvnw spring-boot:run
```
