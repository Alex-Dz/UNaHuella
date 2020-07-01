INSERT INTO role VALUES ('1', 'ROLE_PARTICULAR', 'PARTICULAR');
INSERT INTO role VALUES ('2', 'ROLE_VETERINARIO', 'VETERINARIO');
INSERT INTO role VALUES ('3', 'ROLE_GESTOR', 'GESTOR');

INSERT INTO usuario VALUES ('1018489863', 'Diego', 'Cárdenas', 'en tu corazón', '6666666666', 'Alexander', 'Diaz', 'noob@noob.com', 2, '1', '', '', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 1);
INSERT INTO usuario VALUES ('676282', 'Rosa', 'Superlano', 'junto al chanchito', '6666666666', 'Alejandra', 'Esquibel', 'noob@noob.com', 0, '0', '', '', '123456', 'romper kokoros', 20, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 2);
INSERT INTO usuario VALUES ('666', 'Chanchito', 'Tercero', 'en tu corazón', '6666666666', 'Eduardo', 'De las nieves', 'admin@admin.com', 0, '0', 'ser el puto amo', '5', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza', 3);

INSERT INTO user_roles VALUES ('1018489863','1');
INSERT INTO user_roles VALUES ('676282','2');
INSERT INTO user_roles VALUES ('666','3');

insert into mascota values (1, 'P', 'Thor', 'M', 'Yorkshire', 2, 'Ninguno', 'Ninguno', '123456203658', '1018489863');
insert into mascota values (2, 'P', 'Tiara', 'H', 'Yorkshire', 7, 'Ninguno', 'Ehrlichia canis', '123456203659', '1018489863');

INSERT INTO lugar VALUES (1, 'Ak. 86 #26, Bogotá', 'Unidad Primaria de Atención UPA París Gaitan', 15);
INSERT INTO lugar VALUES (2, 'Cl. 88 #95F-00, Engativá, Bogotá', 'UPA Unidad Primaria de Atención Bachué', 20);

INSERT INTO jornada VALUES  (1, parsedatetime('15/07/2020', 'dd/MM/yyyy'), 1, 'vacunación y esterilización', 666);
INSERT INTO jornada VALUES  (2, parsedatetime('20/07/2020', 'dd/MM/yyyy'), 0, 'esterilización', 666);
INSERT INTO jornada VALUES  (3, parsedatetime('30/07/2020', 'dd/MM/yyyy'), 0, 'vacunación', 666);

INSERT INTO lugar_jornada VALUES (1, 1);
INSERT INTO lugar_jornada VALUES (1, 2);
INSERT INTO lugar_jornada VALUES (2, 1);
INSERT INTO lugar_jornada VALUES (2, 2);
INSERT INTO lugar_jornada VALUES (3, 1);
INSERT INTO lugar_jornada VALUES (3, 2);

INSERT INTO cita VALUES (2, parsedatetime('09:00', 'HH:mm'), 'vacunación', 2, 1, 1);
INSERT INTO cita VALUES (25, parsedatetime('12:00', 'HH:mm'), 'vacunación', 1, 3, 1);
--lista de citas que nadie ha agendado
INSERT INTO cita VALUES (1, parsedatetime('08:00', 'HH:mm'), '', null , 1, 1);
INSERT INTO cita VALUES (3, parsedatetime('10:00', 'HH:mm'), '', null , 1, 1);
INSERT INTO cita VALUES (4, parsedatetime('11:00', 'HH:mm'), '', null , 1, 1);
INSERT INTO cita VALUES (5, parsedatetime('12:00', 'HH:mm'), '', null , 1, 1);

INSERT INTO cita VALUES (6, parsedatetime('08:00', 'HH:mm'), '', null , 1, 2);
INSERT INTO cita VALUES (7, parsedatetime('09:00', 'HH:mm'), '', null , 1, 2);
INSERT INTO cita VALUES (8, parsedatetime('10:00', 'HH:mm'), '', null , 1, 2);
INSERT INTO cita VALUES (9, parsedatetime('11:00', 'HH:mm'), '', null , 1, 2);
INSERT INTO cita VALUES (10, parsedatetime('12:00', 'HH:mm'), '', null , 1, 2);

INSERT INTO cita VALUES (11, parsedatetime('08:00', 'HH:mm'), '', null , 2, 1);
INSERT INTO cita VALUES (12, parsedatetime('09:00', 'HH:mm'), '', null , 2, 1);
INSERT INTO cita VALUES (13, parsedatetime('10:00', 'HH:mm'), '', null , 2, 1);
INSERT INTO cita VALUES (14, parsedatetime('11:00', 'HH:mm'), '', null , 2, 1);
INSERT INTO cita VALUES (15, parsedatetime('12:00', 'HH:mm'), '', null , 2, 1);
INSERT INTO cita VALUES (16, parsedatetime('08:00', 'HH:mm'), '', null , 2, 2);
INSERT INTO cita VALUES (17, parsedatetime('09:00', 'HH:mm'), '', null , 2, 2);
INSERT INTO cita VALUES (18, parsedatetime('10:00', 'HH:mm'), '', null , 2, 2);
INSERT INTO cita VALUES (19, parsedatetime('11:00', 'HH:mm'), '', null , 2, 2);
INSERT INTO cita VALUES (20, parsedatetime('12:00', 'HH:mm'), '', null , 2, 2);

INSERT INTO cita VALUES (21, parsedatetime('08:00', 'HH:mm'), '', null , 3, 1);
INSERT INTO cita VALUES (22, parsedatetime('09:00', 'HH:mm'), '', null , 3, 1);
INSERT INTO cita VALUES (23, parsedatetime('10:00', 'HH:mm'), '', null , 3, 1);
INSERT INTO cita VALUES (24, parsedatetime('11:00', 'HH:mm'), '', null , 3, 1);
INSERT INTO cita VALUES (26, parsedatetime('08:00', 'HH:mm'), '', null , 3, 2);
INSERT INTO cita VALUES (27, parsedatetime('09:00', 'HH:mm'), '', null , 3, 2);
INSERT INTO cita VALUES (28, parsedatetime('10:00', 'HH:mm'), '', null , 3, 2);
INSERT INTO cita VALUES (29, parsedatetime('11:00', 'HH:mm'), '', null , 3, 2);
INSERT INTO cita VALUES (30, parsedatetime('12:00', 'HH:mm'), '', null , 3, 2);

INSERT INTO vet_jornada VALUES (676282, 1);
INSERT INTO vet_jornada VALUES (676282, 2);
INSERT INTO vet_jornada VALUES (676282, 3);
INSERT INTO vet_lugar VALUES (676282, 1);
INSERT INTO vet_lugar VALUES (676282, 2);