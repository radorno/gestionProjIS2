insert into roles(NOMBRE_ROL,DESCRIPCION) values('Administrador','Administrador');
insert into roles(NOMBRE_ROL,DESCRIPCION) values('Desarrollador','Desarrollador');
insert into roles(NOMBRE_ROL,DESCRIPCION) values('Admin. y Desarrollador','Administrador y Desarrollador');
insert into usuarios(NOMBRE,APELLIDO,USER_CODE,PASSWORD,NOMBRE_ROL,FECHA_CREACION) values('admin','admin','admin','admin','Administrador','2020-01-11')
insert into linea_base(CODIGO,DESCRIPCION,FECHA_CREACION) values('ONE','bloqueados','2020-01-11')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE,COD_LINEA_BASE) values('PRESAMOS','V1','MAXIMA','BLOQUEADO','CARGAR PRESTAMOS','URGENTE','PRESTA','BANCO','ONE')
insert into tareas(CODIGO_TAREA,VERSION,PRIORIDAD,ESTADO,DESCRIPCION,OBSERVACION,CODIGO_PROYECTO,TAREA_PADRE,COD_LINEA_BASE) values('BANCO','V1','MAXIMA','BLOQUEADO','CARGAR BANCOS','URGENTE','PRESTA',' ','ONE')