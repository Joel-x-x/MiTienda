create table cajas (
    id int not null primary key auto_increment,
    apertura datetime,
    monto_apertura decimal(10,2),
    cierre datetime,
    monto_cierre decimal(10,2),
    valor_calculado decimal(10,2),
    descuadre decimal(10,2),
    cerrada tinyint default 0,
    usuario_id int,

    constraint fk_cajas_usuario_id foreign key (usuario_id) references usuarios(id)
);