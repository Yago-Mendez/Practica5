// api.js - Consumo del API REST
// Ruta relativa porque frontend y API estan en el mismo servidor
const API_BASE = "/api/carrito";

// --- Persistencia local del id de carrito ---
function getIdCarrito() {
    const id = localStorage.getItem("idCarrito");
    return id ? Number(id) : null;
}
function setIdCarrito(id) {
    localStorage.setItem("idCarrito", id);
}
function limpiarIdCarrito() {
    localStorage.removeItem("idCarrito");
    localStorage.removeItem("lineasCarrito");
}

// --- Lineas guardadas en localStorage ---
function getLineasLocal() {
    const raw = localStorage.getItem("lineasCarrito");
    return raw ? JSON.parse(raw) : [];
}
function guardarLineasLocal(lineas) {
    localStorage.setItem("lineasCarrito", JSON.stringify(lineas));
}

// --- LLAMADAS A LA API ---

// POST /api/carrito
async function crearCarrito(idUsuario, correoUsuario) {
    const res = await fetch(API_BASE, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            idUsuario: idUsuario,
            correoUsuario: correoUsuario,
            totalPrecio: 0.0
        })
    });
    if (!res.ok) throw new Error("Error al crear el carrito (HTTP " + res.status + ")");
    return await res.json();
}

// GET /api/carrito/{id}
async function obtenerCarrito(id) {
    const res = await fetch(API_BASE + "/" + id);
    if (!res.ok) throw new Error("Error al obtener el carrito (HTTP " + res.status + ")");
    return await res.json();
}

// DELETE /api/carrito/{id}
async function borrarCarrito(id) {
    const res = await fetch(API_BASE + "/" + id, { method: "DELETE" });
    if (!res.ok && res.status !== 204) {
        throw new Error("Error al borrar el carrito (HTTP " + res.status + ")");
    }
}

// POST /api/carrito/{idCarrito}/lineas
async function anadirLinea(idCarrito, idArticulo, precioUnitario, numeroUnidades) {
    const res = await fetch(API_BASE + "/" + idCarrito + "/lineas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            idArticulo: idArticulo,
            precioUnitario: precioUnitario,
            numeroUnidades: numeroUnidades
        })
    });
    if (!res.ok) throw new Error("Error al anadir la linea (HTTP " + res.status + ")");
    return await res.json();
}

// PUT /api/carrito/{id}
async function actualizarCarrito(id, datos) {
    const res = await fetch(API_BASE + "/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datos)
    });
    if (!res.ok) throw new Error("Error al actualizar el carrito (HTTP " + res.status + ")");
    return await res.json();
}

// DELETE /api/carrito/lineas/{idLinea}
async function borrarLineaApi(idLinea) {
    const res = await fetch(API_BASE + "/lineas/" + idLinea, { method: "DELETE" });
    if (!res.ok && res.status !== 204) {
        throw new Error("Error al borrar la linea (HTTP " + res.status + ")");
    }
}

// --- Utilidad: asegurar que hay un carrito ---
async function asegurarCarrito() {
    let id = getIdCarrito();
    if (id !== null) {
        try {
            await obtenerCarrito(id);
            return id;
        } catch (e) {
            limpiarIdCarrito();
        }
    }
    const carrito = await crearCarrito(1, "usuario@mangax.com");
    setIdCarrito(carrito.id);
    return carrito.id;
}