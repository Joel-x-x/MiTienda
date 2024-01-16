create table compras (
     id int not null primary key auto_increment,
     punto_emision varchar(10),
     establecimiento varchar(10),
     numero varchar(20),
     fecha date,
     forma_pago varchar(50) default 'Efectivo',
     descuento decimal(10,2),
     proveedor_id int,
     tiene_proveedor tinyint default 0,
     subtotal decimal(10,2),
     iva decimal(10,2),
     total decimal(10,2),

     constraint fk_compras_proveedor_id foreign key (proveedor_id) references proveedores(id)
);