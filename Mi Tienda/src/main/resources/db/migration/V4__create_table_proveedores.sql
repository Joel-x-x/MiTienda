create table proveedores (
    id int not null primary key auto_increment,
    identificacion varchar(20),
    razonSocial varchar(100),
    empresa varchar(),
    direccion varchar(100),
    celular varchar(15),
    correo varchar(50),
    descripcion varchar(300),
    estado tinyint default 1
);