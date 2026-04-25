// carrito.js - Pinta la tabla del carrito y gestiona borrados

document.addEventListener("DOMContentLoaded", async function() {
    await pintarCarrito();

    document.getElementById("btn-vaciar").addEventListener("click", async function() {
        var idCarrito = getIdCarrito();
        if (idCarrito === null) return;

        var lineas = getLineasLocal();
        for (var linea of lineas) {
            await borrarLineaApi(linea.idLinea);
        }

        var carrito = await obtenerCarrito(idCarrito);
        await actualizarCarrito(idCarrito, {
            idUsuario: carrito.idUsuario,
            correoUsuario: carrito.correoUsuario,
            totalPrecio: 0.0
        });

        guardarLineasLocal([]);
        await pintarCarrito();
    });
});

async function pintarCarrito() {
    var tbody = document.querySelector("table tbody");
    var totalElem = document.querySelector("main h3");

    tbody.innerHTML = "";

    var idCarrito = getIdCarrito();
    if (idCarrito === null) {
        tbody.innerHTML = "<tr><td colspan='3'>El carrito esta vacio</td></tr>";
        totalElem.textContent = "Total: 0.00€";
        return;
    }

    try {
        var carrito = await obtenerCarrito(idCarrito);
        var lineas = getLineasLocal();

        if (lineas.length === 0) {
            tbody.innerHTML = "<tr><td colspan='3'>El carrito esta vacio</td></tr>";
        } else {
            lineas.forEach(function(linea) {
                var tr = document.createElement("tr");
                tr.innerHTML =
                    "<td>" + linea.titulo + "</td>" +
                    "<td>" + linea.precioUnitario.toFixed(2) + "€</td>" +
                    "<td><button class='btn-eliminar'>Eliminar</button></td>";

                tr.querySelector(".btn-eliminar").addEventListener("click", async function() {
                    try {
                        await borrarLineaApi(linea.idLinea);
                        var actualizadas = getLineasLocal().filter(function(l) {
                            return l.idLinea !== linea.idLinea;
                        });
                        guardarLineasLocal(actualizadas);
                        await pintarCarrito();
                    } catch (err) {
                        alert("No se pudo eliminar: " + err.message);
                    }
                });

                tbody.appendChild(tr);
            });
        }

        totalElem.textContent = "Total: " + carrito.totalPrecio.toFixed(2) + "€";
    } catch (err) {
        tbody.innerHTML = "<tr><td colspan='3'>Error al cargar el carrito</td></tr>";
        totalElem.textContent = "Total: 0.00€";
    }
}