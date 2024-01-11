create table clientes (
    id int not null primary key auto_increment,
    identificacion varchar(20),
    nombre varchar(70),
    apellido varchar(70),
    celular varchar(15),
    estado tinyint default 1
);