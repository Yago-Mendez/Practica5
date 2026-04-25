| Método | Ruta | Cuerpo (Body) | Descripción | Posibles Respuestas |
| :--- | :--- | :--- | :--- | :--- |
| POST | /api/carrito | JSON del Carrito | Crea un nuevo carrito y lo guarda en memoria. | 201|
| GET | /api/carrito/{id} | Ninguno | Busca y devuelve la información de un carrito por su ID. | 200,404 |
| PUT | /api/carrito/{id} | JSON del Carrito | Actualiza los datos de un carrito existente. | 200,404 |
| DELETE | /api/carrito/{id} | Ninguno | Elimina un carrito del sistema. | 204,404 |
