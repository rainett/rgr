
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS ordered_dishes;

create table clients
(
    client_id int auto_increment
        primary key,
    login     varchar(50) not null,
    password  varchar(50) not null,
    mail      varchar(50) not null,
    role      varchar(50) not null,
    constraint login
        unique (login)
);

INSERT INTO clients VALUES (1, 'admin', 'admin', 'admin@mail.com', 'manager');
INSERT INTO clients VALUES (2, 'ivanov', '1234', 'ivanov@mail.com', 'client');
INSERT INTO clients VALUES (3, 'petrov', '4321', 'petrov@mail.com', 'client');

create table cards
(
    card_id     int      not null,
    client_id   int      not null,
    card_number char(16) null,
    card_till   char(5)  null,
    card_cvv    char(3)  null,
    constraint card_id
        unique (card_id)
);

alter table cards
    add primary key (card_id);

INSERT INTO cards VALUES (1, 1, '1234123412341234', '05-24', '321');
INSERT INTO cards VALUES (2, 1, '4321432143214321', '15-27', '123');
INSERT INTO cards VALUES (3, 2, '4321111122223333', '06-23', '999');

create table addresses
(
    address_id       int         not null,
    client_id        int         not null,
    city             varchar(20) not null,
    street           varchar(30) not null,
    house_number     varchar(10) not null,
    apartment_number varchar(10)     null,
    constraint address_id
        unique (address_id)
);

alter table addresses
    add primary key (address_id);

INSERT INTO addresses VALUES (1, 2, 'Lviv', 'Kulisha', '4', 305);
INSERT INTO addresses VALUES (2, 1,'Lviv','Saharova','39',32);
INSERT INTO addresses VALUES (3, 3, 'Lviv', 'Kulisha', '5', 216);
INSERT INTO addresses (address_id, client_id, city, street, house_number) VALUES (4, 3, 'Lviv', 'Kulisha', '6');

create table dishes
(
    dish_id    int auto_increment,
    dish_name  varchar(40) not null,
    dish_price int         not null,
    dish_pic   varchar(30) null,
    constraint dish_id
        unique (dish_id)
);

alter table dishes
    add primary key (dish_id);

INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Суші', 400, 'images/sushi.jpeg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Вареники', 100, 'images/varenyky.jpeg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Борщ', 80, 'images/borsch.jpeg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Цезар з куркою', 170, 'images/caesar_w_chicken.jpg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Грецький салат', 120, 'images/greek_salad.jpeg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Паста Карбонара', 132, 'images/pasta_carbonara.jpg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Крем-суп з лососем', 136, 'images/salmon_soup.jpg');
INSERT INTO dishes(dish_name, dish_price, dish_pic) VALUES ('Шашлик з телятини', 130, 'images/shashlyk.jpg');

create table ordered_dishes
(
    ordered_id  int         not null,
    dish_id     varchar(40) not null
        primary key,
    dish_amount int         not null
);

INSERT INTO ordered_dishes VALUES (1, 2, 2);
INSERT INTO ordered_dishes VALUES (1, 1, 1);
INSERT INTO ordered_dishes VALUES (2, 3, 1);

create table orders
(
    order_id   int not null,
    client_id  int not null,
    ordered_id int not null,
    card_id    int not null,
    address_id int not null,
    constraint order_id
        unique (order_id)
);

alter table orders
    add primary key (order_id);

INSERT INTO orders VALUES (2, 1, 1, 1, 2);

select c.login, crd.card_number, a.street, d.dish_name, od.dish_amount from orders
    inner join clients c on orders.client_id = c.client_id
    inner join cards crd on c.client_id = crd.client_id
    inner join addresses a on c.client_id = a.client_id
    inner join ordered_dishes od on orders.ordered_id = od.ordered_id
    inner join dishes d on od.dish_id = d.dish_id
where orders.card_id = crd.card_id and orders.address_id = a.address_id


