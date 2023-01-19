-- Admin
INSERT INTO warehouse.users (id, name, last_name, username, password, email) VALUES (1, 'Jan', 'Kowalski', 'Admin', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test@email.com'); -- Admin password: Andrzeju
INSERT INTO warehouse.user_roles VALUES (1, 1), (1, 2);
-- User
INSERT INTO warehouse.users (id, name, last_name, username, password, email) VALUES (2, 'Adam', 'Edamski', 'User', '$2a$12$cvCZfVJQGkTNNVOZrCvu5.vd2uIJBLmYbtpOxOb40zByMJuv1dnb6', 'test2@email.com'); -- User password: Andrzeju
INSERT INTO warehouse.user_roles VALUES (2, 2);