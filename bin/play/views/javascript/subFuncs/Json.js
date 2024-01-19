function conteoElementos() {
    var url = 'json/detectados1.json';

    // Devuelve la promesa que resuelve a la cantidad de elementos
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  // Devuelve la promesa que resuelve a los datos JSON
        })
        .then(datos => {
            // Trabaja con los datos JSON
            console.log(datos);

            const cantidadElementos = Object.keys(datos).length;
            return cantidadElementos;  // Devuelve la cantidad de elementos
        })
        .catch(error => {
            console.error('Error:', error);
            return 0;  // Manejo de error, puedes ajustar esto según tus necesidades
        });
}