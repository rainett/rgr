drop table if exists addresses;
drop table if exists ordered_dishes;
drop table if exists dishes;
drop table if exists orders;
drop table if exists payments;
drop table if exists photos;
drop table if exists users;

create table dishes
(
    dish_id    int auto_increment,
    dish_name  varchar(50) not null,
    dish_price int         not null,
    dish_photo_id   int  null,
    constraint dishes_dish_id_uindex
    unique (dish_id),
    constraint dishes_dish_name_uindex
    unique (dish_name)
    );

alter table dishes
    add primary key (dish_id);

create table photos
(
    photo_id   int auto_increment,
    photo_blob mediumblob not null,
    constraint photos_photo_id_uindex
    unique (photo_id)
);

alter table photos
    add primary key (photo_id);

create table users
(
    user_id  int auto_increment,
    username varchar(50)                  not null,
    password varchar(50)                  not null,
    email    varchar(50)                  not null,
    role     varchar(20) default 'client' not null,
    constraint users_email_uindex
    unique (email),
    constraint users_user_id_uindex
    unique (user_id),
    constraint users_username_uindex
    unique (username)
    );

alter table users
    add primary key (user_id);

create table addresses
(
    address_id       int auto_increment,
    user_id          int         not null,
    city             varchar(50) not null,
    street           varchar(50) not null,
    house_number     varchar(10) not null,
    apartment_number varchar(10) null,
    constraint addresses_address_id_uindex
    unique (address_id),
    constraint addresses_users_user_id_fk
    foreign key (user_id) references users (user_id)
    on update cascade on delete cascade
    );

alter table addresses
    add primary key (address_id);

create table orders
(
    order_id   int auto_increment,
    user_id    int not null,
    payment_id int not null,
    address_id int not null,
    price      int not null,
    constraint orders_order_id_uindex
    unique (order_id),
    constraint orders_users_user_id_fk
    foreign key (user_id) references users (user_id)
    on update cascade on delete cascade
    );

alter table orders
    add primary key (order_id);

create table ordered_dishes
(
    ordered_dishes_id int auto_increment,
    order_id          int not null,
    dish_id           int not null,
    dish_amount       int not null,
    constraint ordered_dishes_ordered_dishes_id_uindex
    unique (ordered_dishes_id),
    constraint ordered_dishes_dishes_dish_id_fk
    foreign key (dish_id) references dishes (dish_id),
    constraint ordered_dishes_orders_order_id_fk
    foreign key (order_id) references orders (order_id)
    on update cascade on delete cascade
    );

alter table ordered_dishes
    add primary key (ordered_dishes_id);

create table payments
(
    payment_id     int auto_increment,
    user_id        int      not null,
    payment_number char(16) not null,
    payment_till   char(5)  not null,
    payment_cvv    char(3)  not null,
    constraint payments_payment_id_uindex
    unique (payment_id),
    constraint payments_payment_number_uindex
    unique (payment_number),
    constraint payments_users_user_id_fk
    foreign key (user_id) references users (user_id)
    on update cascade on delete cascade
    );

alter table payments
    add primary key (payment_id);

