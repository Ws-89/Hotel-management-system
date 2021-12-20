CREATE TABLE IF NOT EXISTS guest (
    GUEST_ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    locked BIT(1),
    enabled BIT(1),
    application_user_role VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    country VARCHAR(255),
    zipcode VARCHAR(255);

);