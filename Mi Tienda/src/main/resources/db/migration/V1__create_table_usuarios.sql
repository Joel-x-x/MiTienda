create table usuarios (
    id int not null primary key auto_increment,
    usuario varchar(25) not null unique,
    clave longblob not null,
    nombre varchar(100),
    apellido varchar(100),
    identificacion varchar(20),
    rol enum('ADMINISTRADOR','VENDEDOR','BODEGUERO') not null,
    estado tinyint default 1
);