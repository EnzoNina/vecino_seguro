# Vecino Seguro - Servicio de Perfiles Familiares

Este proyecto forma parte de la aplicación Vecino Seguro, una herramienta que permite a los usuarios gestionar perfiles
familiares con el fin de mejorar la seguridad del entorno familiar. Este microservicio en Spring Boot maneja la
creación, actualización y eliminación de perfiles familiares y facilita la consulta de relaciones familiares dentro de
una estructura familiar.

## Caracteristicas

- Gestión de perfiles familiares: Crea, actualiza y elimina perfiles familiares.
- Relaciones inversas automáticas: Cada vez que se añade una relación familiar, se genera automáticamente la relación
  inversa.
- Restricción de edad: Solo usuarios mayores de edad pueden añadir o gestionar perfiles familiares.
- Geolocalización actualizada: Los perfiles familiares permiten registrar la ubicación en tiempo real.

## API Endpoints

## Usuarios

### Listar usuarios

```http
  GET /usuarios/listarUsuarios
```

#### Cuerpo de respuesta

```json
{
  "mensaje": "Usuarios listados exitosamente",
  "usuarios": [
    {
      "nombres_completos": "Enzo Nina Aragon"
    },
    {
      "nombres_completos": "Juan Perez Nina Aragon"
    },
    {
      "nombres_completos": "Maria Lopez Nina Aragon"
    },
    {
      "nombres_completos": "Carlos Garcia Nina Aragon"
    },
    {
      "nombres_completos": "Ana Torres Nina Aragon"
    },
    {
      "nombres_completos": "Pedro Sanchez Nina Pareja"
    },
    {
      "nombres_completos": "Carmen Ramirez Aragon Pizarro"
    }
  ]
}
```         

## Tipo Relacion Familiar

### Listar relaciones

```http
  GET /relaciones-familiares/listar
```

#### Cuerpo de respuesta

```json
{
  "relaciones": [
    {
      "id": 1,
      "descripcion": "Padre"
    },
    {
      "id": 2,
      "descripcion": "Hijo"
    },
    {
      "id": 3,
      "descripcion": "Pareja"
    }
  ],
  "mensaje": "Relaciones familiares listadas exitosamente"
}
```

## Perfiles Familiares

### Agregar un perfil familiar

```http
  POST /api/perfiles-familiares/{id_usuario}
```

| Parameter    | Type     | Description                                                      |
|:-------------|:---------|:-----------------------------------------------------------------|
| `id_usuario` | `string` | **Required**. ID del usuario principal que agrega a un familiar. |

##### Cuerpo de envio

```json
{
  "familiar_id": 3,
  // ID del familiar a agregar
  "relacion_id": 1
  // ID de la relación familiar
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

### Listar perfiles familiares de un usuario

```http
  GET /api/perfiles-familiares/{id_usuario}
```

| Parameter    | Type     | Description                             |
|:-------------|:---------|:----------------------------------------|
| `id_usuario` | `string` | **Required**. ID del usuario principal. |

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

### Eliminar perfil familiar

```http
    DELETE /perfiles-familiares/eliminar/{{id_perfil}}/{{usuarioId}}    
```

| Parameter   | Type     | Description                                      |
|:------------|:---------|:-------------------------------------------------|
| `id_perfil` | `string` | **Required**. ID del perfil familiar.            |
| `usuarioId` | `string` | **Required**. ID del usuario que hace la acciónn |

#### Cuerpo de respuesta

```json
{
  "mensaje": "Perfil familiar eliminado exitosamente"
}
```

### Actualizar perfil familiar

```http
    PUT /perfiles-familiares/{{id_perfil}}/{{id_usuario}}
```

| Parameter   | Type     | Description                                      |
|:------------|:---------|:-------------------------------------------------|
| `id_perfil` | `string` | **Required**. ID del perfil familiar.            |
| `usuarioId` | `string` | **Required**. ID del usuario que hace la acciónn |

#### Cuerpo de envio

```json
{
  "id_tipo_relacion": 1
}
```

#### Cuerpo de respuesta

```json
{
  "perfil": {
    "id": 32,
    "nombre_usuario": "Pedro Sanchez",
    "apellido_usuario": "Nina",
    "nombre_familiar": "Carmen Ramirez",
    "apellido_familiar": "Aragon",
    "relacion": "Pareja"
  },
  "mensaje": "Perfil familiar actualizado exitosamente"
}
```

### Actualizar ubicación de un perfil familiar

```http
    PUT /perfiles-familiares/{{id_perfil}}/ubicacion?latitud={{$placeholder}}&longitud={{$placeholder}}
```

| Parameter   | Type     | Description                             |
|:------------|:---------|:----------------------------------------|
| `id_perfil` | `string` | **Required**. ID del perfil familiar.   |
| `latitud`   | `string` | **Required**. Latitud de la ubicación.  |
| `longitud`  | `string` | **Required**. Longitud de la ubicación. |

#### Cuerpo de respuesta

```json
{
  "perfil": {
    "id": 32,
    "nombre_usuario": "Pedro Sanchez",
    "apellido_usuario": "Nina",
    "nombre_familiar": "Carmen Ramirez",
    "apellido_familiar": "Aragon",
    "relacion": "Pareja"
  },
  "mensaje": "Ubicación actualizada exitosamente"
}
```

## Tipo Incidentes

#### Listar Tipo Incidentes

```http
    GET /tiposIncidentes/listar
```

#### Cuerpo de respuesta

```json
{
  "tiposIncidentes": [
    {
      "id": 1,
      "descripcion": "Robo"
    },
    {
      "id": 2,
      "descripcion": "Acoso"
    },
    {
      "id": 3,
      "descripcion": "Violencia"
    }
  ],
  "mensaje": "Todos los tipos de incidentes"
}
```

## Incidentes

### Reportar incidente

```http
    POST /incidentes/reportarIncidente
```

#### Cuerpo de envio

```json
{
  "id_usuario": "",
  "id_tipo_incidente": "",
  "descripcion": "",
  "latitud": "",
  "longitud": ""
}
```

#### Cuerpo de respuesta

```json
{
  "mensaje": "Incidente reportado exitosamente"
}
```

### Listar Incidentes Por Usuario

```http
    GET /incidentes/listar/{{usuarioId}}
```

#### Cuerpo de respuesta

```json
    {
  "incidentes": [
    {
      "id": 1,
      "tipo_incidente": "Robo",
      "descripcion": "Robo en la calle 1",
      "latitud": "123456",
      "longitud": "654321",
      "fecha_reporte": "2021-10-10"
    },
    {
      "id": 2,
      "tipo_incidente": "Acoso",
      "descripcion": "Acoso en la calle 2",
      "latitud": "123456",
      "longitud": "654321"
    }
  ],
  "mensaje": "Incidentes listados exitosamente"
}
```

### Listar todos los incidentes

```http
    GET /incidentes/listarTodos
```

#### Cuerpo de respuesta

```json
{
  "incidentes": [
    {
      "id": 1,
      "tipo_incidente": "Robo",
      "descripcion": "Robo en la calle 1",
      "latitud": "123456",
      "longitud": "654321",
      "fecha_reporte": "2021-10-10"
    },
    {
      "id": 2,
      "tipo_incidente": "Acoso",
      "descripcion": "Acoso en la calle 2",
      "latitud": "123456",
      "longitud": "654321"
    }
  ],
  "mensaje": "Todos los incidentes"
}
```

### Contactos de Emergencia

#### Listar Todos los Contactos de Emergencia

```http
    GET /servicios-emergencia/listar
```

#### Cuerpo de respuesta

```json
{
  "contactos": [
    {
      "id": 1,
      "tipoServicio": "Policía",
      "nombreServicio": "Policía",
      "telefonoContacto": "123456",
      "ubicacionLat": "123456",
      "ubicacionLong": "123456"
    },
    {
      "id": 2,
      "tipoServicio": "Policía",
      "nombreServicio": "Policía",
      "telefonoContacto": "123456",
      "ubicacionLat": "123456",
      "ubicacionLong": "123456"
    }
  ],
  "mensaje": "Todos los contactos de emergencia"
}
```

## Boton de Panico

### Activar Boton de Panico

```http
    POST /boton-panico/activar
```

#### Cuerpo de envio

```json
{
  "id_usuario": "",
  "latitud": "",
  "longitud": ""
}
```

#### Cuerpo de respuesta

```json
{
  "mensaje": "Boton de panico activado exitosamente",
  "botonPanico": {
    "id": 1,
    "nombres" : "Pedro adsas asd",
    "latitud": "123456",
    "longitud": "654321",
    "fecha_activacion": "2021-10-10"
  }
}
```