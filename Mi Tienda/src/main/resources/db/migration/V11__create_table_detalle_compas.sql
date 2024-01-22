create table detalle_compras (
     id int not null primary key auto_increment,
     cantidad double,
     precio_unitario decimal(10,2),
     subtotal decimal(10,2),
     iva decimal(10,2),
     total decimal(10,2),
     producto_id int,
     compra_id int,

     constraint fk_detalle_compras_productos_id foreign key (producto_id) references productos(id),
     constraint fk_detalle_compras_compras_id foreign key (compra_id) references compras(id)
);