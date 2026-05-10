# 📖 HOWTO: Guía de Implementación Paso a Paso (EAC8 - AppBet)

Este manual técnico condensa las instrucciones del enunciado oficial para asegurar que el proyecto cumpla con todos los criterios de evaluación de la IOC.

---
## 0️⃣ PASO 0: Instalación de PostgreSQL en Mac

Antes de comenzar con la configuración del entorno de datos, es necesario tener PostgreSQL instalado en tu equipo.
Si ya lo tienes instalado, puedes saltar al Paso 1.

### 0.1. Instalar Homebrew (si no lo tienes)

Abre la Terminal y ejecuta:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
Introduce la contraseña de tu usuario cuando lo solicite.

### 0.2. Instalar PostgreSQL

Con Homebrew instalado, ejecuta:
```bash
brew install postgresql
```

Cuando ves esto en la terminal:
postgres=#

significa que estás dentro de la consola interactiva de PostgreSQL (`psql`).
Desde aquí puedes ejecutar comandos SQL, por ejemplo:

```sql
CREATE USER hibuser WITH PASSWORD 'password';
CREATE DATABASE hibernatedb OWNER hibuser;
GRANT ALL PRIVILEGES ON DATABASE hibernatedb TO hibuser;
```



#### Errores típicos

Si al pegar la primera linea en el terminal (CREATE USER hibuser...), aparece este error: ERROR: role "hibuser" already exists
significa que el usuario `hibuser` ya fue creado anteriormente.
Puedes continuar con los siguientes comandos:
```sql
CREATE DATABASE hibernatedb OWNER hibuser;
GRANT ALL PRIVILEGES ON DATABASE hibernatedb TO hibuser;
````

Ya tienes el usuario y la base de datos creados y con permisos.


** Si el comando `GRANT` se ejecutó correctamente, ya puedes salir de la consola de PostgreSQL escribiendo:**



### 0.3. Iniciar el servicio de PostgreSQL

Para que PostgreSQL esté disponible cada vez que inicies tu Mac:
```bash
brew services start postgresql
```

### 0.4. Verificar la instalación

Comprueba que PostgreSQL está correctamente instalado:
```bash
psql --version
```
Deberías ver la versión instalada de PostgreSQL.

---

## 🛠️ PASO 1: Configuración del Entorno de Datos

### 1.1. Creación de la Base de Datos y Usuario

1. Accede a la consola de PostgreSQL:
   ```bash
   psql postgres
   ```

2. Ejecuta los siguientes comandos SQL:
   ```sql
   CREATE USER hibuser WITH PASSWORD 'password';
   CREATE DATABASE hibernatedb OWNER hibuser;
   GRANT ALL PRIVILEGES ON DATABASE hibernatedb TO hibuser;
   ```

3. Sal de la consola de PostgreSQL:
   ```bash
   \q
   ```

### 1.2. Configuración de Hibernate

1. Abre el archivo `hibernate.cfg.xml` de tu proyecto.
2. Asegúrate de tener estas propiedades:
   ```xml
   <property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/hibernatedb</property>
   <property name="hibernate.connection.username">hibuser</property>
   <property name="hibernate.connection.password">password</property>
   <property name="hibernate.hbm2ddl.auto">update</property>
   ```

---

## 🏗️ PASO 2: Implementación del Modelo (Entidades JPA)

Ubicación: Paquete `cat.betdatabase.model`.

### 2.1. Clase `Event.java` (Esdeveniment)
* **Anotaciones:** `@Entity`, `@Table(name = "events")`.
* **Relación:** `@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)`.
* **Validación Obligatoria:** El constructor debe validar que la fecha (`timestamp`) sea posterior a la actual. Si no, lanza `IllegalArgumentException`.
* **Métodos:** Implementa `addBet(Bet bet)` para gestionar la relación bidireccional correctamente.

### 2.2. Clase `Bet.java` (Aposta)
* **Anotaciones:** `@Entity`, `@Table(name = "bets")`.
* **Relación:** `@ManyToOne(fetch = FetchType.LAZY)` con `@JoinColumn(name = "event_id")`.
* **Validación Obligatoria:** 
    * `odds` (cuota) > 0.
    * `amount` (importe) > 0.
    * Lanzar `IllegalArgumentException` si no se cumplen.

---

## 💾 PASO 3: Capa de Acceso a Datos (DAOs)

Ubicación: Paquete `cat.betdatabase.dao`. Cada método debe abrir una `Session` y gestionar la `Transaction`.

### 3.1. `EventDAO`
* **Métodos CRUD:** `save`, `update`, `delete`, `getById`, `getAll`.
* **Búsquedas HQL:** 
    * `findByType(String type)`: Debe usar `LIKE` para permitir búsquedas parciales de deportes.

### 3.2. `BetDAO`
* **Métodos CRUD:** `save`, `update`, `delete`, `getById`, `getAll`.
* **Búsquedas HQL:** 
    * `findByEventId(Long eventId)`: Para listar las apuestas de un evento concreto.

---

## ⚙️ PASO 4: Utilidades y Configuración

### 4.1. `HibernateUtil.java` (Patrón Singleton)
* Configura la `SessionFactory` cargando el XML.
* **IMPORTANTE:** Registra las clases: `.addAnnotatedClass(Event.class).addAnnotatedClass(Bet.class)`.

### 4.2. `Constants.java`
* Define el formato de fecha: `yyyyMMdd`.
* Centraliza los mensajes de error y las cabeceras de los listados.

---

## 🎮 PASO 5: Lógica de Negocio (`Application.java`)

El menú principal debe gestionar el siguiente flujo:

1.  **Opción 1 (Afegir Esdeveniment):** Pedir datos -> Validar fecha futura -> Persistir.
2.  **Opción 2 (Afegir Aposta):** 
    * Mostrar eventos disponibles.
    * Seleccionar ID.
    * **Regla de Validación:** Comprobar si el `bettorName` ya existe en la lista de apuestas del evento seleccionado para evitar duplicados.
3.  **Opción 3 (Llistats):** Mostrar apuestas filtradas por deporte o el total.

---

## ✅ CHECKLIST FINAL DE REVISIÓN

- [ ] ¿El archivo comprimido se llama `DA2_M03B2_EAC8_Cognom1_InicialCognom2.zip`?
- [ ] ¿He incluido el archivo `pom.xml` con las dependencias correctas?
- [ ] ¿Se han mapeado correctamente las relaciones `@OneToMany` y `@ManyToOne`?
- [ ] ¿He gestionado los cierres de sesión (`session.close()`) en todos los DAO?
- [ ] ¿Funciona el filtrado `LIKE` (ignore case) en la búsqueda de deportes?