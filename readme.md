#gestion de proyecto Is2
#LISTA DE INTEGRANES
*Ruth Almirón
*Rodrigo Adorno
*Sergio Benegas
*Justo Almirón
# El proyecto se encuentra en la carpeta proyectoIs2
#Entrega_01: 08/03 Login + Dashboard inicial
* permite ingresar al sistema desde la pantalla de login
* se visualiza el dashboard al iniciar con los botones de acceso a los modulos
#Entrega_02: 15/03 Modulo Administracion (Crear Roles, Crear Usuarios, Asignar Roles a Usuarios)
* permite crear, editar, modificar Roles
* permite crear, editar, modificar Usuarios
* permite asignar roles a Usuarios
#Entrega_03: 22/03 Modulo Desarrollo (Crear Proyecto, Crear Tareas, sin Conectar todavia)
* permite crear, editar, modificar Proyectos
* permite crear, editar, modificar Tareas. El objeto Tarea tendra al menos las siguientes propiedades
{ id: numerico,
 version: string,
 prioridad: string,
 estado: string, // las opciones son [iniciado, pendiente, finalizado]
 descripcion: string,
 observacion: string,
 id_tarea_padre: numerico
}
#Entrega_04: 19/04 Modulo Desarrollo (Agregar Tareas a proyecto, Conectar Tareas entre si)
* permite asignar Tareas a un Proyecto
* permite conectar Tareas entre si (Las Tareas finales de una Fase se conectan a las tareas iniciales de la Fase siguiente.)
#Entrega_05: 26/04 Modulo Gestion de Configuracion (crear linea base)
* permite crear Linea Base
* verificar que las tareas que forman parte de la Linea Base no se pueden editar
#Entrega_06: 10/05 Modulo Gestion de Configuracion (Visualizar lineas bases en Tabla)
* al acceder al Dashboard, en la seccion de Gestion de Configuracion, se visualiza la tabla de Lineas Base existentes
* la Tabla debera mostrar Descripcion de las Tareas que conforman la Linea Base, estado y version de cada Tarea