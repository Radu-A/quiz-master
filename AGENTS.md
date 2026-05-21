# repaso-ia

Spring Boot 4.0.6 / Java 17 project. Multi-layer (Controller -> Service -> Repository -> Entity).

## Commands

```sh
./mvnw spring-boot:run    # dev server
./mvnw test               # run all tests
./mvnw verify             # test + package (jar in target/)
```

## Notable

- **Spring Boot 4 modular starters** — this project uses the explicit `-webmvc`, `-thymeleaf`, `-validation`, `-data-jpa` (and `-test` variants) rather than the monolithic `-starter-web`. Add dependencies the same way.
- **Persistence:** Spring Data JPA. Profile `dev` = H2, `prod` = MySQL.
- **Package** = `com.github.Radu_A.evaluacion_final` (hyphen replaced with underscore because `-` is invalid in Java packages). 
- Strictly separate concerns into standard Spring Boot packages: `model` (or `entity`), `repository`, `service`, and `controller`.
- **Java 17** — no record/pattern-matching lint restrictions; feel free to use modern Java.
- `application.properties` currently only has `spring.application.name=repaso-ia`.
- **UI (Thymeleaf)** - Local Bootstrap from `src/main/resources/static/`
