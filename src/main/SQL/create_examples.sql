
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS ordered_dishes;

CREATE TABLE clients(
    client_id INTEGER NOT NULL,
    login varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    mail varchar(50) NOT NULL,
    role varchar(50) NOT NULL,
    PRIMARY KEY (client_id),
    UNIQUE KEY (login)
);

INSERT INTO clients VALUES (1, 'admin', 'admin', 'admin@mail.com', 'manager');
INSERT INTO clients VALUES (2, 'ivanov', '1234', 'ivanov@mail.com', 'client');
INSERT INTO clients VALUES (3, 'petrov', '4321', 'petrov@mail.com', 'client');

CREATE TABLE cards
(
    card_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    card_number CHAR(16),
    card_till CHAR(5),
    card_cvv CHAR(3),
    PRIMARY KEY (card_id),
    UNIQUE KEY (card_id)
);

INSERT INTO cards VALUES (1, 1, '1234123412341234', '05-24', '321');
INSERT INTO cards VALUES (2, 1, '4321432143214321', '15-27', '123');
INSERT INTO cards VALUES (3, 2, '4321111122223333', '06-23', '999');

CREATE TABLE addresses(
    address_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    city varchar(20) NOT NULL,
    street varchar(30) NOT NULL,
    house_number VARCHAR(10) NOT NULL,
    apartment_number INTEGER,
    PRIMARY KEY (address_id),
    UNIQUE KEY (address_id)
);

INSERT INTO addresses VALUES (1, 2, 'Lviv', 'Kulisha', '4', 305);
INSERT INTO addresses VALUES (2, 1,'Lviv','Saharova','39',32);
INSERT INTO addresses VALUES (3, 3, 'Lviv', 'Kulisha', '5', 216);
INSERT INTO addresses (address_id, client_id, city, street, house_number) VALUES (4, 3, 'Lviv', 'Kulisha', '6');

CREATE TABLE dishes(
    dish_id INTEGER NOT NULL,
    dish_name varchar(40) NOT NULL,
    dish_price INTEGER NOT NULL,
    PRIMARY KEY (dish_id),
    UNIQUE KEY (dish_id)
);

INSERT INTO dishes VALUES (1, 'Sushi', 400);
INSERT INTO dishes VALUES (2,'Varenyky', 100);
INSERT INTO dishes VALUES (3, 'Borsch', 80);

CREATE TABLE ordered_dishes(
    ordered_id INTEGER NOT NULL,
    dish_id varchar(40) NOT NULL,
    dish_amount INTEGER NOT NULL,
    PRIMARY KEY (dish_id)
);

INSERT INTO ordered_dishes VALUES (1, 2, 2);
INSERT INTO ordered_dishes VALUES (1, 1, 1);
INSERT INTO ordered_dishes VALUES (2, 3, 1);

CREATE TABLE orders(
    order_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    ordered_id INTEGER NOT NULL,
    card_id INTEGER NOT NULL,
    address_id INTEGER NOT NULL,
    PRIMARY KEY (order_id),
    UNIQUE KEY (order_id)
);

INSERT INTO orders VALUES (2, 1, 1, 1, 2);

select c.login, crd.card_number, a.street, d.dish_name, od.dish_amount from orders
    inner join clients c on orders.client_id = c.client_id
    inner join cards crd on c.client_id = crd.client_id
    inner join addresses a on c.client_id = a.client_id
    inner join ordered_dishes od on orders.ordered_id = od.ordered_id
    inner join dishes d on od.dish_id = d.dish_id
where orders.card_id = crd.card_id and orders.address_id = a.address_id


