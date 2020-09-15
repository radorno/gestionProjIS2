insert into roles(NOMBRE_ROL,DESCRIPCION) values('Administrador','Administrador');
insert into roles(NOMBRE_ROL,DESCRIPCION) values('Desarrollador','Desarrollador');
insert into roles(NOMBRE_ROL,DESCRIPCION) values('Admin. y Desarrollador','Administrador y Desarrollador');
insert into usuarios(NOMBRE,APELLIDO,USER_CODE,PASSWORD,NOMBRE_ROL,FECHA_CREACION) values('admin','admin','admin','admin','Administrador','2020-01-11')
insert into linea_base(CODIGO,DESCRIPCION,FECHA_CREACION) values('ONE','bloqueados','2020-01-11')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE,COD_LINEA_BASE) values('PRESTAMOS','V1','MAXIMA','BLOQUEADO','CARGAR PRESTAMOS','URGENTE','PRESTA','BANCO','ONE')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE,COD_LINEA_BASE) values('BANCO','V1','MAXIMA','BLOQUEADO','CARGAR BANCOS','URGENTE','PRESTA',' ','ONE')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE) values('HIJO','V1','MAXIMA','INICIADO','CARGAR BANCOS','URGENTE',' ',' ')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE) values('PADRE','V1','MAXIMA','INICIADO','CARGAR BANCOS','URGENTE',' ',' ')
insert into proyectos(codigo_Proyecto,ADMIN,Fase,descripcion,estado,fecha_Inicio,fecha_fin) values('PRESTA','admin',121,'proyecto prestamos','iniciado','2020-01-11','2025-01-11');