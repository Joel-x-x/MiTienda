create table productos (
    id int not null primary key auto_increment,
    codigo varchar(20),
    nombre varchar(100),
    descripcion varchar(300),
    categoria_id int not null,
    iva_id int not null,
    tiene_iva tinyint default 1,
    utilidad DECIMAL(10, 2),
    stock DECIMAL(10,2),
    ultimo_precio_compra Double,
    precio_medio Double,
    ultima_fecha_compra date,
    ultimo_proveedor_compra varchar(100),
    estado tinyint default 1,

    constraint fk_productos_categoria_id foreign key (categoria_id) references categorias(id),
    constraint fk_productos_iva_id foreign key (iva_id) references ivas(id)
);