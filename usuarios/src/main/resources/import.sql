insert into usuarios(username, password, enabled, nombre, apellido, email, intentos) values ('miguelgomezaguirre', '$2a$10$e/jSFNYHhtobYgb3ORDOC.mc4/c/Yh5yCh/UdTTgZQpn7o.g.DOpi', 1, 'Miguel', 'Gomez Aguirre', 'miguelgomezaguirre@hotmail.com', 0);
insert into usuarios(username, password, enabled, nombre, apellido, email, intentos) values ('admin', '$2a$10$4VBvYOTwNahNHkHuuchcZeRY/0J7FfIDyWjlbW7fwqNNQjKOhXn7S', 1, 'admin', 'admin', 'admin@hotmail.com', 0);

insert into roles (nombre) values ('ROLE_ADMIN');
insert into roles (nombre) values ('ROLE_USER');

insert into usuarios_roles(usuario_id, role_id) values (1,2);
insert into usuarios_roles(usuario_id, role_id) values (2,1);
insert into usuarios_roles(usuario_id, role_id) values (2,2);