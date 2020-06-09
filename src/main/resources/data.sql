INSERT INTO role VALUES ('1', 'ROLE_PARTICULAR', 'PARTICULAR');
INSERT INTO role VALUES ('2', 'ROLE_VETERINARIO', 'VETERINARIO');
INSERT INTO role VALUES ('3', 'ROLE_GESTOR', 'GESTOR');

INSERT INTO usuario VALUES ('1018489863', 'Diego', 'Cárdenas', 'en tu corazón', '6666666666', 'Alexander', 'Diaz', 'noob@noob.com', 9, '1', '', '', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza');
INSERT INTO usuario VALUES ('676282', 'Rosa', 'Superlano', 'junto al chanchito', '6666666666', 'Alejandra', 'Esquibel', 'noob@noob.com', 0, '0', '', '', '123456', 'romper kokoros', 20, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza');
INSERT INTO usuario VALUES ('666', 'Chanchito', 'Tercero', 'en tu corazón', '6666666666', 'Eduardo', 'De las nieves', 'admin@admin.com', 0, '0', 'ser el puto amo', '5', '', '', 0, '$2a$05$VyssKnpu57dUriNAPnkzK.MKu45Q8NAteLFs957WGK5KoJRzXPsza');

INSERT INTO user_roles VALUES ('1018489863','1');
INSERT INTO user_roles VALUES ('676282','2');
INSERT INTO user_roles VALUES ('666','3');