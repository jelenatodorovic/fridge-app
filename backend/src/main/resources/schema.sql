create table fridge
(
   id integer not null AUTO_INCREMENT,
   name varchar(255) not null,
   primary key(id)
);

create table fridge_compartment(
    id integer not null AUTO_INCREMENT,
    fridge_id integer not null,
    temp_from double,
    temp_to double,
    capacity double,
    primary key(id),
    foreign key(fridge_id) references fridge(id)
);

create table item
(
   id integer not null AUTO_INCREMENT,
   fridge_compartment_id integer not null,
   name varchar(255) not null,
   type char(4) not null,
   capacity_requirement double not null,
   time_stored date,
   best_before_date date,
   primary key(id),
   foreign key(fridge_compartment_id) references fridge_compartment(id)
);