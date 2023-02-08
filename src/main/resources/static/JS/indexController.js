document.addEventListener("DOMContentLoaded", e => {
    //cargarPendientes();
})
cargarPendientes();

let formCrear = document.getElementById('tarea-form');
formCrear.addEventListener('submit', function (event) {
    event.preventDefault();

    let titulo = document.getElementById('titulo').value;
    let descripcion = document.getElementById('descripcion').value;
    let fecha = document.getElementById('fechaLimite').value;

    fetch('/tareas/crear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            titulo: titulo,
            descripcion: descripcion,
            fechaLimite: fecha
        })
    })
        .then(location.reload());
});

function finalizarTarea(id){
    fetch(`/tareas/finalizar/${id}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log('Tarea finalizada exitosamente');
            } else {
                console.error('Error al finalizar la tarea');
            }
            location.reload();
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

function borrarTarea(id){
    fetch(`/tareas/borrar/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                console.log('Tarea borrada exitosamente');
            } else {
                console.error('Error al borrar la tarea');
            }
            location.reload();
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

function consultarDetalles(id){
    fetch(`/tareas/listar/${id}`).then(resultado => resultado.json()).then(tarea => {
        let $titulo = document.getElementById("detallesTitulo");
        let $descripcion = document.getElementById("detallesDescripcion");
        let $fechaLimite = document.getElementById("detallesFechaLimite");
        let $fechaCreacion = document.getElementById("detallesFechaCreacion");
        let $fechaFinalizacion = document.getElementById("detallesFechaFinalizacion");
        let $estado = document.getElementById("detallesEstado");

        $titulo.innerHTML = tarea.titulo;
        $descripcion.innerHTML = `<b>Descripción:</b> `+tarea.descripcion;
        $fechaLimite.innerHTML = `<b>Fecha límite:</b> `+tarea.fechaLimite;
        $fechaCreacion.innerHTML = `<b>Fecha de creación:</b> `+tarea.fechaCreacion;
        $fechaFinalizacion.innerHTML = tarea.fechaFinalizacion == null ? '<b>Fecha de finalización:</b> -/-/-' : `<b>Fecha de finalización:</b> `+tarea.fechaFinalizacion;
        if(tarea.estado === 'PENDIENTE'){
            $estado.style.color = '#E09412';
        } else if(tarea.estado === 'FINALIZADA'){
            $estado.style.color = '#32c412';
        } else {
            $estado.style.color = '#838c81';
        }
        $estado.innerHTML = `<b style="color: black">Estado:</b> `+tarea.estado;
    });
}

async function cargarPendientes(){
    fetch("/tareas/listar/pendientes").then(result => result.json()).then(tareas => {
        let listadoHtml = '';
        tareas.forEach(tarea => {
            let botonEliminar = `<button type="button" onclick="borrarTarea(${tarea.id})" class="btn btn-danger" >Borrar</button>`;
            let botonDetalles = `<button type="button" onclick="consultarDetalles(${tarea.id})" class="btn btn-primary" data-toggle="modal" data-target="#modalDetalles">Detalles</button>`;
            let finalizar = `<input class="form-check-input" type="checkbox" onclick="finalizarTarea(${tarea.id})" id="flexCheckFinalizar" style="padding: 8px 10px;">`+ `<label className="form-check-label" htmlFor="flexCheckFinalizar">Finalizar</label>`;

            let fechaLimite = tarea.fechaLimite == null ? '-/-/-' : tarea.fechaLimite;

            let tareaHtml = `<tr><td>${tarea.titulo}</td><td>${tarea.descripcion}</td><td>${fechaLimite}</td><td class="tablaAcciones">${finalizar+botonEliminar+botonDetalles}</td></tr>`;

            listadoHtml += tareaHtml;
        });
        document.getElementById("tareasListado").innerHTML = listadoHtml;
    });
}

async function cargarFinalizadas(){
    fetch("/tareas/listar/finalizadas").then(result => result.json()).then(tareas => {
        let listadoHtml = '';
        tareas.forEach(tarea => {
            let botonDetalles = `<button type="button" onclick="consultarDetalles(${tarea.id})" class="btn btn-primary" data-toggle="modal" data-target="#modalDetalles">Detalles</button>`;

            let fechaLimite = tarea.fechaLimite;

            let tareaHtml = `<tr><td>${tarea.titulo}</td><td>${tarea.descripcion}</td><td>${fechaLimite}</td><td class="tablaAcciones">${botonDetalles}</td></tr>`;

            listadoHtml += tareaHtml;
        });
        document.getElementById("tareasListado").innerHTML = listadoHtml;
    });
}

async function filtrarTareas(){
    let desde = document.getElementById("fecha_desde").value;
    let hasta = document.getElementById("fecha_hasta").value;
    let texto = document.getElementById("textoBusqueda").value;
    fetch(`/listar/filtrar?fechaInicio=${desde}&fechaFin=${hasta}&texto=${texto}`).then(res => res.json()).then(tareas => {
        let listadoHtml = '';
        tareas.forEach(tarea => {
            let botonEliminar = `<button type="button" onclick="borrarTarea(${tarea.id})" class="btn btn-danger" >Borrar</button>`;
            let botonDetalles = `<button type="button" onclick="consultarDetalles(${tarea.id})" class="btn btn-primary" data-toggle="modal" data-target="#modalDetalles">Detalles</button>`;
            let finalizar = `<input class="form-check-input" type="checkbox" onclick="finalizarTarea(${tarea.id})" id="flexCheckFinalizar" style="padding: 8px 10px;">`+ `<label className="form-check-label" htmlFor="flexCheckFinalizar">Finalizar</label>`;

            let fechaLimite = tarea.fechaLimite == null ? '-/-/-' : tarea.fechaLimite;

            let tareaHtml = `<tr><td>${tarea.titulo}</td><td>${tarea.descripcion}</td><td>${fechaLimite}</td><td class="tablaAcciones">${finalizar+botonEliminar+botonDetalles}</td></tr>`;

            listadoHtml += tareaHtml;
        });
        document.getElementById("tareasListado").innerHTML = listadoHtml;
    });
}