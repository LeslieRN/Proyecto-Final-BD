/* CREACION DE LA BASE DE DATOS */
create database proyectoFinal_grupo1

/* CREACION DE LAS TABLAS */

use proyectoFinal_grupo1

create table Cliente (
	cedula int not null primary key,
	nombre varchar (50) not null,
	telefono varchar (50),
	direccion varchar (50)
);

create table Contrato (
	numeroContrato int not null primary key identity(1,1),
	montoTotal int not null,
	penalidad int,
	cedula int not null,
	constraint FK_cedula_cliente_Contrato foreign key (cedula)
	references Cliente(cedula)
);

create table Lenguaje (
	idLenguaje int not null primary key identity(1,1),
	nombre varchar (50)
);

create table TipoUsuario(
	idTipoUsuario int not null primary key identity(1,1),
	nombre varchar (50)
);

create table Usuario(
	codigo int not null primary key identity(1,1),
	nombre varchar(50) not null,
	contraseña varchar (50) not null,
	idTipoUsuario int not null,
	constraint FK_idTipoUsuario_TipoUsuario_Usuario foreign key (idTipoUsuario)
	references TipoUsuario(idTipoUsuario)
);

create table Puesto(
	idPuesto int not null primary key identity(1,1),
	nombre varchar (50)
);

create table Empleado (
	cedula int not null primary key,
	nombre varchar (50) not null,
	apellido varchar (50) not null,
	sexo varchar (1) not null,
	direccion varchar(50),
	evaluacion varchar(50),
	salario float not null,
	precioHora float not null,
	experiencia int,
	idPuesto int not null,
	constraint FK_idPuesto_Puesto_Empleado foreign key (idPuesto)
	references Puesto(idPuesto)
);

create table EmpleadoLenguaje (
	idLenguaje int not null,
	cedula int not null,
	constraint FK_cedula_Empleado_EmpleadoLenguaje foreign key (cedula)
	references Empleado(cedula),
	constraint FK_idLenguaje_Lenguaje_EmpleadoLenguaje foreign key (idLenguaje)
	references Lenguaje(idLenguaje)
);

create table Proyecto (
	idProyecto int not null primary key identity (1, 1),
	numeroContrato int not null,
	constraint FK_numeroContrato_Contrato foreign key (numeroContrato)
	references Contrato(numeroContrato),
	nombre varchar(50) not null,
	tipoProyecto varchar(50),
	fechaIncio date not null,
	fechaFin date not null,
	fechaEntrega date not null,
	estado int not null,
	extendido int not null,
	idLenguaje int not null,
	constraint FK_idLenguaje_Lenguaje_Proyecto foreign key (idLenguaje)
	references Lenguaje(idLenguaje),
);

create table ProyectoEmpleado (
	cedula int not null,
	idProyecto int not null,
	constraint FK_cedula_Empleado_ProyectoEmpleado foreign key (cedula)
	references Empleado(cedula),
	constraint FK_idProyecto_Proyecto_ProyectoEmpleado foreign key (idProyecto)
	references Proyecto(idProyecto)

);

create table Historial (
	idHistorial int not null primary key identity (1, 1),
	descripcion varchar (50) not null,
	codigo int not null,
	constraint FK_codigo_Usuario_Historial foreign key (codigo)
	references Usuario(codigo),
);


/* QUERYS UTILIZADOS EN EL PROGRAMA */

select TOP 1 E.cedula, E.nombre, E.apellido, E.precioHora, E.idPuesto from Empleado as E where E.cedula = 402130999

/* "Cedula", "Nombre","Telefono", "Direccion", "Proyectos Activos" */

select CL.cedula, CL.nombre, CL.telefono, CL.direccion, count(PR.idProyecto) as proyectosActivos from Cliente as CL inner join (Proyecto as PR inner join Contrato as CO on PR.numeroContrato = CO.numeroContrato) on CL.cedula = CO.cedula group by CL.cedula, CL.nombre, CL.telefono, CL.direccion

/* Num. Contrato", "Cedula Cliente", "Nombre Cliente", "Fecha Inicio", "Fecha Entrega", "Monto Total" */

select CO.numeroContrato, CL.cedula, CL.nombre, PR.fechaIncio, PR.fechaEntrega, CO.montoTotal from (Contrato as CO inner join Cliente as CL on CO.cedula = CL.cedula) inner join Proyecto as PR on PR.numeroContrato = CO.numeroContrato

/* "Nombre", "Tipo", "Lenguaje", "Fecha Inicio", "Fecha Terminacion" */

select PR.nombre, TP.nombre, LE.nombre, PR.fechaIncio, PR.fechaFin from (Proyecto as PR inner join TipoProyecto as TP on PR.id_TipoProyecto = TP.id_TipoProyecto) inner join Lenguaje as LE on LE.idLenguaje = PR.idLenguaje

select PR.idProyecto, PR.estado from Proyecto as PR

update Proyecto set fechaFin = '', fechaEntrega = '', extendido = 1 where nombre = ''

/** CAMBIAR DE FECHA UN PROYECTO DADO EL NOMBRE DEL MISMO **/
update Proyecto set estado=0 where nombre = ''


/** MOSTRAR TODOS LOS USUARIOS Y EL TIPO DE LO MISMOS **/
select US.nombre, TU.nombre from Usuario as US inner join TipoUsuario as TU on US.idTipoUsuario = TU.idTipoUsuario

/** RETORNA RETORNA 0 SI EL USUARIO NO EXISTE **/
select count(US.nombre) from Usuario as Us where US.nombre = ''

/** FUNCION QUE CREA UN NUEVO USUARIO **/
select * from usuario;
select * from TipoUsuario
insert into Usuario(nombre, contraseña, idTipoUsuario) values ('Secretaria', 'Secre', 2);