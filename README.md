
# ScreenMatch Spring Boot 🎬

![Java](https://img.shields.io/badge/Java-25-%23ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-%236DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.x-%23C71A36?logo=apache-maven)
![OMDb API](https://img.shields.io/badge/API-OMDb-%23F5C518?logo=imdb)

ScreenMatch es una aplicación de consola desarrollada con **Spring Boot** que consume la **OMDb API** para consultar información detallada de series de televisión: datos generales, temporadas y episodios. El proyecto fue desarrollado como parte del programa **Alura One - Oracle Next Education**.

---

## ✨ Funcionalidades

- **Consumo de API REST** — Integración con OMDb API usando `HttpClient` nativo de Java.
- **Deserialización JSON** — Mapeo de respuestas a `records` de Java con Jackson y `@JsonAlias`.
- **Consulta por temporadas** — Itera sobre todas las temporadas de una serie y muestra episodio por episodio.
- **Arquitectura limpia** — Separación en capas: `model`, `service` y la aplicación principal.

---

## 🧰 Stack Tecnológico

| Tecnología | Versión |
|-----------|---------|
| Java | 25 |
| Spring Boot | 4.1.0 |
| Maven | 3.x |
| Jackson Databind | 2.22.1 |
| OMDb API | v1 |

---

## 🗂️ Estructura del Proyecto

```
screenmatchSpringBoot/
├── src/main/java/com/aluracursos/screenmatchSpringBoot/
│   ├── ScreenmatchSpringBootApplication.java   # Entry point & lógica principal
│   ├── model/
│   │   ├── DatosSerie.java                     # Record: datos generales de la serie
│   │   ├── DatosTemporadas.java                # Record: temporada + lista de episodios
│   │   └── DatosEpisodio.java                  # Record: datos del episodio
│   └── service/
│       ├── ConsumoAPI.java                     # Cliente HTTP para llamadas a la API
│       ├── IConvierteDatos.java                # Interface genérica para conversión JSON
│       └── ConvierteDatos.java                 # Implementación con Jackson ObjectMapper
└── pom.xml                                     # Configuración Maven
```

---

## 🚀 Cómo ejecutar

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/screenmatchSpringBoot.git

# 2. Entrar al directorio
cd screenmatchSpringBoot

# 3. Ejecutar con Maven
./mvnw spring-boot:run
```

> **Nota:** La aplicación requiere una API key de OMDb. La actual es de prueba — reemplázala en `ScreenmatchSpringBootApplication.java` con tu propia clave en [omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx).

---

## 📌 Ejemplo de salida

```
ScreenMatch Spring Boot
Título: Game of Thrones
Temporadas: 8
Evaluación: 9.2

Temporada 1:
  Episodio 1: "Winter Is Coming" (2011-04-17) ⭐ 8.9
  Episodio 2: "The Kingsroad" (2011-04-24) ⭐ 8.7
  ...
```

---

## 👨‍💻 Lo que demuestra este proyecto

| Habilidad | Evidencia |
|-----------|-----------|
| **Java moderno** | Records, `HttpClient`, lambdas, streams |
| **Spring Boot** | `@SpringBootApplication`, `CommandLineRunner`, inyección de dependencias |
| **APIs REST** | Consumo de servicios web con HTTP |
| **JSON & Serialización** | Jackson `ObjectMapper`, anotaciones `@JsonAlias`, `@JsonIgnoreProperties` |
| **POO & SOLID** | Interfaces genéricas, separación de responsabilidades |
| **Maven** | Gestión de dependencias, ciclo de build |

---

## 📚 Sobre el programa

Proyecto desarrollado durante el **Alura One - Oracle Next Education**, programa de formación en desarrollo backend con Java y Spring.

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT.
