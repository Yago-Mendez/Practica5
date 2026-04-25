// productos.js - Botones "Anadir al Carrito" llaman a la API

document.addEventListener("DOMContentLoaded", function() {
    var botones = document.querySelectorAll(".btn-add");

    botones.forEach(function(btn, indice) {
        var seccion = btn.closest(".seccion-manga");
        var titulo = seccion.querySelector("h2").textContent.trim();
        var precioTexto = seccion.querySelector(".precio-grande").textContent;
        var precio = parseFloat(precioTexto.replace("€", "").replace(",", ".").trim());
        var idArticulo = indice + 1;

        btn.addEventListener("click", async function() {
            btn.disabled = true;
            var textoOriginal = btn.textContent;
            btn.textContent = "Anadiendo...";

            try {
                var idCarrito = await asegurarCarrito();
                var linea = await anadirLinea(idCarrito, idArticulo, precio, 1);

                var lineas = getLineasLocal();
                lineas.push({
                    idLinea: linea.id,
                    idArticulo: idArticulo,
                    titulo: titulo,
                    precioUnitario: precio,
                    numeroUnidades: 1,
                    costeLinea: linea.costeLinea
                });
                guardarLineasLocal(lineas);

                btn.textContent = "Anadido ✓";
                setTimeout(function() {
                    btn.textContent = textoOriginal;
                    btn.disabled = false;
                }, 1200);
            } catch (err) {
                alert("No se pudo anadir al carrito: " + err.message);
                btn.textContent = textoOriginal;
                btn.disabled = false;
            }
        });
    });
});