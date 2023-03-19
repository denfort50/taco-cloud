create table if not exists Taco_Order (
    id serial primary key,
    delivery_Name varchar not null,
    delivery_Street varchar not null,
    delivery_City varchar not null,
    delivery_State varchar not null,
    delivery_Zip varchar not null,
    cc_number varchar not null,
    cc_expiration varchar not null,
    cc_cvv varchar not null,
    placed_at timestamp not null
);

create table if not exists Taco (
    id serial primary key,
    name varchar not null,
    taco_order bigint not null references Taco_Order(id),
    taco_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists Ingredient (
    id varchar primary key,
    name varchar not null,
    type varchar not null
);

create table if not exists Ingredient_Ref (
    ingredient varchar not null references Ingredient(id),
    taco bigint not null,
    taco_key bigint not null
);
