create table cliente
(
    id int auto_increment,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    email varchar(255) null,
    senha varchar(255) not null,
    constraint cliente_pk
        primary key (id)
);

create table produto
(
    id int auto_increment,
    nome varchar(255) not null,
    preco double not null,
    constraint produto_pk
        primary key (id)
);

create table pedido
(
    id int auto_increment,
    data datetime not null,
    status int default 1 not null,
    cliente_id int not null,
    constraint pedido_pk
        primary key (id),
    constraint pedido_cliente_id_fk
        foreign key (cliente_id) references cliente (id)
);