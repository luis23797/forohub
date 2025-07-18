CREATE TABLE curso (
    id bigint not null auto_increment,
    nombre varchar(150) not null unique,
    categoria varchar(150) not null unique,
    PRIMARY KEY (id)
);