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
| **Procesamiento con Streams** | `Stream API` para filtrar, ordenar, mapear y recolectar datos (Top 5 episodios, conversión a modelo) |
| **Búsqueda de Episodios** | Por fragmento de título (case-insensitive) con `Optional` + `findFirst`, y por año con `LocalDate` + `DateTimeFormatter` |
| **Estadísticas por Temporada** | Evaluación promedio por temporada con `groupingBy` + `averagingDouble`, y resumen global (count, sum, min, average, max) con `summarizingDouble` / `DoubleSummaryStatistics` |
| **Ciclo de Vida Spring Boot** | `CommandLineRunner` como punto de entrada de la aplicación |
| **Interfaz Genérica de Conversión** | `IConvierteDatos<T>` — contrato reutilizable para cualquier mapeo JSON → objeto |
| **Modelo de Dominio Enriquecido** | Clase `Episodio` con getters/setters, `LocalDate` para fechas, conversión desde DTO |

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
                  └────────┬─────────┘
                           │ map/flatMap
                           ▼
                  ┌──────────────────┐
                  │  Model (class)   │
                  │    Episodio      │
                  │ temporada,       │
                  │ titulo, rating,  │
                  │ fechaLanzamiento │
                  └──────────────────┘
```

### 📁 Estructura del Proyecto

```
src/main/java/com/aluracursos/screenmatchSpringBoot/
├── ScreenmatchSpringBootApplication.java   # Entry point + CommandLineRunner
├── principal/
│   ├── Principal.java                      # Orquestación de la lógica de negocio
│   └── EjemploStreams.java                 # Ejemplos educativos de Stream API
├── model/
│   ├── DatosSerie.java                     # DTO de serie (record)
│   ├── DatosTemporadas.java                # DTO de temporada (record)
│   ├── DatosEpisodio.java                  # DTO de episodio (record)
│   └── Episodio.java                       # Modelo de dominio enriquecido (class)
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

Top 5 episodios
DatosEpisodio[titulo=The Rains of Castamere, evaluacion=9.9, ...]
DatosEpisodio[titulo=Battle of the Bastards, evaluacion=9.9, ...]
DatosEpisodio[titulo=The Winds of Winter, evaluacion=9.8, ...]
...

Episodio encontrado
Los datos son: temporada=3, titulo='The Rains of Castamere', numeroEpisodio=9, evaluacion=9.9, fechaDeLanzamiento=2013-06-02

{1=8.45, 2=8.71, 3=8.95, ...}
DoubleSummaryStatistics{count=62, sum=543.200000, min=7.200000, average=8.761290, max=9.900000}
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
| **Java Moderno (17+)** | `record` como DTOs (sin Lombok, sin POJOs manuales), `HttpClient`, lambdas con `forEach`, inferencia `var`, `Optional` para búsquedas |
| **Stream API** | `stream()`, `flatMap()`, `filter()`, `sorted()`, `limit()`, `collect()`, `map()`, comparadores personalizados, `groupingBy` + `averagingDouble`, `summarizingDouble` (`DoubleSummaryStatistics`), `peek()` para depuración |
| **Spring Boot** | `@SpringBootApplication`, `CommandLineRunner`, hooks del ciclo de vida Spring |
| **Consumo de APIs REST** | Peticiones HTTP GET a OMDb, construcción de query params, manejo de respuestas |
| **Procesamiento JSON** | Jackson `ObjectMapper`, `@JsonAlias` para mapeo de campos, `@JsonIgnoreProperties` |
| **Diseño Orientado a Objetos** | Diseño basado en interfaces (`IConvierteDatos<T>`), clases con responsabilidad única |
| **Principios SOLID** | Open/Closed mediante interfaces genéricas, Inversión de Dependencias con abstracciones |
| **Build con Maven** | Configuración `pom.xml`, gestión de dependencias, Maven Wrapper |
| **Arquitectura Limpia** | Separación en capas `model`, `service` y orquestación |
| **Modelado de Dominio** | Clase `Episodio` con encapsulamiento, `LocalDate`, conversión desde DTO inmutable, manejo de `N/A` → `0.0` y fechas inválidas → `null` |

---

## 📚 Sobre el Programa

Proyecto desarrollado durante **Alura One — Oracle Next Education**, un programa intensivo de formación en backend enfocado en Java, Spring y prácticas modernas de desarrollo de software.

---

## 📄 Licencia

Distribuido bajo la licencia MIT. Consulte `LICENSE` para más información.
