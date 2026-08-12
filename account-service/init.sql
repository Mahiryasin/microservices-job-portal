CREATE TABLE users (
    user_id bigserial PRIMARY KEY,
    username VARCHAR(25) NOT NULL UNIQUE DEFAULT '',
    email VARCHAR(25) NOT NULL UNIQUE DEFAULT '',
    price NUMERIC(10,2) NOT NULL
);

-- Kolon listesine 'price' eklendi ve tüm değerler tek tırnağa ('') çevrildi
INSERT INTO users (user_id, username, email, price) VALUES 
(1, 'julia', 'julia@gmail.com', 202),
(2, 'john', 'john@gmail.com', 240),
(3, 'emma', 'emma@gmail.com', 260),
(4, 'bob', 'bob@gmail.com', 230),
(5, 'emily', 'emily@gmail.com', 190);