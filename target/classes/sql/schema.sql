DROP TABLE IF EXISTS ventas_detalle;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS proveedores;




CREATE TABLE proveedores (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL,
    observaciones VARCHAR(200)
);

CREATE TABLE clientes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    dni CHAR(8) NOT NULL,
    domicilio VARCHAR(20),
    telefono VARCHAR(25) 
);


CREATE TABLE productos (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio_venta INT NOT NULL,
    precio_compra INT NOT NULL,
    stock INT NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    id_proveedor INT NOT NULL, 
    CONSTRAINT fk_productos_proveedores
    FOREIGN KEY (id_proveedor)
    REFERENCES proveedores(id)
);


CREATE TABLE ventas (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    id_cliente INT NOT NULL,
    precio_total_venta INT NOT NULL,
    fecha_venta DATE NOT NULL,
    CONSTRAINT fk_ventas_clientes
    FOREIGN KEY (id_cliente)
    REFERENCES clientes(id)
);

CREATE TABLE ventas_detalle (
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_total INT NOT NULL,
    CONSTRAINT fk_ventas_detalle_ventas
    FOREIGN KEY (id_venta)
    REFERENCES ventas(id),
    CONSTRAINT fk_ventas_detalle_producto
    FOREIGN KEY (id_producto)
    REFERENCES productos(id),
    PRIMARY KEY (id_venta,id_producto)
);
