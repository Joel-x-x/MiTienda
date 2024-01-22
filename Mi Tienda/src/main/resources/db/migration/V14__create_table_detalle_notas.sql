create table detalle_notas (
     id int not null primary key auto_increment,
     cantidad double,
     precio decimal(10,2),
     total decimal(10,2),
     producto_id int,
     nota_venta_id int,

     constraint fk_detalle_notas_productos_id foreign key (producto_id) references productos(id),
     constraint fk_detalle_notas_nota_ventas_id foreign key (nota_venta_id) references nota_ventas(id)
);