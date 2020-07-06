INSERT INTO role VALUES ('1', 'ROLE_PARTICULAR', 'PARTICULAR');
INSERT INTO role VALUES ('2', 'ROLE_VETERINARIO', 'VETERINARIO');
INSERT INTO role VALUES ('3', 'ROLE_GESTOR', 'GESTOR');

INSERT INTO usuario VALUES ('1018489863', 'Diego', 'Cárdenas', 'en tu corazón', '6666666666', 'Alexander', 'Diaz', 'noob@noob.com', 3, '1', '', '', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 1);
INSERT INTO usuario VALUES ('676282', 'Rosa', 'Superlano', 'junto al chanchito', '6666666666', 'Alejandra', 'Esquibel', 'noob@noob.com', 0, '0', '', '', '123456', 'romper kokoros', 20, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 2);
INSERT INTO usuario VALUES ('666', 'Chanchito', 'Tercero', 'en tu corazón', '6666666666', 'Eduardo', 'De las nieves', 'admin@admin.com', 0, '0', 'ser el puto amo', '5', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 3);

INSERT INTO usuario VALUES ('10027', 'Betsabé', 'Sanoga', 'PLwXIrrH', '35981382', 'Michay', 'Gamboa', 'HFuilPdEs', 0, '', '', '', '681177462390', 'Rehabilitación', 14, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 2);

INSERT INTO user_roles VALUES ('1018489863','1');
INSERT INTO user_roles VALUES ('676282','2');
INSERT INTO user_roles VALUES ('666','3');

INSERT INTO user_roles VALUES ('10027','2');

insert into mascota values (1, 'P', 'Thor', 'M', 'Yorkshire', 2, 'Ninguno', 'Ninguno', '123456203658', '1018489863');
insert into mascota values (2, 'P', 'Tiara', 'H', 'Yorkshire', 7, 'Ninguno', 'Ehrlichia canis', '123456203659', '1018489863');
insert into mascota values (3, 'G', 'Ozai', 'M', 'Criollo', 7, 'Ninguno', 'Ninguno', '', '1018489863');

INSERT INTO lugar VALUES (1, 'Ak. 86 #26, Bogotá', 'Unidad Primaria de Atención UPA París Gaitan', 15);
INSERT INTO lugar VALUES (2, 'Cl. 88 #95F-00, Engativá, Bogotá', 'UPA Unidad Primaria de Atención Bachué', 20);

INSERT INTO jornada VALUES  (1, STR_TO_DATE('15/07/2020', '%d/%m/%Y'), 1, 'vacunación y esterilización', 666);
INSERT INTO jornada VALUES  (2, STR_TO_DATE('20/07/2020', '%d/%m/%Y'), 0, 'esterilización', 666);
INSERT INTO jornada VALUES  (3, STR_TO_DATE('30/07/2020', '%d/%m/%Y'), 0, 'vacunación', 666);

INSERT INTO lugar_jornada VALUES (1, 1);
INSERT INTO lugar_jornada VALUES (1, 2);
INSERT INTO lugar_jornada VALUES (2, 1);
INSERT INTO lugar_jornada VALUES (2, 2);
INSERT INTO lugar_jornada VALUES (3, 1);
INSERT INTO lugar_jornada VALUES (3, 2);

INSERT INTO cita VALUES (2, '09:00:00', 'vacunación', 2, 1, 1);
INSERT INTO cita VALUES (25, '12:00:00', 'vacunación', 1, 3, 1);
--lista de citas que nadie ha agendado
INSERT INTO cita VALUES (1, '08:00:00', '', null , 1, 1);
INSERT INTO cita VALUES (3, '10:00:00', '', null , 1, 1);
INSERT INTO cita VALUES (4, '11:00:00', '', null , 1, 1);
INSERT INTO cita VALUES (5, '12:00:00', '', null , 1, 1);

INSERT INTO cita VALUES (6, '08:00:00', '', null , 1, 2);
INSERT INTO cita VALUES (7, '09:00:00', '', null , 1, 2);
INSERT INTO cita VALUES (8, '10:00:00', '', null , 1, 2);
INSERT INTO cita VALUES (9, '11:00:00', '', null , 1, 2);
INSERT INTO cita VALUES (10, '12:00:00', '', null , 1, 2);

INSERT INTO cita VALUES (11, '08:00:00', '', null , 2, 1);
INSERT INTO cita VALUES (12, '09:00:00', '', null , 2, 1);
INSERT INTO cita VALUES (13, '10:00:00', '', null , 2, 1);
INSERT INTO cita VALUES (14, '11:00:00', '', null , 2, 1);
INSERT INTO cita VALUES (15, '12:00:00', '', null , 2, 1);
INSERT INTO cita VALUES (16, '08:00:00', '', null , 2, 2);
INSERT INTO cita VALUES (17, '09:00:00', '', null , 2, 2);
INSERT INTO cita VALUES (18, '10:00:00', '', null , 2, 2);
INSERT INTO cita VALUES (19, '11:00:00', '', null , 2, 2);
INSERT INTO cita VALUES (20, '12:00:00', '', null , 2, 2);

INSERT INTO cita VALUES (21, '08:00:00', '', null , 3, 1);
INSERT INTO cita VALUES (22, '09:00:00', '', null , 3, 1);
INSERT INTO cita VALUES (23, '10:00:00', '', null , 3, 1);
INSERT INTO cita VALUES (24, '11:00:00', '', null , 3, 1);
INSERT INTO cita VALUES (26, '08:00:00', '', null , 3, 2);
INSERT INTO cita VALUES (27, '09:00:00', '', null , 3, 2);
INSERT INTO cita VALUES (28, '10:00:00', '', null , 3, 2);
INSERT INTO cita VALUES (29, '11:00:00', '', null , 3, 2);
INSERT INTO cita VALUES (30, '12:00:00', '', null , 3, 2);

INSERT INTO vet_jornada VALUES (676282, 1);
INSERT INTO vet_jornada VALUES (676282, 2);
INSERT INTO vet_jornada VALUES (676282, 3);
INSERT INTO vet_jornada VALUES (10027, 1);
INSERT INTO vet_jornada VALUES (10027, 2);
INSERT INTO vet_jornada VALUES (10027, 3);
INSERT INTO vet_lugar VALUES (676282, 1);
INSERT INTO vet_lugar VALUES (10027, 2);

-- dueños para times de mascotas
INSERT INTO usuario VALUES ('1058626239', 'Samira', 'Cárdenas', 'Calle 152a #109-45', '3212345678', 'Camila', 'Diaz', 'samicami@gmail.com', 0, '1', '', '', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 1);
INSERT INTO usuario VALUES ('1022226239', 'Samuel', 'Gonzáles', 'Calle 152a #109-45', '3212345678', 'Alejandro', 'Lobo', 'elsamuro@gmail.com', 0, '1', '', '', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 1);

INSERT INTO user_roles VALUES ('1058626239','1');
INSERT INTO user_roles VALUES ('1022226239','1');