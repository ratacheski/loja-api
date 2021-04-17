create table item_pedido
(
    pedido_id int not null,
    produto_id int not null,
    quantidade int not null,
    valor_unitario numeric(9,2) not null,
    constraint item_pedido_pk
        primary key (pedido_id, produto_id),
    constraint item_pedido_pedido_id_fk
        foreign key (pedido_id) references pedido (id),
    constraint item_pedido_produto_id_fk
        foreign key (produto_id) references produto (id)
);