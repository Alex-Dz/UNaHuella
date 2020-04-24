
create table PARTICULAR (
    id_particular char(25) PRIMARY KEY, 
    primer_nombre_particular CHAR(30) not null, 
    primer_apellido_particular CHAR(30) not null, 
    direccion_particular CHAR (50)not null, 
    telefono_particular CHAR(15) not null, 
    segundo_nombre_particular CHAR(30), 
    segundo_apellido_particular CHAR(30), 
    correo_particular CHAR (30) not null, 
    cantidad_mascotas_inscritas INT, 
    estrato CHAR(1) not null);

create table VETERINARIO (
    id_veterinario char(25) PRIMARY KEY, 
    primer_nombre_veterinario CHAR(30) not null, 
    primer_apellido_veterinario CHAR(30) not null, 
    direccion_veterinario CHAR (50)not null, 
    telefono_veterinario CHAR(15) not null, 
    segundo_nombre_veterinario CHAR(30), 
    segundo_apellido_veterinario CHAR(30), 
    correo_veterinario CHAR (30) not null, 
    num_tarjetaprof CHAR (30) not null, 
    especializacion CHAR (50) not null, 
    anos_experiencia INT not null);

create table GESTOR (id_gestor char(25) PRIMARY KEY, primer_nombre_gestor CHAR(30) not null, primer_apellido_gestor CHAR(30) not null, 
direccion_gestor CHAR (50)not null, telefono_gestor CHAR(15) not null, segundo_nombre_gestor CHAR(30), segundo_apellido_gestor CHAR(30), 
correo_gestor CHAR (30) not null, funciones CHAR (60) not null, nivel_acceso CHAR(1) not null);

create table JORNADA (id_jornada char(10) not null, PRIMARY KEY (id_jornada), id_gestor char(25) NOT NULL, FOREIGN KEY (id_gestor) references GESTOR (id_gestor),
fecha_jornada date NOT NULL, cant_inscritos int NOT NULL, servicios char (700));

create table MASCOTA (id_mascota char(10) not null, PRIMARY KEY (id_mascota), especie char(1) NOT NULL, id_particular char(25) NOT NULL, 
FOREIGN KEY (id_particular) references PARTICULAR (id_particular), id_veterinario char(25) NOT NULL, FOREIGN KEY (id_veterinario) references 
VETERINARIO (id_veterinario), nombre_mascota char (20) NOT NULL, genero char (1) NOT NULL, raza char (25) NOT NULL, edad_mascota char(10) NOT NULL, 
historial_cirugias char(500) NOT NULL, portador_parasito char(50) not null, carnet_vacunacion char (20));

create table CITA (id_mascota char(10) not null, id_jornada char(10) not null, FOREIGN KEY (id_mascota) references MASCOTA (id_mascota), 
FOREIGN KEY (id_jornada) references JORNADA(id_jornada), PRIMARY KEY (id_mascota, id_jornada), fecha_cita datetime not null, especificacion_cita char (500));

create table MEDICAMENTO (id_medicamento char(10) not null, PRIMARY KEY (id_medicamento), nombre_medicamento char (50) not null, tipo_medicamento char (60) not null, 
presentacion_med char (60) not null, componente_act char (60) not null);

create table DOSIS (id_mascota char(10) not null, id_jornada char(10) not null, FOREIGN KEY (id_mascota) references MASCOTA (id_mascota), 
id_medicamento char(10) not null, FOREIGN KEY (id_medicamento) references MEDICAMENTO(id_medicamento), 
PRIMARY KEY (id_mascota, id_medicamento),
cantidad_dosis char (25),
descripcion_dosis char (500));

create table LUGAR (id_lugar char(10) PRIMARY KEY, ubicacion_lugar char (50) not null, nombre_lugar char (40) not null, capacidad_pacientes int not null, 
personal int not null);

create table SALA (id_sala char (5) not null, 
id_lugar char(10) not null, 
FOREIGN KEY (id_lugar) references LUGAR (id_lugar), 
PRIMARY KEY (id_sala, id_lugar),
capacidad_sala int not null);

create table CIRUGIA (id_cirugia char (10) not null,
id_sala char (5) not null, 
id_veterinario char(25) NOT NULL, 
FOREIGN KEY (id_veterinario) references VETERINARIO (id_veterinario),
FOREIGN KEY (id_sala) references SALA (id_sala), 
PRIMARY KEY (id_cirugia),
procedimiento char (300) not null,
complicaciones char (300));

create table LUGAR_JORNADA (id_lugar char(10) not null, 
FOREIGN KEY (id_lugar) references LUGAR (id_lugar),
id_jornada char(10) not null,
FOREIGN KEY (id_jornada) references JORNADA (id_jornada),
nombre_jornada char (30));
