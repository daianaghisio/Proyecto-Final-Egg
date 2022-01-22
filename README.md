# Proyecto-Final-Egg
MigraEmprende App

El objetivo principal de este proyecto fue crear un nuevo espacio para la Fundacion MIGRA,
donde las personas en situacion de movilidad pudieran adquirir conocimientos, gestionar y 
dar a conocer eficazmente su negocio, contar con todas las novedades en un mismo lugar y
conocer las leyes que los afectan.

La web cuenta con un sistema de login, donde la persona puede crear una cuenta e iniciar sesion, 
en caso de no recordar la contraseña, se le pedira al usuario el email con el cual se registro 
y desde el back se verificara que el email ingresado figure en nuestras bases de datos, de ser asi, el usuario
recibira un email en su casilla con un URL para cambiar su contraseña. 
Iniciar sesion es necesario para poder acceder a otros espacios como el foro, el perfil y la seccion de emprendimientos. 

Creamos una seccion de consultas, con un formulario y sistema mail sender, como el utilizado para la 
recuperacion de contraseñas, en esta seccion el usuario puede detallar sus dudas y MigraEmprende
recibira un email con la consulta y los datos del usuario para poder contestar.

La seccion de Leyes brinda a la persona en situacion de movilidad la informacion que necesita.
Por ejemplo, sobre que deberia hacer si tiene una PYME, como obtener un CUIT en Argentina y las leyes que los ampara.
Esta seccion fue desarrollada con HTML y CSS unicamente, al igual que las secciones de Noticias y Capacitaciones.

Nuestro proyecto cuenta con cuatro CRUDs.

El usuario puede crear su emprendimiento a traves de un formulario y luego modificarlo o eliminarlo si asi lo desea.
Para esto debera haber ingresado, ya que cada emprendimiento pertenece a un unico usuario 
y nadie mas puede administrarlos por ellos. En la seccion Emprendimientos podemos ver todos 
los ya existentes, si le damos clic a un emprendimiento nos lleva a una pagina con informacion especifica del mismo
y los datos de contacto.

Creamos tambien un foro, que es una seccion de consultas interna donde la persona podra comunicarse con otros usuarios
de la pagina creando topicos y respondiendo a topicos creados por otras personas.
Cada topico y comentario posee la foto del usuario (utilizamos multipartFile en Spring Boot para mostrar imagenes),
el nombre, la fecha y la hora. Presionando sobre el nombre o la foto podemos acceder al perfil del usuario.
Cualquier usuario puede responder cualquier topico pero solo puede eliminar sus propios topicos y/o respuestas.

