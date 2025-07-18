CREATE TABLE topico (
    id bigint not null auto_increment,
    titulo varchar(150) not null unique,
    mensaje TEXT not null,
    fecha datetime not null DEFAULT CURRENT_TIMESTAMP,
    status tinyint(1) not null DEFAULT 1,
    autor_id bigint not null,

    PRIMARY KEY (id),
    constraint fk_topico_autor_id foreign key(autor_id) references usuario(id)
);