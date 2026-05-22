# repaso-ia

Spring Boot 4.0.6 / Java 17. Multi-layer (Controller -> Service -> Repository -> Entity).

## Commands

```sh
./mvnw spring-boot:run    # dev server
./mvnw test               # run all tests
./mvnw verify             # test + package (jar in target/)
```

## Package structure

`com.github.Radu_A.evaluacion_final` — controller, dto, entity, exception, repository, security, service.

## Dependencies

- Uses **modular starters**: `-webmvc`, `-thymeleaf`, `-validation`, `-data-jpa`. Add new ones the same way.
- **Lombok** is present — do not add manual getters/setters/constructors; annotate instead.
- Persistence: Spring Data JPA. Schema auto-created (`ddl-auto=create`) on startup.
- **H2 console** enabled at `/h2-console`.
- Profile `dev` = H2 (in-memory), `prod` = MySQL (credentials not in `application.properties`).

## UI

Thymeleaf templates. Local Bootstrap from `src/main/resources/static/`.

## Config

`application.properties` has full H2/JPA config — do not assume it only has the app name.