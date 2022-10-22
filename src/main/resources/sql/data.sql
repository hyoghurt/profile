DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

-- Brad password - brad
INSERT INTO users (first_name, last_name, phone, email, password)
VALUES
('Brad', 'Pitt', '8900121212', 'brad@bit.com', '$2a$10$X1h0LNQMtjlODWPYL8wI1uuLOU66AZtuHBzKMJZn4Tr5XkcEdVcRe');
