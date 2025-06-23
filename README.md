# 📱 Prueba Técnica Android - Interrapidísimo

Este proyecto es una solución a la prueba técnica Android propuesta por Interrapidísimo. La app está desarrollada en Kotlin siguiendo buenas prácticas de arquitectura, principios SOLID y el patrón MVI (Model-View-Intent).

---

## 🚀 Tecnologías usadas

- Kotlin
- Jetpack Compose
- Retrofit + OkHttp
- Room (SQLite)
- MVI Pattern
- Coroutines + Flow
- Dagger Hilt
- Clean Architecture

---

## 🧱 Arquitectura

La app está basada en Clean Architecture y dividida en capas:

- **Data:** APIs, modelos remotos/locales, DAOs
- **Domain:** Modelos de dominio, casos de uso, interfaces de repositorios
- **Presentation:** Pantallas, estados, intents, vistas

El patrón de presentación es **MVI (Model-View-Intent)**:
- `Intent`: evento emitido por la vista (ej. cargar usuario)
- `State`: representa el estado actual de la UI
- `ViewModel`: orquesta intents y expone estados

---

## 📲 Características

### 🔐 Seguridad
- Validación de versión contra endpoint remoto
- Login con usuario/password codificados y validación de código HTTP
- Almacenamiento local del usuario si login es exitoso

### 💾 Datos
- Sincronización de esquema de tablas desde API
- Persistencia en SQLite usando Room

### 🖼 Pantallas
- `Home`: muestra nombre del usuario autenticado y botones a Tablas y Localidades
- `Tablas`: muestra las tablas descargadas y almacenadas localmente
- `Localidades`: consume y muestra listado de localidades con nombre y abreviación

---

## ⚙️ Instalación y ejecución

1. Clona el repositorio:
```bash
git clone https://github.com/tuusuario/prueba-tecnica-interrapidisimo.git

##👤 Autor
Felipe Méndez
[GitHub](https://github.com/FelipeMz-dev) • [LinkedIn](https://www.linkedin.com/in/juan-felipe-mendez-carmona-3ab104242/)