-- Admin
INSERT INTO warehouse.users (name, lastName, username, password, email) VALUES ('Jan', 'Kowalski', 'Admin', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test@email.com'); -- password: Andrzeju
INSERT INTO warehouse.user_roles VALUES ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 1), ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 2);
-- User
INSERT INTO warehouse.users (name, lastName, username, password, email) VALUES ('Adam', 'Edamski', 'User', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test2@email.com'); -- password: Andrzeju
INSERT INTO warehouse.user_roles VALUES ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 1), ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 2);