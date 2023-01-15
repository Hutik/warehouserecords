-- Admin
INSERT INTO warehouse.users (id, name, last_name, username, password, email) VALUES (nextval('warehouse.user_seq'), 'Jan', 'Kowalski', 'Admin', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test@email.com'); -- Admin password: Andrzeju
INSERT INTO warehouse.user_roles VALUES ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 1), ((SELECT id FROM warehouse.users WHERE username LIKE 'Admin'), 2);
-- User
INSERT INTO warehouse.users (id, name, last_name, username, password, email) VALUES (nextval('warehouse.user_seq'), 'Adam', 'Edamski', 'User', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test2@email.com'); -- User password: Andrzeju
INSERT INTO warehouse.user_roles VALUES ((SELECT id FROM warehouse.users WHERE username LIKE 'User'), 2);