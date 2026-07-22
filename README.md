# ScreenMatch Spring Boot 🎬

![Java](https://img.shields.io/badge/Java-25-%23ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-%236DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-%23C71A36?logo=apache-maven&logoColor=white)
![Jackson](https://img.shields.io/badge/Jackson-2.22.1-black?logo=json&logoColor=white)
![OMDb API](https://img.shields.io/badge/API-OMDb-%23F5C518?logo=imdb&logoColor=black)
[![License: MIT](https://img.shields.io/badge/Licencia-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Aplicación de consola que consume la API REST de OMDb** para obtener información detallada de series de televisión — temporadas, episodios, calificaciones y más — construida con Java moderno y Spring Boot.

> Proyecto desarrollado durante el programa **Alura One — Oracle Next Education**.

---

## ✨ Funcionalidades

| Capacidad | Implementación |
|---|---|
| **Cliente REST** | `java.net.http.HttpClient` nativo de Java 11+ — sin librerías externas HTTP |
| **Deserialización JSON** | Jackson `ObjectMapper` con `@JsonAlias` y `@JsonIgnoreProperties` |
| **DTOs Inmutables** | `record` de Java — concisos, thread-safe, sin boilerplate |
| **Consulta por Temporadas** | Itera todas las temporadas y muestra cada episodio con título, rating y fecha |
| **Ciclo de Vida Spring Boot** | `CommandLineRunner` como punto de entrada de la aplicación |
| **Interfaz Genérica de Conversión** | `IConvierteDatos<T>` — contrato reutilizable para cualquier mapeo JSON → objeto |

---

## 🧱 Arquitectura

```
┌────────────────────────────────────────────┐
│  ScreenmatchSpringBootApplication.java     │
│  @SpringBootApplication + CommandLineRunner │
└────────────────┬───────────────────────────┘
                 │  instancia
                 ▼
┌────────────────────────────────────────────┐
│  Principal.java                            │
│  Orquestación — input de usuario, llamadas │
│  a la API, renderizado de salida           │
└────┬─────────────────────┬─────────────────┘
     │                     │
     ▼                     ▼
┌────────────┐    ┌──────────────────┐
│ ConsumoAPI │    │ ConvierteDatos   │
│ HttpClient │    │ ObjectMapper     │
│ (service)  │    │ (service)        │
└────────────┘    └──────────────────┘
                        │
                        ▼
                 ┌──────────────────┐
                 │  Model (record)  │
                 │ DatosSerie       │
                 │ DatosTemporadas  │
                 │ DatosEpisodio    │
                 └──────────────────┘
```

### 📁 Estructura del Proyecto

```
src/main/java/com/aluracursos/screenmatchSpringBoot/
├── ScreenmatchSpringBootApplication.java   # Entry point + CommandLineRunner
├── principal/
│   └── Principal.java                      # Orquestación de la lógica de negocio
├── model/
│   ├── DatosSerie.java                     # DTO de serie (record)
│   ├── DatosTemporadas.java                # DTO de temporada (record)
│   └── DatosEpisodio.java                  # DTO de episodio (record)
└── service/
    ├── ConsumoAPI.java                     # Capa cliente HTTP
    ├── IConvierteDatos.java                # Interfaz genérica convertidor JSON
    └── ConvierteDatos.java                 # Implementación con Jackson
```

---

## 🚀 Cómo Ejecutar

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/screenmatchSpringBoot.git
cd screenmatchSpringBoot

# Ejecutar con Maven
./mvnw spring-boot:run
```

> **⚠️ API Key:** Reemplaza la clave de prueba en `Principal.java` con tu propia clave en [omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx).

---

## 🖥️ Ejemplo de Salida

```
Por favor escribe la serie que deseas buscar: Game of Thrones

DatosSerie[titulo=Game of Thrones, totalDeTemporadas=8, evaluacion=9.2]
DatosTemporadas[numero=1, episodios=[DatosEpisodio[titulo=Winter Is Coming, ...]]]
DatosTemporadas[numero=2, episodios=[DatosEpisodio[titulo=The North Remembers, ...]]]
...
```

---

## 🛠️ Stack Tecnológico

| Tecnología | Propósito |
|---|---|
| **Java 25** | Records, Lambdas, Streams, `HttpClient`, inferencia `var` |
| **Spring Boot 4.1.0** | Auto-configuración, `CommandLineRunner`, contenedor IoC |
| **Maven 3.x** | Gestión de dependencias, ciclo de build |
| **Jackson 2.22.1** | Serialización/deserialización JSON |
| **OMDb API** | Fuente de datos REST externa |
| **JUnit 5** | Test de carga de contexto con `@SpringBootTest` |

---

## ✅ Habilidades Demostradas

| Habilidad | Evidencia en Código |
|---|---|
| **Java Moderno (17+)** | `record` como DTOs (sin Lombok, sin POJOs manuales), `HttpClient`, lambdas con `forEach`, inferencia `var` |
| **Spring Boot** | `@SpringBootApplication`, `CommandLineRunner`, hooks del ciclo de vida Spring |
| **Consumo de APIs REST** | Peticiones HTTP GET a OMDb, construcción de query params, manejo de respuestas |
| **Procesamiento JSON** | Jackson `ObjectMapper`, `@JsonAlias` para mapeo de campos, `@JsonIgnoreProperties` |
| **Diseño Orientado a Objetos** | Diseño basado en interfaces (`IConvierteDatos<T>`), clases con responsabilidad única |
| **Principios SOLID** | Open/Closed mediante interfaces genéricas, Inversión de Dependencias con abstracciones |
| **Build con Maven** | Configuración `pom.xml`, gestión de dependencias, Maven Wrapper |
| **Arquitectura Limpia** | Separación en capas `model`, `service` y orquestación |

---

## 📚 Sobre el Programa

Proyecto desarrollado durante **Alura One — Oracle Next Education**, un programa intensivo de formación en backend enfocado en Java, Spring y prácticas modernas de desarrollo de software.

---

## 📄 Licencia

Distribuido bajo la licencia MIT. Consulte `LICENSE` para más información.
