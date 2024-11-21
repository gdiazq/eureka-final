# Prueba Eureka Final Full Stack Spring Angular

Aplicacion Full Stack con Spring Boot y Angular que permite registar usuarios y asociarlos a una area, mostrarlos en una tabla y un grafico de barras, en el backend es una API ResT con Spring Boot y en el frontend se ocupan formularios con validaciones con Angular, la base de datos es H2 y ofrece endpoints para operaciones CRUD

## Criterios para aceptacion

- El stack de herramientas a utilizar es SpringBoot (Java), Angular y base de datos H2 (de preferencia) o MySQL.
- El formulario de registro de personas tiene los campos:
    - Nombre
    - Correo electrónico (único e identificador de la persona)
- Área de trabajo: Ventas, Recursos Humanos, etc.
- Todos los campos son obligatorios.
- Luego del registro de datos, se debe informar al usuario si la acción fue exitosa, o bien si hubo errores en el formulario.
- Debes agregar otra pantalla que muestre una tabla que indique el nombre del área y la cantidad de personas que la integran.
- Se valorará que los datos anteriores sean desplegados en un gráfico de barras.
- Debes agregar al menos tres tests unitarios por cada capa.
- Considera una sección que indique los datos del desarrollador de la solución, agregando una imagen alusiva.
- El proyecto debe ser publicado en GitHub, con un archivo README que permita descargar, compilar y ejecutar el aplicativo.

## Requisitos

- Integración de framework de presentación como Material o Bootstrap.
- Se valorarán las buenas prácticas de codificación para frontend y backend.
- Se apreciará que en el frontend utilices buenas prácticas en cuanto a accesibilidad y usabilidad.
- Versión de Java: 17+
- Versión de Spring Boot: 3.x
- Versión de Angular: 15+

## Instalacion

- Clonar repositorio:

```bash
git clone https://github.com/gdiazq/eureka-final.git
```

### Para el backend

- Ingresar a la carpeta del proyecto

- Compilar el proyecto
```bash
mvn clean install
```
- Ejecutar la aplicacion
```bash
mvn spring-boot:run
```

### Para el frontend

- Ingresar a la carpeta del proyecto frontend

- Instalar dependencias
```bash
npm install
```

- Ejecutar el proyecto
```bash
ng serve
```

## Endpoints

Se recomienda instalar postman o thunderbird como extension para vscode o utilizar el comando curl en linux

- Listar todos los usuarios

```bash
GET http://localhost:8080/users
```

- Listar todos las areas

```bash
GET https://localhost:8080/zones
```

- Listar usuario por id

```bash
GET http://localhost:8080/users/{id}
```

- Listar areas por id

```bash
GET http://localhost:8080/zones/{id}
```

- Crear usuario

```bash
POST http://localhost:8080/users
```

 - Ejemplo del body:

```json
{
    "nombre": "Jorge Diaz",
    "email": "jorgec.diazq@gmail.com",
    "zonaId": 3
}
```

- Contar usuarios por area

```bash
GET http://localhost:8080/users/count
```

## Sobre las pruebas

- Para ejecutar las pruebas unitarias del backend con JUnit y Mockito se puede ocupar el comando mvn test o en el entorno de desarrollo elegido ir a la parte de test
- Para ejecutar las pruebas unitarias del frontend, ejecutar el comando ng test

