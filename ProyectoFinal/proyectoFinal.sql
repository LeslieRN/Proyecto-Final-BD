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

create table TipoProyecto(
	idTipoProyecto int not null primary key identity(1,1),
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
	fechaIncio date not null,
	fechaFin date not null,
	fechaEntrega date not null,
	estado int not null,
	extendido int not null,
	idTipoProyecto int not null,
	constraint FK_idTipoProyecto_TipoProyecto_Proyecto foreign key (idTipoProyecto)
	references TipoProyecto(idTipoProyecto),
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