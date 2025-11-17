INSERT INTO proveedores (nombre, telefono, email) VALUES
('AgroCampo', '1122334455', 'contacto@agrocampo.com'),
('PetDistribuidora', '1133445566', 'ventas@petdistribuidora.com'),
('NutriVet', '1144556677', 'info@nutrivet.com'),
('CampoFértil', '1155667788', 'ventas@campoferil.com'),
('VetLine', '1166778899', 'contacto@vetline.com'),
('EcoPet', '1177889900', 'info@ecopet.com'),
('AgroAndes', '1188990011', 'contacto@agroandes.com');

INSERT INTO clientes (nombre, apellido, dni, domicilio, telefono) VALUES
('Juan', 'Pérez', '40893123', 'Av. Mitre 101', '1123456789'),
('María', 'Gómez', '37221111', 'San Martín 532', '1139876543'),
('Pedro', 'Sánchez', '44902123', 'Calle Belgrano 512', '1145678901'),
('Lucía', 'Fernández', '41589011', '9 de Julio 321', '1122334455');

INSERT INTO productos (nombre, precio_venta, precio_compra, stock, descripcion, categoria, id_proveedor) VALUES
('Alimento Perro Adulto 20kg', 23000, 18000, 30, 'Balanceado perro adulto marca NutriVet', 'Alimento', 3),
('Alimento Perro Cachorro 15kg', 24500, 19500, 20, 'Balanceado cachorro alta proteína', 'Alimento', 3),
('Alimento Gato Premium 10kg', 21000, 16000, 20, 'Alimento premium gatos adultos', 'Alimento', 3),
('Alimento Conejo Natural 5kg', 9500, 7000, 25, 'Mezcla de alfalfa y maíz natural', 'Alimento', 1),
('Alimento Caballo Premium 25kg', 35000, 28000, 10, 'Balanceado alto rendimiento', 'Alimento', 4),
('Maíz Quebrado 25kg', 9500, 7000, 40, 'Maíz triturado para aves y cerdos', 'Granos', 1),
('Avena Pelada 20kg', 11000, 8500, 35, 'Avena natural para equinos', 'Granos', 4),
('Alfalfa Premium 25kg', 14000, 10000, 15, 'Alfalfa seca seleccionada', 'Forraje', 4),
('Pasto Seco Mezcla 30kg', 12500, 9000, 25, 'Pasto seco con trébol y avena', 'Forraje', 4),
('Semillas de Maíz 10kg', 9500, 7000, 30, 'Semillas de maíz seleccionadas para siembra', 'Semillas', 7),
('Correa Reforzada Talla L', 12000, 8500, 10, 'Correa de nylon reforzada', 'Accesorios', 2),
('Collar Antipulgas Perro', 9000, 6000, 20, 'Collar antipulgas de larga duración', 'Accesorios', 2),
('Juguete Pelota Mordedora', 5500, 3000, 25, 'Pelota de caucho resistente para perro', 'Accesorios', 2),
('Comedero Doble Inoxidable', 8500, 5000, 15, 'Comedero doble de acero', 'Accesorios', 2),
('Piedritas Sanitarias 10kg', 8500, 6000, 25, 'Arena sanitaria absorbente', 'Higiene', 6),
('Shampoo Perro Hipoalergénico', 11000, 7500, 18, 'Shampoo natural para piel sensible', 'Higiene', 6),
('Desinfectante Canil 1L', 6500, 4000, 20, 'Desinfectante ecológico para espacios de animales', 'Higiene', 6),
('Vitaminas Equinas Plus 1kg', 22000, 17000, 8, 'Suplemento vitamínico para caballos', 'Suplementos', 5),
('Desparasitante Interno 100ml', 18000, 14000, 10, 'Desparasitante interno amplio espectro', 'Medicamento', 5),
('Antipulgas Spot-On Perro 20kg+', 16000, 12000, 15, 'Tratamiento mensual antipulgas', 'Medicamento', 5),
('Calcio para Aves 500g', 7000, 4000, 20, 'Suplemento mineral para aves ponedoras', 'Suplementos', 5);


INSERT INTO ventas (id_cliente, precio_total_venta, fecha_venta) VALUES
(1, 32500, '2025-11-01'),
(2, 21000, '2025-11-01'),
(3, 45500, '2025-11-02'),
(4, 14000, '2025-11-02'),
(1, 37000, '2025-11-03'),
(2, 51000, '2025-11-03'),
(3, 28000, '2025-11-04'),
(4, 19500, '2025-11-04'),
(1, 8500,  '2025-11-04'),
(2, 31000, '2025-11-04');

INSERT INTO ventas_detalle (id_venta, id_producto, cantidad, precio_total) VALUES
(1, 1, 1, 23000), 
(1, 4, 1, 9500),
(2, 3, 1, 21000),
(3, 6, 2, 19000),
(3, 5, 1, 35000),
(4, 8, 1, 14000),
(5, 2, 1, 24500),
(5, 15, 1, 11000),
(5, 14, 1, 1500), 
(6, 19, 1, 18000),
(6, 18, 1, 22000),
(6, 16, 1, 11000),
(7, 4, 2, 19000),
(7, 9, 1, 9000),
(8, 15, 1, 11000),
(8, 13, 1, 5500),
(8, 12, 1, 9000),
(9, 16, 1, 8500),
(10, 11, 1, 12000),
(10, 20, 1, 16000),
(10, 16, 1, 3000);