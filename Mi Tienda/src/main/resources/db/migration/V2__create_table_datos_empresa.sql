create table datos_empresa (
    id int not null primary key auto_increment,
    nombre varchar(70),
    direccion varchar(200),
    celular varchar(15),
    correo varchar(50),
    identificacion varchar(15),
    estado tinyint default 1
);