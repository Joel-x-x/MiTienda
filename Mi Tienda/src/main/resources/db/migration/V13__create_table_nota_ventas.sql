create table nota_ventas (
     id int not null primary key auto_increment,
     numero varchar(20),
     punto_emision varchar(10),
     establecimiento varchar(10),
     fecha date,
     forma_pago varchar(50) default 'Efectivo',
     descuento decimal(10,2),
     cliente_id int,
     tiene_cliente tinyint default 0,
     usuario_id int,
     total decimal(10,2),

     constraint fk_nota_ventas_cliente_id foreign key (cliente_id) references clientes(id),
     constraint fk_nota_ventas_usuario_id foreign key (usuario_id) references usuarios(id)
);