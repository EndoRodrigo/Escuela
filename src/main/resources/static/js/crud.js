window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

	listadoEstudiantes();
	
    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
});

function getHeader(){
	return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    }
}

async function listadoEstudiantes(){
	const request = await fetch('api/Estudiante', {
    method: 'GET',
    headers: getHeader()
  });

  const estudiantes = await request.json();
  console.log(estudiantes);
  
   let listadoHtml = '';
  for(let students of  estudiantes){
	let studentsHtml = '<tr><td>'+students.id+'</td><td>'+students.nombre+'</td><td>'+students.apellido+'</td><td>'+students.edad+'</td>'
	                   +'<td>'+students.genero+'</td><td>'+students.curso+'</td><td>'+students.matricula+'</td></tr>';
	listadoHtml+=studentsHtml;
}

document.querySelector('#datatablesSimple tbody').outerHTML = listadoHtml;
}


async function registraEstudiante(){
	let datos = {};
	datos.nombre = document.getElementById('txtNombre').value;
	datos.apellido = document.getElementById('txtApellido').value;
	datos.edad = parseInt(document.getElementById('txtEdad').value); ;
	let tipoGenero = document.getElementById('txtGenero');
	datos.genero = tipoGenero.options[tipoGenero.selectedIndex].value;
	datos.curso = document.getElementById('txtCurso').value;
	datos.matricula = document.getElementById('txtMatricula').checked;
	
	const request = await fetch('api/register', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
  const estudiantes = await request.json();
  console.log(estudiantes);
  alert("La cuenta fue creada con exito!");

}

async function eliminarEstudiante(){
 let id = document.getElementById('txtid').value;
 console.log(id);
	const request = await fetch('api/delete/'+id, {
    method: 'DELETE',
    headers: getHeader()
  });
  
  const estudiantes = await request.json();
  console.log(estudiantes);
  
    location.reload();

}

async function estudianteSelecionado(){
	
	let id = document.getElementById('txtid').value;
	
	const request = await fetch('api/alumno/'+id, {
    method: 'GET',
    headers: getHeader()
  });
  
    const estudiantes = await request.json();
    console.log(estudiantes);
    
    document.getElementById('txtNombre').value = estudiantes.nombre;
    document.getElementById('txtApellido').value = estudiantes.apellido;
    document.getElementById('txtEdad').value = estudiantes.edad;
    document.getElementById('txtGenero').value = estudiantes.genero;
    document.getElementById('txtCurso').value = estudiantes.curso;
    document.getElementById('txtMatricula').checked = estudiantes.matricula;

}

async function actualizarEstudiante(){
	let datos = {};
	datos.id = parseInt(document.getElementById('txtid').value);
	datos.nombre = document.getElementById('txtNombre').value;
	datos.apellido = document.getElementById('txtApellido').value;
	datos.edad = parseInt(document.getElementById('txtEdad').value); ;
	let tipoGenero = document.getElementById('txtGenero');
	datos.genero = tipoGenero.options[tipoGenero.selectedIndex].value;
	datos.curso = document.getElementById('txtCurso').value;
	datos.matricula = document.getElementById('txtMatricula').checked;
	
	const request = await fetch('api/actualizar', {
    method: 'PUT',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
  const estudiantes = await request.json();
  console.log(estudiantes);
  alert("Informacion actualizada con exito!");

}
