insert into usuarios(username, password, enabled, nombre, apellido, email) values ('miguelgomezaguirre', '1234', 1, 'Miguel', 'Gomez Aguirre', 'miguelgomezaguirre@hotmail.com');
insert into usuarios(username, password, enabled, nombre, apellido, email) values ('admin', '1234', 1, 'admin', 'admin', 'admin@hotmail.com');

insert into roles (nombre) values ('ROLE_ADMIN');
insert into roles (nombre) values ('ROLE_USER');

insert into usuarios_roles(usuario_id, role_id) values (1,1);
insert into usuarios_roles(usuario_id, role_id) values (2,1);
insert into usuarios_roles(usuario_id, role_id) values (2,2);