-- One admin user, named curro with passwor curro and authority admin
INSERT INTO users(username,password,enabled) VALUES ('curro','curro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'curro','admin');

-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');








--A CONTINUACION EMPIEZA LO MODIFICADO POR EL GRUPO



--ejercicio 8 practica 1
INSERT INTO users(username,password,enabled) VALUES ('danricost','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'danricost','owner');

INSERT INTO users(username,password,enabled) VALUES ('ismlunati','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'ismlunati','owner');

INSERT INTO users(username,password,enabled) VALUES ('rodsangon','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'rodsangon','owner');

INSERT INTO users(username,password,enabled) VALUES ('frabreloz','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'frabreloz','owner');

INSERT INTO users(username,password,enabled) VALUES ('gonrodter','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'gonrodter','owner');

INSERT INTO users(username,password,enabled) VALUES ('robpazriv','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'robpazriv','owner');


INSERT INTO owners VALUES (11, 'Daniel', 'Rico Ostos', '110 W. Liberty St.', 'Madison', '6085551023', 'danricost');
INSERT INTO owners VALUES (12, 'Ismael', 'Luna Atienza', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'ismlunati');
INSERT INTO owners VALUES (13, 'Rodrigo', 'Sánchez González', '2693 Commerce St.', 'McFarland', '6085558763', 'rodsangon');
INSERT INTO owners VALUES (14, 'Francisco Jose', 'Brenes Lozano', '563 Friendly St.', 'Windsor', '6085553198', 'frabreloz');
INSERT INTO owners VALUES (15, 'Gonzalo', 'Rodríguez Terrón', '2387 S. Fair Way', 'Madison', '6085552765', 'gonrodter');
INSERT INTO owners VALUES (16, 'Roberto', 'Paz Rivera', '105 N. Lake St.', 'Monona', '6085552654', 'robpazriv');




INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'mascotaDani', '2010-09-07', 1, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'mascotaIsmael', '2012-08-06', 6, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'mascotaRodrigo', '2011-04-17', 2, 13);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'mascotaFran', '2010-03-07', 2, 14);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'mascotaGonzalo', '2010-11-30', 3, 15);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (19, 'mascotaRoberto', '2010-01-20', 4, 16);












--A CONTINUACION EMPIEZA LO MODIFICADO POR EL GRUPO PARA EL PROYECTO



--insertamos vehiculo
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('4772HZC', 'Coche');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('7288ASM', 'Moto');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('9008XXC', 'Coche');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('7395LRG', 'Moto');
INSERT INTO vehiculos(matricula, tipovehiculo) VALUES ('3560GFH', 'Moto');



-- insertamos dependientes
INSERT INTO users(username,password,enabled) VALUES ('dependiente','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'dependiente','dependiente');
INSERT INTO dependientes(nombre, username, cuentabancaria, dni, sueldo, fechanacimiento) VALUES ('Paco Lastre', 'dependiente', '4599830100494019', '15151515R', 2000, '1992-08-01');



-- insertamos repartidores
INSERT INTO users(username,password,enabled) VALUES ('repartidor','clave',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'repartidor','repartidor');
INSERT INTO repartidores(nombre, username, cuentabancaria, dni, sueldo, fechanacimiento, vehiculo_id) VALUES ('Jorge Méndez', 'repartidor', '4548812049400004', '77133335P', 2300, '1995-11-21', 3);
INSERT INTO repartidores(nombre, username, cuentabancaria, dni, sueldo, fechanacimiento, vehiculo_id) VALUES ('Rafael Castaño', 'repartidor', '4548032003933011', '37551947T', 2220, '1998-03-14', 2);



-- insertamos cocineros
INSERT INTO cocineros(nombre, cuentabancaria, dni, sueldo, fechanacimiento) VALUES ('Cristian Andorra', '4599830100592012', '79051555L', 2400, '1985-08-15');



-- insertamos clientes
INSERT INTO users(username,password,enabled) VALUES ('generico@gmail.com','generico',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'generico@gmail.com','cliente');
INSERT INTO clientes(nombre, apellidos, telefono, direccion, fechanacimiento, username) VALUES ('Generico', 'Generico', '666666666', 'Calle Generica', '2000-01-01', 'generico@gmail.com');

INSERT INTO users(username,password,enabled) VALUES ('juan@gmail.com','juan',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'juan@gmail.com','cliente');
INSERT INTO clientes(nombre, apellidos, telefono, direccion, fechanacimiento, username) VALUES ('Juan', 'Pérez Rodriguez', '629877612', 'Avenida El Pantano, 18', '1999-09-21', 'juan@gmail.com');

INSERT INTO users(username,password,enabled) VALUES ('pedro@gmail.com','pedro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'pedro@gmail.com','cliente');
INSERT INTO clientes(nombre, apellidos, telefono, direccion, fechanacimiento, username) VALUES ('Pedro', 'Clemente Ortiz', '643556901', 'Calle Fuencarral, 2', '2000-07-11', 'pedro@gmail.com');

INSERT INTO users(username,password,enabled) VALUES ('antonio@gmail.com','antonio',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'antonio@gmail.com','cliente');
INSERT INTO clientes(nombre, apellidos, telefono, direccion, fechanacimiento, username) VALUES ('Antonio', 'Candau López', '625410972', 'Avenida El Pantano, 47', '1979-11-18', 'antonio@gmail.com');



-- insertamos alergenos
INSERT INTO alergenos(alergenotype) VALUES ('Gluten');
INSERT INTO alergenos(alergenotype) VALUES ('Crustaceos');
INSERT INTO alergenos(alergenotype) VALUES ('Huevos');
INSERT INTO alergenos(alergenotype) VALUES ('Pescado');
INSERT INTO alergenos(alergenotype) VALUES ('Cacahuetes');
INSERT INTO alergenos(alergenotype) VALUES ('Soja');
INSERT INTO alergenos(alergenotype) VALUES ('Lacteos');
INSERT INTO alergenos(alergenotype) VALUES ('Oregano');
INSERT INTO alergenos(alergenotype) VALUES ('FrutosSecos');
INSERT INTO alergenos(alergenotype) VALUES ('Apio');
INSERT INTO alergenos(alergenotype) VALUES ('Mostaza');
INSERT INTO alergenos(alergenotype) VALUES ('GranosDeSesamo');
INSERT INTO alergenos(alergenotype) VALUES ('DioxidoDeAzufre');
INSERT INTO alergenos(alergenotype) VALUES ('Moluscos');
INSERT INTO alergenos(alergenotype) VALUES ('Altramuces');



-- insertamos productos
INSERT INTO productos(id, name, precio, descripcion) VALUES (1, 'Pizza Margarita', 10, 'La pizza margarita de nuestro local es atípica, contiene atún, anchoa, jamón york y aceitunas');
INSERT INTO productos(id, name, precio, descripcion) VALUES (2, 'Gallo Morón', 12, 'La pizza Gallo Morón es original de nuestra pizzería haciendo honor a un símbolo del pueblo, está elaborada con carne de pollo, queso, salami, jamón york y champiñones');
INSERT INTO productos(id, name, precio, descripcion) VALUES (3, 'Pizza Leonor', 11, 'La pizza Leonor, contiene piña y jamón york ');



-- insertamos alergenos en productos
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(1, 3);
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(1, 4);
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(1, 7);

INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(2, 3);
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(2, 7);
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(2, 6);

INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(3, 7);
INSERT INTO productos_alergenos(productos_id,alergenos_id) VALUES(3, 6);



-- insertamos pedidos
INSERT INTO pedidos(id, fecha, hora_estimada, comentario, valoracion, metodopago, estadopedido, tipopedido, cliente_id) VALUES(1,'2020-08-06 15:59:42', '16:59:42', 'excelente', 5, 'efectivo', 'Entregado', 'enLocal', 2);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(2,'2020-01-02 15:57:48', '16:57:48', 'tarjeta', 'pendiente', 'enLocal', 3);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(3,'2020-05-11 15:41:11', '16:41:11', 'efectivo', 'pendiente', 'enLocal', 4);

INSERT INTO pedidos(id, fecha, hora_estimada, comentario, valoracion, metodopago, estadopedido, tipopedido, cliente_id) VALUES(4,'2020-07-05 15:59:48', '16:59:48', 'bueno', 4, 'efectivo', 'Entregado', 'aDomicilio', 2);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(5,'2020-01-02 17:12:30', '18:12:30', 'tarjeta', 'enReparto', 'aDomicilio', 3);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(6,'2021-01-14 12:42:20', '13:42:20', 'tarjeta', 'pendiente', 'aDomicilio', 4);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(7,'2021-01-23 10:44:02', '11:44:02', 'efectivo', 'pendiente', 'aDomicilio', 2);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(8,'2021-02-02 09:11:38', '10:11:38', 'tarjeta', 'pendiente', 'aDomicilio', 3);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(9,'2021-02-17 17:33:06', '18:33:06', 'efectivo', 'pendiente', 'aDomicilio', 4);
INSERT INTO pedidos(id, fecha, hora_estimada, metodopago, estadopedido, tipopedido, cliente_id) VALUES(10,'2021-02-26 21:05:18', '22:05:18', 'tarjeta', 'pendiente', 'aDomicilio', 2);



-- insertamos linea_pedidos
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (1, 2, 1);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (2, 1, 3);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (3, 3, 3);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (4, 4, 2);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (5, 6, 1);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (6, 1, 2);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (7, 1, 3);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (8, 2, 1);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (9, 4, 3);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (10, 1, 1);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (11, 3, 2);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (12, 2, 3);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (13, 1, 3);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (14, 2, 1);

INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (15, 3, 2);
INSERT INTO linea_pedidos(id, cantidad, producto_id) VALUES (16, 1, 1);



-- insertamos pedidos_linea_pedidos
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (1, 1);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (1, 2);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (2, 3);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (2, 4);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (3, 5);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (4, 6);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (4, 7);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (5, 8);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (6, 9);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (6, 10);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (7, 11);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (8, 12);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (9, 13);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (9, 14);

INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (10, 15);
INSERT INTO pedidos_linea_pedidos(pedido_id, linea_pedidos_id) VALUES (10, 16);



-- insertamos repartos
INSERT INTO repartos(id, fecha, hora_inicio, repartidor_id) VALUES (1, '1999-11-12', '12:11:11', 1);



-- insertamos pedidos en el reparto
INSERT INTO repartos_pedidos(reparto_id, pedidos_id) VALUES (1, 4);
INSERT INTO repartos_pedidos(reparto_id, pedidos_id) VALUES (1, 5);








