[readme.md](https://github.com/user-attachments/files/27570929/readme.md)
# 🏆 Proyecto AppEAC8 - BET IOC!
## Gestión de Persistencia con Hibernate y PostgreSQL

Este proyecto constituye la entrega para el **Ejercicio de Evaluación Continua 8 (EAC8)** de la asignatura Programación II (Curso 2025-26 / 2n semestre). Implementa una aplicación de gestión de apuestas deportivas utilizando un modelo de persistencia basado en objetos.

---

## 📋 Introducción y Objetivos
El software, denominado **BET IOC!**, permite la administración de eventos deportivos y las apuestas realizadas sobre ellos. El objetivo principal es aplicar los resultados de aprendizaje **RA8** y **RA9**, enfocados en el uso de bbdd orientadas a objetos y el mantenimiento de la integridad y consistencia de la información almacenada.

## 🛠️ Especificaciones del Entorno Técnico
Para cumplir con los criterios de corrección, el entorno debe estar configurado de la siguiente manera:

* **Lenguaje:** Java 21.
* **Base de Datos:** PostgreSQL 16.
* **ORM:** Hibernate 6.4.4.Final.
* **Gestor de Dependencias:** Maven.
* **Configuración Obligatoria (hibernate.cfg.xml):**
    * **Host:** `localhost`
    * **Puerto:** `5432`
    * **Usuario:** `hibuser`
    * **Password:** `password`
    * **Database:** `hibernatedb`
    * **Propiedad Crítica:** `hibernate.hbm2ddl.auto` establecida en `update` para la generación automática de tablas e índices.

---

## 🏗️ Arquitectura y Estructura del Proyecto
El proyecto se organiza en paquetes siguiendo el patrón **DAO (Data Access Object)**:

### 1. Paquete `cat.betdatabase.model` (Entidades JPA)
* **`Event.java`**: Representa un evento deportivo.
    * **Atributos:** `id` (Long, PK), `type` (Deporte), `name` (Nombre), `timestamp` (Fecha/Hora), `betsList` (Relación One-to-Many).
    * **Relación:** `@OneToMany` hacia `Bet`, con cascada total (`ALL`) y eliminación de huérfanos.
    * **Validación:** El `timestamp` debe ser siempre una fecha futura; de lo contrario, lanza `IllegalArgumentException`.
* **`Bet.java`**: Representa una apuesta individual.
    * **Atributos:** `id` (Long, PK), `bettorName` (Apostante), `betDescription` (Descripción), `odds` (Cuota), `amount` (Importe), `event` (Relación Many-to-One).
    * **Relación:** `@ManyToOne` hacia `Event` con carga diferida (`LAZY`).
    * **Validación:** `odds` y `amount` deben ser estrictamente mayores que 0.

### 2. Paquete `cat.betdatabase.dao` (Persistencia)
* **`EventDAO.java`**: Gestiona el CRUD de eventos. Incluye métodos como `save`, `update`, `delete`, `getById`, `getAll`, y búsquedas específicas por nombre o tipo de deporte.
* **`BetDAO.java`**: Gestiona el CRUD de apuestas. Incluye métodos como `save`, `update`, `delete`, `getById`, `getAll`, y búsquedas por nombre de apostante o por ID de evento.

### 3. Paquete `cat.betdatabase.util` (Utilidades)
* **`HibernateUtil.java`**: Implementa el patrón **Singleton**. Configura el `SessionFactory` cargando `hibernate.cfg.xml` y registrando las clases `Event` y `Bet`.
* **`UtilsIO.java`**: Gestiona la interfaz de consola, validación de entradas de texto, números y fechas, y la visualización de menús.
* **`Constants.java`**: Almacena todos los mensajes de usuario, formatos de fecha (`yyyyMMdd`) y las consultas HQL (Hibernate Query Language).

---

## 🎮 Guía de Funcionamiento
El programa debe replicar exactamente el flujo del menú principal definido por la IOC:

1.  **Afegir un esdeveniment:** El sistema solicita el tipo de deporte, el nombre del evento y la fecha/hora. Valida que la fecha sea futura.
2.  **Afegir una aposta a un esdeveniment:**
    * Muestra el listado de eventos disponibles.
    * El usuario selecciona uno por su ID.
    * Se solicitan los datos de la apuesta: nombre, descripción, cuota e importe.
    * **Regla de Negocio:** No se puede añadir una apuesta si el apostante ya ha apostado en ese mismo evento.
3.  **Veure el llistat d'apostes:**
    * Permite ver todas las apuestas o filtrar por tipo de deporte (búsqueda parcial `LIKE`).
    * Muestra los datos en formato tabla: `DATETIME | SPORT | ESDEVENIMENT | TIPUS | QUOTES | IMPORT`.
0.  **Sortir:** Cierra la sesión de Hibernate y finaliza el proceso.

---

## 📐 Criterios de Evaluación y Entrega
* **Configuración del entorno (50%):** Correcta definición del `pom.xml`, `hibernate.cfg.xml` y que Hibernate cree la base de datos automáticamente sin errores.
* **Funcionamiento (50%):** Código ordenado, indentado, sin errores de compilación y que cumpla exactamente con la lógica del vídeo descriptivo de la IOC.
* **Nombre del archivo de entrega:** `DA2_M03B2_EAC8_Cognom1_InicialDelCognom2.zip` (o .7z).
* **Fecha límite:** 12/05/2026 a las 23:55h.

---
*Este proyecto ha sido desarrollado siguiendo las directrices del **Institut Obert de Catalunya (IOC)**.*
