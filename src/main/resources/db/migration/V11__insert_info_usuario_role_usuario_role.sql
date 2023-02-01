INSERT INTO usuario (nome, email, senha) VALUES ('admin', 'admin@forum.com', '$2y$04$2JE9OL7zPNbx4fmTSZo8XuurJwPZGe0kGNWngIOTdiB3KavNvHjbi');
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (2, 2);


