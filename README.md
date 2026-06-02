# Quiz Master 🧠

Quiz Master es una robusta API REST y plataforma web monolítica desarrollada con **Spring Boot** para la creación, gestión y realización de evaluaciones interactivas[cite: 4, 5]. El proyecto destaca por implementar una arquitectura limpia en capas, seguridad avanzada mediante tokens y soporte para múltiples tipos de preguntas.

---

## 🚀 Características Clave

* **Temáticas con Identidad Propia:** El juego no es un trivial genérico. Cuenta con un sistema de categorías dinámicas con descripciones, colores de interfaz (`color`) e iconos (`icono`) únicos guardados en base de datos.
* **Autenticación Segura (JWT):** Control de acceso y autorización mediante Spring Security y JSON Web Tokens (`JwtTokenProvider`)[cite: 5].
* **Arquitectura Orientada a Objetos:** Modelado avanzado de herencia de entidades para soportar preguntas de Selección Múltiple, Selección Única y Verdadero/Falso[cite: 5].
* **Persistencia Robusta:** Gestión de datos relacionales utilizando Spring Data JPA y precarga de datos mediante `import.sql`[cite: 5].
* **Controladores Duales:** Exposición de endpoints REST (`PreguntaRestController`) e interfaces dinámicas con Thymeleaf (`PreguntaController`)[cite: 5].
* **Contenerización:** Configuración lista para producción mediante Docker Compose[cite: 5].
* **Documentación Viva:** OpenAPI / Swagger integrado para la prueba interactiva de la API[cite: 5].

---

## 🧠 Universo Quiz Master (Temáticas del Sistema)

La aplicación viene con un dataset inicial precargado optimizado para perfiles curiosos, programadores y mentes analíticas[cite: 5]:

1. **💻 Cibercultura y Frikismo:** Desde las entrañas del código y la historia hacker hasta los secretos mejor guardados del cine, la literatura y los videojuegos[cite: 5].
2. **🌿 Biología Extrema:** Criaturas indestructibles, mecanismos de defensa letales, venenos exóticos y rarezas de la flora y fauna mundial[cite: 5].
3. **🌌 Paradojas Cósmicas:** Viajes en el tiempo, física cuántica, agujeros negros y experimentos mentales que desafían la lógica humana[cite: 5].
4. **⚖️ Legalmente Absurdo:** Normas vigentes, prohibiciones extrañas y leyes históricas de distintos países que parecen una auténtica broma[cite: 5].
5. **🧭 Manual de Supervivencia:** Qué hacer en situaciones límite, cómo sobrevivir a la naturaleza salvaje y los peligros ocultos en la gastronomía extrema[cite: 5].

---

## 📂 Estructura del Proyecto

El código sigue las mejores prácticas de estructuración de proyectos en el ecosistema Java[cite: 5]:

* `config/`: Configuraciones de seguridad (CORS, JWT) y MVC[cite: 5].
* `controller/`: Controladores web y endpoints de la API REST[cite: 5].
* `dto/`: Objetos de Transferencia de Datos para desacoplar la API de la base de datos[cite: 5].
* `entity/`: Modelos de datos del dominio (Usuarios, Roles, Preguntas, Temáticas)[cite: 5].
* `repository/`: Interfaces de acceso a datos que extienden de JpaRepository[cite: 5].
* `security/`: Filtros y lógica de interceptación de peticiones JWT[cite: 5].
* `service/`: Capa de negocio donde reside la lógica principal de la aplicación[cite: 5].

---

## ⚙️ Instalación y Uso Local

### Prerrequisitos
* Java 17 o superior
* Maven 3.x o utilizar el Maven Wrapper incluido (`./mvnw`)[cite: 5]
* Docker (Opcional, para levantar el entorno completo)[cite: 5]

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
