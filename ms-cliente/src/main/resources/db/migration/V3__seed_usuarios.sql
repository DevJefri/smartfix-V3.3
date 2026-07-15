CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO usuarios (username, password, role)
VALUES
    ('admin@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'ADMIN'),
    ('user1@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user2@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user3@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user4@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user5@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user6@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user7@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user8@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER'),
    ('user9@smartfix.cl',  crypt('abcd.1234', gen_salt('bf')), 'USER')
ON CONFLICT (username) DO NOTHING;
