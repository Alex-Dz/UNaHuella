alter table JORNADA add CONSTRAINT fk_Id_Gestor FOREIGN KEY (a_id_gestor) references GESTOR (id_gestor);

alter table MASCOTA add CONSTRAINT fk_Id_Particular FOREIGN KEY (i_id_particular) references PARTICULAR (id_particular) ;

alter table CITA add CONSTRAINT fk_Id_Mascota FOREIGN KEY (a_id_mascota) references MASCOTA (id_mascota);
alter table CITA add CONSTRAINT fk_Id_Jornada FOREIGN KEY (b_id_jornada) references JORNADA(id_jornada);

alter table DOSIS add CONSTRAINT fk_Id_Medicamento FOREIGN KEY (a_id_medicamento) references MEDICAMENTO(id_medicamento);
alter table DOSIS add CONSTRAINT fk_Id_MascotaD FOREIGN KEY (b_id_mascota) references MASCOTA (id_mascota);

alter table SALA add CONSTRAINT fk_Id_lugar FOREIGN KEY (b_id_lugar) references LUGAR (id_lugar);

alter table CIRUGIA add CONSTRAINT fk_Id_Veterinario FOREIGN KEY (b_id_veterinario) references VETERINARIO (id_veterinario);
alter table CIRUGIA add CONSTRAINT fk_Id_SalaC FOREIGN KEY (a_id_sala) references SALA (a_id_sala);

alter table LUGAR_JORNADA add CONSTRAINT fk_Id_LugarL FOREIGN KEY (b_id_lugar) references LUGAR (id_lugar);
alter table LUGAR_JORNADA add CONSTRAINT fk_Id_JornadaL FOREIGN KEY (a_id_jornada) references JORNADA (id_jornada);

alter table CIRUGIA alter column id_cirugia varchar (10) not null;
alter table CIRUGIA alter column a_id_sala varchar(5) NOT NULL;
alter table CIRUGIA alter column b_id_veterinario varchar(25) NOT NULL; 
alter table CIRUGIA alter column c_procedimiento varchar (300) not null;
alter table CIRUGIA alter column d_complicaciones varchar (300);

alter table CITA alter column a_id_mascota varchar(10) not null; 
alter table CITA alter column b_id_jornada varchar(10) not null;
alter table CITA alter column a_fecha_cita timestamp not null;
alter table CITA alter column b_especificacion_cita varchar (500);

alter table DOSIS alter column a_id_medicamento varchar(10) not null; 
alter table DOSIS alter column b_id_mascota varchar(10) not null; 
alter table DOSIS alter column a_cantidad_dosis varchar (25) not null;
alter table DOSIS alter column b_descripcion_dosis varchar (500) not null;

alter table GESTOR alter column id_gestor varchar(25) not null;
alter table GESTOR alter column a_primer_nombre_gestor varchar(30) not null;
alter table GESTOR alter column b_primer_apellido_gestor varchar(30) not null;
alter table GESTOR alter column c_direccion_gestor varchar (50)not null; 
alter table GESTOR alter column d_telefono_gestor varchar(15) not null; 
alter table GESTOR alter column e_segundo_nombre_gestor varchar(30); 
alter table GESTOR alter column f_segundo_apellido_gestor varchar(30); 
alter table GESTOR alter column g_correo_gestor varchar (30) not null; 
alter table GESTOR alter column h_funciones varchar (60) not null; 
alter table GESTOR alter column i_nivel_acceso varchar(1) not null;

alter table JORNADA alter column id_jornada varchar(10) not null;
alter table JORNADA alter column a_id_gestor varchar(25) NOT NULL; 
alter table JORNADA alter column b_fecha_jornada timestamp NOT NULL; 
alter table JORNADA alter column c_cant_inscritos int NOT NULL; 
alter table JORNADA alter column d_servicios varchar (700) not null;

alter table LUGAR alter column id_lugar varchar(10) not null; 
alter table LUGAR alter column a_ubicacion_lugar varchar (50) not null;
alter table LUGAR alter column b_nombre_lugar varchar (40) not null;
alter table LUGAR alter column c_capacidad_pacientes int not null;
alter table LUGAR alter column d_personal int not null;

alter table LUGAR_JORNADA alter column a_id_jornada varchar(10) not null;
alter table LUGAR_JORNADA alter column b_id_lugar varchar(10) not null;
alter table LUGAR_JORNADA alter column nombre_jornada varchar (30);

alter table MASCOTA alter column id_mascota varchar(10) not null;
alter table MASCOTA alter column a_especie varchar(1) NOT NULL; 
alter table MASCOTA alter column b_nombre_mascota varchar (20) NOT NULL;
alter table MASCOTA alter column c_genero varchar (1) NOT NULL;
alter table MASCOTA alter column d_raza varchar (25) NOT NULL;
alter table MASCOTA alter column e_edad_mascota varchar(10) NOT NULL; 
alter table MASCOTA alter column f_historial_cirugias varchar(500) NOT NULL; 
alter table MASCOTA alter column g_portador_parasito varchar(50) not null;
alter table MASCOTA alter column h_carnet_vacunacion varchar (20);
alter table MASCOTA alter column i_id_particular varchar(25) NOT NULL; 

alter table MEDICAMENTO alter column id_medicamento varchar(10) not null; 
alter table MEDICAMENTO alter column a_nombre_medicamento varchar (50) not null;
alter table MEDICAMENTO alter column b_tipo_medicamento varchar (60) not null;
alter table MEDICAMENTO alter column c_presentacion_med varchar (60) not null;
alter table MEDICAMENTO alter column d_componente_act varchar (60) not null;

alter table PARTICULAR alter column id_particular varchar(25);
alter table PARTICULAR alter column a_primer_nombre_particular varchar(30) not null;
alter table PARTICULAR alter column b_primer_apellido_particular varchar(30) not null;
alter table PARTICULAR alter column c_direccion_particular varchar (50)not null;
alter table PARTICULAR alter column d_telefono_particular varchar(15) not null;
alter table PARTICULAR alter column e_segundo_nombre_particular varchar(30);
alter table PARTICULAR alter column f_segundo_apellido_particular varchar(30);
alter table PARTICULAR alter column g_correo_particular varchar (30) not null; 
alter table PARTICULAR alter column h_cantidad_mascotas_inscritas INT;
alter table PARTICULAR alter column i_estrato varchar(1) not null;

alter table veterinario alter column id_veterinario varchar(25);
alter table veterinario alter column a_primer_nombre_veterinario varchar(30) not null;
alter table veterinario alter column b_primer_apellido_veterinario varchar(30) not null;
alter table veterinario alter column c_direccion_veterinario varchar (50)not null;
alter table veterinario alter column d_telefono_veterinario varchar(15) not null;
alter table veterinario alter column e_segundo_nombre_veterinario varchar(30);
alter table veterinario alter column f_segundo_apellido_veterinario varchar(30);
alter table veterinario alter column g_correo_veterinario varchar (30) not null; 
alter table veterinario alter column h_num_tarjetaprof varchar (30) not null;
alter table veterinario alter column i_especializacion varchar (50) not null;
alter table veterinario alter column j_anos_experiencia INT not null;

alter table SALA alter column a_id_sala varchar (5) not null;
alter table SALA alter column b_id_lugar varchar(10) not null;
alter table SALA alter column capacidad_sala int not null;


INSERT INTO VETERINARIO VALUES('1010101010','Francisco','Salgado','Carrera 13 # 26- 81, Bogota',3161111111,'Santiago','Alarcon','fssa@yopmail.com','0001','Anestesiología',3);
INSERT INTO VETERINARIO VALUES('1010101011','Alejandro','Salmona','Carrera 18 # 26- 81, Bogota',3161111112,'','','asal@yopmail.com','0002','Anestesiología',5);
INSERT INTO VETERINARIO VALUES('1010101012','Samanta','Garnica','Carrera 20 # 20- 81, Bogota',3161111113,'','','samanta2011@hotmail.com','0003','Neurología',1);
INSERT INTO VETERINARIO VALUES('1010101013','Andrea','Polo','Carrera 26 # 20- 2, Bogota',3161111114,'','Polo','polopolo@hotmail.com','0004','Oftalmología',5);
INSERT INTO VETERINARIO VALUES('1010101014','Camila','Valero','Carrera 30 # 20- 2, Bogota',3161111115,'','','camilavalero11@gmail.com','0005','Oftalmología',1);
INSERT INTO VETERINARIO VALUES('1010101015','Daniel','Villada','Carrera 103 # 20- 2, Bogota',3161111116,'Santiago','','ddvillada@gmail.com','0006','Fisioterapia',3);
INSERT INTO VETERINARIO VALUES('1010101016','Jhon','Castaño','Carrera 83 # 20- 2, Bogota',3161111117,'Alexander','','jhoncas@gmail.com','0007','Fisioterapia',1);
INSERT INTO VETERINARIO VALUES('1010101017','Natalia','Castañeda','Carrera 123 # 10- 2, Bogota',3161111118,'','','nath2012@yopmail.com','0008','Dermatología',1);
INSERT INTO VETERINARIO VALUES('1010101018','Valentina','Torres','Calle 12 # 10- 2A, Bogota',3161111120,'','','xxvalx2x@yopmail.com','0010','Cadiología',10);
INSERT INTO VETERINARIO VALUES('1010101019','Will','Smith','Calle 120 # 20- 2B, Bogota',3161111121,'','','wii@yahoo.com','0011','Cadiología',4);

INSERT INTO PARTICULAR VALUES('1010143201','David','Sarmiento','Zipaquira',3177987331,'Santiago','Malagon','davidsas@yopmail.com',1,3);
INSERT INTO PARTICULAR VALUES 
('1010143204','Daniela','Morales','Carrera 3 # 18- 45, Bogota',3134987331,'Maria','Alfonso','danielamoras@yopmail.com',2,4);
INSERT INTO PARTICULAR VALUES
('1010134804','Katalina','Ellis','Carrera 18 # 53- 2, Bogota',3124486331,'Valeria','Presly','kaylin2011@hotmail.com',1,3);
INSERT INTO PARTICULAR VALUES('1000143201','Cristiano','dos Santos','3912 Cerullo Road, Lousville', 5027792242,'Ronaldo','Aveiro','siuuuu@yopmail.com',4,6);
INSERT INTO PARTICULAR VALUES 
('1010103204','Kaleth','Morales','Av. Ciudad de Cali No. 6C-09, Bogota',3204987330,'Morales', 'Troya,','kalethdeath@yopmail.com',2,3);
INSERT INTO PARTICULAR VALUES
('1009134804','Lina','Garzon','Calle 48b sur No. 21-13, Bogota',3128886771,'','Carreño','liana1998@yahoo.com',4,2);
INSERT INTO PARTICULAR VALUES
('1021144809','Sandra','Garnica','Avenida Cra. 60 No. 57-60, Bogota',3208890222,'','','sandragar@hotmail.com',4,2);
INSERT INTO PARTICULAR VALUES
('1040322123','Manuel','Turizo','Calle 11 No. 4-21 / 93, Bogota', 3122303450,'','Zapata','manute@yopmail.com',1,3);
INSERT INTO PARTICULAR VALUES 
('1134059023','James','Rodriguez','Cr.9 # 74-99, Bogota',3187032304,'','','jajajajames@hotmail.com',1,6);
INSERT INTO PARTICULAR VALUES
('1009134704','Rebecca','Gómez','2652 Everette Alley, Miami',3058012435,'Marie','','beckyG@yahoo.com',4,2);

insert into MEDICAMENTO values ('0000001', 'Frontline','Antipulgas/Antigarrapatas','Spray','Fipronil');
insert into MEDICAMENTO values ('0000002', 'Proventis','Antipulgas/Antigarrapatas','Tabletas','Spinosad');
insert into MEDICAMENTO values ('0000003', 'Carpodril','Antiinflamatorio','Tabletas','Carpofeno');
insert into MEDICAMENTO values ('0000004', 'Hematofos','Antianémico','Solución inyectable','Cacodilato de sodio');
insert into MEDICAMENTO values ('0000005', 'Vetonic','Promotor de crecimiento','Solución oral','Vitamina A, Vitamina F, Vitamina E');
insert into MEDICAMENTO values ('0000006', 'Spot On','Antipulgas','Solución tópica cutánea','Selamectina');
insert into MEDICAMENTO values ('0000007', 'FH-10','Hepatoprotector','Solución Oral','L-Carnitina');
insert into MEDICAMENTO values ('0000008', 'Cani-Tabs','Suplemento nutricional','Tabletas','Vitaminas A, B, C, D, E, B1, B2, B3, B5');
insert into MEDICAMENTO values ('0000009', 'Ventocardyl','Analéptico cardiorespiratorio','Solución inyectable','Heptaminol');
insert into MEDICAMENTO values ('0000010', 'Vetactin','Estimulador de apetito','Solución Oral','Ciproheptadina');

INSERT INTO GESTOR VALUES('2020202020','Francisca','Salomar','Carrera 60 # 26- 82, Bogota',3262222222,'','','fssa2020@yopmail.com','Garantizar atención medica',3);
INSERT INTO GESTOR VALUES('2020202021','Alejandra','Salgado','Carrera 61 # 26- 82, Bogota',3262222222,'','','alesalgado1@yopmail.com','Prestadores de Servicios',2);
INSERT INTO GESTOR VALUES('2020202022','Samuel','Garnica','Carrera 62 # 20- 82, Bogota',3262222223,'','','samue125@hotmail.com','Orientar a los Beneficiarios',4);
INSERT INTO GESTOR VALUES('2020202023','Andres','Palomar','Carrera 63 # 20- 2, Bogota',3262222224,'','Polo','palomarpol@hotmail.com','Orientar a los Beneficiarios',1);
INSERT INTO GESTOR VALUES('2020202024','Camilo','Valencia','Carrera 64 # 20- 2, Bogota',3262222225,'','','kmilovalenci2@gmail.com','Prestadores de Servicios',1);
INSERT INTO GESTOR VALUES('2020202025','Daniela','Villa','Carrera 201 # 20- 2, Bogota',3262222226,'','','dvilla04@gmail.com','Orientar a los Beneficiarios',5);
INSERT INTO GESTOR VALUES('2020202026','Jhonny','Castañeda','Carrera 65 # 20- 2, Bogota',3262222227,'Alex','','jhonnyalex@gmail.com','Garantizar atención medica',2);
INSERT INTO GESTOR VALUES('2020202027','Nath','Castaño','Carrera 120 # 20- 2, Bogota',3262222228,'','','nathcas01@yopmail.com','Orientar a los Beneficiarios',2);
INSERT INTO GESTOR VALUES('2020202028','Valentin','Sarmiento','Calle 69 # 20- 2A, Bogota',3262222220,'','','valesarm01@yopmail.com','Prestadores de Servicios',4);
INSERT INTO GESTOR VALUES('2020202029','Was','Smith','Calle 221 # 21- 2B, Bogota',3262222222,'','','wassmith@yahoo.com','Prestadores de Servicios',2);

insert into JORNADA values ('0011','2020202020','2019-01-10',2,'Esterilizacion');
insert into JORNADA values ('0012','2020202021','2018-02-10',60,'Asesoramiento');
insert into JORNADA values ('0013','2020202022','2018-03-12',30,'Esterilizacion');
insert into JORNADA values ('0014','2020202023','2019-04-13',20,'Vacunacion');
insert into JORNADA values ('0015','2020202024','2019-05-04',160,'Asesoramiento');
insert into JORNADA values ('0016','2020202025','2020-06-24',30,'Vacunacion');
insert into JORNADA values ('0017','2020202026','2020-07-23',40,'Esterilizacion');
insert into JORNADA values ('0018','2020202027','2020-08-16',40,'Esterilizacion');
insert into JORNADA values ('0019','2020202028','2020-09-20',20,'Vacunacion');
insert into JORNADA values ('0020','2020202029','2017-05-22',40,'Esterilizacion');
insert into JORNADA values ('0021','2020202020','2021-01-11',2,'Esterilizacion');
insert into JORNADA values ('0022','2020202021','2022-11-05',60,'Asesoramiento');
insert into JORNADA values ('0023','2020202022','2020-10-11',30,'Esterilizacion');
insert into JORNADA values ('0024','2020202023','2020-05-22',20,'Vacunacion');
insert into JORNADA values ('0025','2020202024','2020-06-29',160,'Asesoramiento');
insert into JORNADA values ('0026','2020202025','2020-11-25',30,'Vacunacion');
insert into JORNADA values ('0027','2020202026','2021-02-03',40,'Esterilizacion');
insert into JORNADA values ('0028','2020202027','2021-03-15',40,'Esterilizacion');
insert into JORNADA values ('0029','2020202028','2020-03-16',20,'Vacunacion');
insert into JORNADA values ('0030','2020202029','2020-05-11',40,'Esterilizacion');
insert into JORNADA values ('0001','2020202020','2020-04-23',4,'Esterilizacion');
insert into JORNADA values ('0002','2020202021','2020-06-14',10,'Asesoramiento');
insert into JORNADA values ('0003','2020202022','2020-07-22',20,'Esterilizacion');
insert into JORNADA values ('0004','2020202023','2020-08-08',10,'Vacunacion');
insert into JORNADA values ('0005','2020202024','2020-09-21',10,'Asesoramiento');
insert into JORNADA values ('0006','2020202025','2020-10-30',30,'Vacunacion');
insert into JORNADA values ('0007','2020202026','2020-07-13',4,'Esterilizacion');
insert into JORNADA values ('0008','2020202027','2020-09-03',40,'Esterilizacion');
insert into JORNADA values ('0009','2020202028','2020-12-15',10,'Vacunacion');
insert into JORNADA values ('0010','2020202029','2020-12-07',40,'Esterilizacion');

INSERT INTO  MASCOTA VALUES('2020','G','Mishi','M','Persa','3 años', 'Esterilización', 'No es portador','100001', '1010143201');
INSERT INTO  MASCOTA VALUES('2021','P','Bambi','H','Labrador','4 años', 'Esterilización', 'No es portador','100002', '1010143204');
INSERT INTO  MASCOTA VALUES('2022','G','Misifu','H','Angora','2 meses', 'Cirugía ocular', 'No es portador','100003', '1010134804');
INSERT INTO  MASCOTA VALUES('2023','P','Rocky','M','Bulldog','4 meses', 'Cirugía digestiva, Esterilización', 'Es portador','100004', '1000143201');
INSERT INTO  MASCOTA VALUES('2024','G','Copito','M','Ragdoll','1 año', 'Esterilización', 'No es portador','100005', '1010103204');
INSERT INTO  MASCOTA VALUES('2025','P','Rock','H','Pastor Aleman','10 años', 'Esterilización', 'No es portador','100006', '1009134804');
INSERT INTO  MASCOTA VALUES('2026','G','Toretto','M','Siberiano','2 meses', 'Cirugía ocular, Cirugía odontológica', 'No es portador','100007', '1021144809');
INSERT INTO  MASCOTA VALUES('2027','P','Minimi','H','Shih Tzu','3 años', 'Esterilización', 'Es portador','100009', '1040322123');
INSERT INTO  MASCOTA VALUES('2028','G','Carlos','M','Siames','11 años', 'Esterilización, Cirugía digestiva', 'No es portador','100010', '1134059023');
INSERT INTO  MASCOTA VALUES('2029','P','Lucas','M','Labrador','7 años', 'Esterilización, Neurocirugía', 'No es portador','100011', '1009134704');
INSERT INTO  MASCOTA VALUES('2030','G','Manchitas','H','Angora','5 meses', 'Esterilización', 'No es portador','100012', '1009134804');
INSERT INTO  MASCOTA VALUES('2031','G','Motas','M','Maine Coon','4 meses', 'Esterilización', 'Es portador','100013', '1010143201');

INSERT INTO DOSIS VALUES('0000001','2020','3','Aplicar tres veces al dia');
INSERT INTO DOSIS VALUES('0000002','2021','10mg','Una vez al dia por una semana');
INSERT INTO DOSIS VALUES('0000003','2022','5mg','5mg de medicamento por cada KG de peso');
INSERT INTO DOSIS VALUES('0000005','2023','10ml','10ml de medicamento por cada KG de peso');
INSERT INTO DOSIS VALUES('0000006','2024','3','Aplicar 3 veces al dia en las zonas afectadas');
INSERT INTO DOSIS VALUES('0000007','2025','8ml','8ml de medicamento por cada KG de peso');
INSERT INTO DOSIS VALUES('0000008','2026','15mg','15ml de medicamento por cada KG de peso');
INSERT INTO DOSIS VALUES('0000009','2027','10ml','Una aplicacion semanal');
INSERT INTO DOSIS VALUES('0000010','2028','7ml','7ml de medicamento por cada KG de peso');
INSERT INTO DOSIS VALUES('0000004','2029','8ml','Una aplicacion cada dos semanas');


INSERT INTO LUGAR values ('1000300400', 'Calle 45 #34 - 43','CaniPatitas',100,35);
INSERT INTO LUGAR values ('1000373900', 'Calle 87 #15 - 76','Kanicat',75,46);
INSERT INTO LUGAR values ('1400300492', 'Calle 100 #7 - 45','CEBA Mascotas',150,60);
INSERT INTO LUGAR values ('1351300400', 'Calle 180 #19 - 43','Gatitos Peludos',100,35);
INSERT INTO LUGAR values ('1000300407', 'Calle 55 #4 - 43','DoggiePaws',80,42);
INSERT INTO LUGAR values ('1000300564', 'Carrera 5 #34 - 43','LoveAnimals',156,70);
INSERT INTO LUGAR values ('1528364910', 'Carrera 52 #108 - 3','Pets Place',100,45);
INSERT INTO LUGAR values ('1005287400', 'Carrera 45 #56 - 99','Animales',120,55);
INSERT INTO LUGAR values ('1000300411', 'Carrera 67 #34 - 90','DarkAnimals',100,45);
INSERT INTO LUGAR values ('1052700400', 'Carrera 45 #4 - 93','CaniPaws',200,80);


INSERT INTO LUGAR_JORNADA VALUES('0011','1000300400', 'jornada1');
INSERT INTO LUGAR_JORNADA VALUES('0012','1000373900', 'jornada2');
INSERT INTO LUGAR_JORNADA VALUES('0014','1400300492', 'jornada3');
INSERT INTO LUGAR_JORNADA VALUES('0010','1351300400', 'jornada4');
INSERT INTO LUGAR_JORNADA VALUES('0007','1000300407', 'jornada5');
INSERT INTO LUGAR_JORNADA VALUES('0005','1000300564', 'jornada6');
INSERT INTO LUGAR_JORNADA VALUES('0030','1528364910', 'jornada7');
INSERT INTO LUGAR_JORNADA VALUES('0027','1005287400', 'jornada8');
INSERT INTO LUGAR_JORNADA VALUES('0026','1000300411', 'jornada9');
INSERT INTO LUGAR_JORNADA VALUES('0004','1052700400', 'jornada10');

insert INTO SALA values ('A0001','1000300400',6);
insert INTO SALA values ('A0002','1000373900',8);
insert INTO SALA values ('A0003','1400300492',4);
insert INTO SALA values ('A0004','1351300400',6);
insert INTO SALA values ('A0005','1000300407',8);
insert INTO SALA values ('A0006','1000300564',8);
insert INTO SALA values ('A0007','1528364910',7);
insert INTO SALA values ('A0008','1005287400',9);
insert INTO SALA values ('A0009','1000300411',7);
insert INTO SALA values ('A0010','1052700400',5);


INSERT INTO CITA VALUES ('2021', '0011', '2019-01-10 8:00', 'Vacunacion');
INSERT INTO CITA VALUES ('2022', '0015', '2019-05-04 10:00', 'Operacion Oftalmológica');
INSERT INTO CITA VALUES ('2023', '0016', '2020-06-24 13:00', 'Gastropexia');
INSERT INTO CITA VALUES('2024', '0019', '2020-09-20 14:00', 'Vacunacion');
INSERT INTO CITA VALUES('2025', '0024', '2020-05-22 7:30', 'Cesárea');
INSERT INTO CITA VALUES('2026', '0002', '2020-06-14 11:30', 'Vacunacion');
INSERT INTO CITA VALUES('2027', '0019', '2020-09-20 15:00', 'Vacunacion');
INSERT INTO CITA VALUES ('2028', '0004', '2020-08-08 9:45', 'Vacunacion');
INSERT INTO CITA VALUES ('2029', '0010', '2020-12-07 7:30', 'Vacunacion' );
INSERT INTO CITA VALUES ('2030', '0010', '2020-12-07 8:30', 'Vacunacion');
INSERT INTO CITA VALUES ('2031', '0030', '2020-05-11 10:30', 'Vacunacion');
INSERT INTO CITA VALUES ('2021', '0027', '2021-02-03 9:00', 'Vacunacion');

