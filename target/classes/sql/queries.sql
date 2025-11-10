-- Muestra todas las ventas con nombre y apellido del cliente

SELECT v.id AS id_venta, c.nombre, c.apellido, v.precio_total_venta, v.fecha_venta
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id
ORDER BY v.id;

-- Muestra detalle completo de cada venta

SELECT v.id AS id_venta, c.nombre AS cliente, p.nombre AS producto,
       vd.cantidad, vd.precio_total
FROM ventas_detalle vd
JOIN ventas v ON vd.id_venta = v.id
JOIN clientes c ON v.id_cliente = c.id
JOIN productos p ON vd.id_producto = p.id;

-- Muestra el stock total de productos por proveedor

SELECT pr.nombre AS proveedor, SUM(p.stock) AS total_stock
FROM productos p
JOIN proveedores pr ON p.id_proveedor = pr.id
GROUP BY pr.nombre;

-- Busca productos por categoría 

SELECT * FROM productos
WHERE categoria LIKE '%Alimento%';

-- Muestra el stock por categoria

SELECT categoria, SUM(stock) AS total_stock
FROM productos
GROUP BY categoria;

-- Muestra el total vendido por cliente

SELECT c.nombre, c.apellido, SUM(v.precio_total_venta) AS total_gastado
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id
GROUP BY c.id;

