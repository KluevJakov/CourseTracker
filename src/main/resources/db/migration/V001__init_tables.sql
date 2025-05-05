CREATE TABLE roles (
    id UUID PRIMARY KEY,
    name VARCHAR(320)
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(320),
    password VARCHAR(20),
    age INTEGER,
    role_id UUID,
    activate_code UUID,
    enabled bool,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);