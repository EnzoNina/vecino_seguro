
# Vecino Seguro - Servicio de Perfiles Familiares

Este proyecto forma parte de la aplicación Vecino Seguro, una herramienta que permite a los usuarios gestionar perfiles familiares con el fin de mejorar la seguridad del entorno familiar. Este microservicio en Spring Boot maneja la creación, actualización y eliminación de perfiles familiares y facilita la consulta de relaciones familiares dentro de una estructura familiar.


## Caracteristicas

- Gestión de perfiles familiares: Crea, actualiza y elimina perfiles familiares.
- Relaciones inversas automáticas: Cada vez que se añade una relación familiar, se genera automáticamente la relación inversa.
- Restricción de edad: Solo usuarios mayores de edad pueden añadir o gestionar perfiles familiares.
- Geolocalización actualizada: Los perfiles familiares permiten registrar la ubicación en tiempo real.


## API Endpoints

#### Agregar un perfil familiar

```http
  POST /api/perfiles-familiares/{id_usuario}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id_usuario` | `string` | **Required**. ID del usuario principal que agrega a un familiar. |

##### Cuerpo de envio

```json
{
  "familiar_id": 3,   // ID del familiar a agregar
  "relacion_id": 1    // ID de la relación familiar
}
```

##### Cuerpo de respuesta

```json
{
  "mensaje": "Perfil familiar agregado exitosamente",
  "perfil": {
    "id": 32,
    "nombre_usuario": "Pedro Sanchez",
    "apellido_usuario": "Nina",
    "nombre_familiar": "Carmen Ramirez",
    "apellido_familiar": "Aragon",
    "relacion": "Pareja"
  }
}
```


#### Listar perfiles familiares de un usuario

```http
  GET /api/perfiles-familiares/{id_usuario}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id_usuario`      | `string` | **Required**. ID del usuario principal. |


##### Cuerpo de respuesta

```json
{
    "perfiles": [
        {
            "id": 20,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Enzo",
            "apellido_familiar": "Nina",
            "relacion": "Hijo"
        },
        {
            "id": 22,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Juan Perez",
            "apellido_familiar": "Nina",
            "relacion": "Hijo"
        },
        {
            "id": 24,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Maria Lopez",
            "apellido_familiar": "Nina",
            "relacion": "Hijo"
        },
        {
            "id": 26,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Carlos Garcia",
            "apellido_familiar": "Nina",
            "relacion": "Hijo"
        },
        {
            "id": 28,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Ana Torres",
            "apellido_familiar": "Nina",
            "relacion": "Hijo"
        },
        {
            "id": 30,
            "nombre_usuario": "Pedro Sanchez",
            "apellido_usuario": "Nina",
            "nombre_familiar": "Carmen Ramirez",
            "apellido_familiar": "Aragon",
            "relacion": "Pareja"
        }
    ],
    "mensaje": "Perfiles familiares listados exitosamente"
}
```