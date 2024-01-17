create table kardex (
    id int not null primary key auto_increment,
    fecha date,
    numero varchar(20),
    proveedor_cliente varchar(100),
    tipo enum('ENTRADA','SALIDA'),
    producto_id int not null,
    cantidad decimal(10,2),
    precio_unitario double,
    precio_total double,
    cantidad_existencia decimal(10,2),
    costo_unitario_existencia double,
    costo_total_existencia double,

    constraint fk_kardex_producto_id foreign key (producto_id) references productos(id)
);