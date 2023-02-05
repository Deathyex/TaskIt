let formCrear = document.getElementById('tarea-form');
formCrear.addEventListener('submit', function (event) {
    event.preventDefault();

    let titulo = document.getElementById('titulo').value;
    let descripcion = document.getElementById('descripcion').value;
    let fecha = document.getElementById('fecha').value;

    fetch('/tareas/crear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            titulo: titulo,
            fecha_limite: fecha,
            descripcion: descripcion
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        });
});

let formFinalizar = document.getElementById('finalizar-form');
formFinalizar.addEventListener('submit', function (event) {
    let id = document.getElementById('id_finalizar').value;

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
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });

});