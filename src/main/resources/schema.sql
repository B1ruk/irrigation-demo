create table crop
(

    id                    bigint unsigned auto_increment primary key,
    name                  varchar(100) not null,
    description           varchar(100) not null,
    water_requried_amount double       not null
);

create table land
(
    id        bigint unsigned auto_increment primary key,
    name      varchar(100) not null,
    soil_type varchar(100) not null,
    land_index integer  not null unique ,
    crop bigint unsigned not null,
    foreign key (crop) references crop(id)
);

create table irrigation_time_slot
(
    id bigint unsigned auto_increment primary key ,
    land bigint unsigned not null ,
    irrigation_time int unsigned,
    foreign key (land) references land(id)
);